package main;

import actionhandler.ActionMenu;
import actionhandler.ActionPicker;
import gamecharacters.GameCharacter;
import gamecharacters.Party;
import inventory.equippables.jewelry.AmuletOfDahra;
import inventory.equippables.jewelry.Jewelry;
import statuseffects.CoolDown;
import statuseffects.StatusEffect;

import java.util.ArrayList;
import java.util.Scanner;


public class Battle {
    private final static CooldownManager cooldownManager = new CooldownManager();
    private final static CharacterOrderManager characterOrderManager = new CharacterOrderManager();
    private final static Scanner scanner = new Scanner(System.in);
    private final Game game;
    private GameCharacter currentCharacter;
    private Party currentParty;
    private Party currentEnemy;
    private boolean allEnemiesDefeated;

    // Constructor
    public Battle(Game game) {
        this.game = game;
        this.currentParty = game.getMonsterParty();
        this.currentEnemy = game.getHeroParty();
        characterOrderManager.determineOrder();
        this.currentCharacter = CharacterOrderManager.getCharacterAt(1);
        this.allEnemiesDefeated = false;
    }

    // Getters
    public static CooldownManager getCooldownManager() {
        return cooldownManager;
    }

    public static CharacterOrderManager getCharacterOrderManager() {
        return characterOrderManager;
    }

    // Main.Battle Mechanics
    public void execute() {
        ArrayList<GameCharacter> order = characterOrderManager.getOrder();

        while (!allEnemiesDefeated && !game.gameOver) {

            for (GameCharacter character : order) {
                boolean skipAction = false;

                setCurrentCharacter(character);
                battleReport();
                currentCharacter.checkForStatusEffect();
                checkForStartOfRoundEvents();

                // Player picks action or computer picks action.
                if (!currentCharacter.isDead() &&
                        !currentCharacter.isFrightened() &&
                        !currentCharacter.isFrozen() &&
                        !skipAction) {
                    if (isComputerPlayer()) {
                        ActionPicker actionPicker = new ActionPicker(currentCharacter, currentParty);
                        actionPicker.pickAction();
                    } else {
                        ActionMenu menu = new ActionMenu(currentCharacter);
                        menu.print();
                        menu.pickAction();
                    }
                }

                // Resolving round
                removeDeadCharacters();
                checkForEndOfBattle();
                updateCounters();

                // End battle if all enemies are defeated
                if (allEnemiesDefeated) {
                    characterOrderManager.clear();
                    break;
                }

                // End battle if all heroes are defeated
                if (game.gameOver) {
                    characterOrderManager.clear();
                    cooldownManager.clear();
                    break;
                }

                System.out.println("\n\u001B[1mPress ENTER to continue\u001B[0m");
                scanner.nextLine();
            }


            // When all characters have taken their turn, END ROUND
            // Updating cooldowns
            cooldownManager.updateCooldowns();
            removeRedundantStatusEffects();

            // Back to the first character by copying updated characterorder
            order = new ArrayList<>(characterOrderManager.getOrder());


        }
    }

    private void updateCounters() {
        if (currentCharacter.hasEffect()) {
            StatusEffect effect = currentCharacter.getEffect();
            effect.countDownByOneCooldown();
            effect.countDownByOneActive();
        }
        if (currentCharacter.getSpecialAttack() != null) {
            if (currentCharacter.getSpecialAttack().isOnCooldown()) {
                currentCharacter.getSpecialAttack().countDownByOne();
            }
        }
    }

    private void checkForStartOfRoundEvents() {
        // Check for amulet of dahra
        Jewelry jewelry = currentCharacter.getEquippedItems().getJewelry();
        if (jewelry instanceof AmuletOfDahra amulet) {
            amulet.getRegeneration().regenerate(currentCharacter);

        }
    }

    private void checkForEndOfBattle() {
        if (currentEnemy.isEmpty()) {
            System.out.println("\nAll enemies in this round have been defeated!");
            System.out.println();

            allEnemiesDefeated = true;

            // Loot remaining party inventory items
            if (!currentEnemy.getInventory().getItems().isEmpty()) {
                currentEnemy.loot(currentParty.getInventory());
            }

            int ADD_BATTLE = 1;
            game.setNumOfBattles(ADD_BATTLE);

            System.out.println("\n\u001B[1mPress ENTER to continue\u001B[0m");
            scanner.nextLine();
        } else if (game.getHeroParty().isEmpty()) {
            System.out.println();
            System.out.println("All heroes have been defeated! The dark forces have prevailed.");
            game.gameOver = true;
        }
    }

    private void removeDeadCharacters() {
        for (GameCharacter character2 : characterOrderManager.getOrder()) {
            if (character2.isDead()) {
                CharacterOrderManager.remove(character2);
            }
        }
    }


    public void battleReport() {
        System.out.println("===================== THE BATTLE =====================");
        for (GameCharacter character : game.getHeroParty().getCharacters()) {
            if (character.equals(currentCharacter)) {
                System.out.println("\u001B[33m" +
                        "[" + (characterOrderManager.getOrder().indexOf(character) + 1) + "] " +
                        character.getCharacterReport() + "\u001B[0m");
            } else {
                System.out.println("[" + (characterOrderManager.getOrder().indexOf(character) + 1) + "] " +
                        character.getCharacterReport());
            }
        }
        System.out.println("------------------------- VS -------------------------");
        for (GameCharacter character : game.getMonsterParty().getCharacters()) {
            if (character.equals(currentCharacter)) {
                System.out.println("\u001B[33m" +
                        "[" + (characterOrderManager.getOrder().indexOf(character) + 1) + "] " +
                        character.getCharacterReport() + "\u001B[0m");
            } else {
                System.out.println("[" + (characterOrderManager.getOrder().indexOf(character) + 1) + "] " +
                        character.getCharacterReport());
            }
        }
        System.out.println("======================================================");
        System.out.println();
        System.out.println("\u001B[34mIts " + currentCharacter.getName() + "'s turn...\u001B[0m");
        System.out.println();
    }

    private boolean isComputerPlayer() {
        return currentCharacter.getOwnParty().equals(game.getMonsterParty());
    }

    private void removeRedundantStatusEffects() {
        ArrayList<GameCharacter> effectsToRemove = new ArrayList<>();

        for (GameCharacter character : characterOrderManager.getOrder()) {
            if (character.hasEffect()) {
                StatusEffect effect = character.getEffect();

                if (!effect.isActive() && !effect.isOnCooldown()) {
                    effectsToRemove.add(character);
                } else if (!effect.isActive() && effect.isOnCooldown()) {
                    character.setEffect(new CoolDown(effect));
                }
            }
        }

        for (GameCharacter character : effectsToRemove) {
            character.setEffect(null);
        }
    }

    // Setters
    public void setCurrentCharacter(GameCharacter currentCharacter) {
        this.currentCharacter = currentCharacter;
        setCurrentParty(currentCharacter.getOwnParty());
        if (currentParty.equals(game.getMonsterParty())) {
            setCurrentEnemy(game.getHeroParty());
        } else {
            setCurrentEnemy(game.getMonsterParty());
        }
    }

    public void setCurrentEnemy(Party currentEnemy) {
        this.currentEnemy = currentEnemy;
    }

    public void setCurrentParty(Party currentParty) {
        this.currentParty = currentParty;
    }


}
