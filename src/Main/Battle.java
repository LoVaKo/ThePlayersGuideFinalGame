package Main;

import ActionHandler.ActionMenu;
import ActionHandler.ActionPicker;
import GameCharacters.GameCharacter;
import GameCharacters.Heroes.Walt;
import StatusEffects.Frightened;

import java.util.ArrayList;
import java.util.Scanner;


public class Battle {
    private final static CooldownManager cooldownManager = new CooldownManager();
    private final static CharacterOrderManager characterOrderManager = new CharacterOrderManager();
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



    // Main.Battle Mechanics
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<GameCharacter> order = characterOrderManager.getOrder();

        while (!allEnemiesDefeated && !game.gameOver) {

            for (GameCharacter character : order) {
                // Setting up current character
                setCurrentCharacter(character);

                boolean skipAction = false;

                battleReport();

                System.out.println();
                System.out.println("\u001B[34mIts " + currentCharacter.getName() + "'s turn...\u001B[0m");
                System.out.println();

                // Check for status effects
                currentCharacter.checkForStatusEffect();

                // Check for little Easter egg:
                if (currentCharacter instanceof Walt) {
                    if (((Walt) currentCharacter).takeNap()) {
                        skipAction = true;
                    }
                }

                // Player picks action or computer picks action.
                if (    !currentCharacter.isDead() &&
                        !currentCharacter.isFrightened() &&
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

                // Checking to see if any characters need to be removed from characterOrder
                for (GameCharacter character2 : characterOrderManager.getOrder()) {
                    if (character2.isDead()) {
                        CharacterOrderManager.remove(character2);
                    }
                }

                // Checking to see if all enemies are defeated and battle should end
                if (currentEnemy.isEmpty()) {
                    System.out.println("\nAll enemies in this round have been defeated!");
                    System.out.println();

                    allEnemiesDefeated = true;

                    currentEnemy.loot(currentParty.getInventory());

                    int ADD_BATTLE = 1;
                    game.setNumOfBattles(ADD_BATTLE);

                    System.out.println("\n\u001B[1mPress ENTER to continue\u001B[0m");
                    scanner.nextLine();
                }

                // End battle if all enemies are defeated
                if (allEnemiesDefeated) {
                    characterOrderManager.clear();
                    break;
                }

                // Checking to see if all heroes are defeated and battle should end
                if (game.getHeroParty().isEmpty()) {
                    System.out.println();
                    System.out.println("All heroes have been defeated! The dark forces have prevailed.");
                    game.gameOver = true;
                }
                // End battle if all heroes are defeated
                if (game.gameOver) {
                    characterOrderManager.clear();
                    cooldownManager.clear();
                    break;
                }
            }


            // When all characters have taken their turn, END ROUND
            // Updating cooldowns
            cooldownManager.updateCooldowns();
            removeRedundantStatusEffects();

            // Back to the first character by copying updated characterorder
            order = new ArrayList<>(characterOrderManager.getOrder());

            System.out.println("\n\u001B[1mPress ENTER to continue\u001B[0m");
            scanner.nextLine();
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
    }

    private boolean isComputerPlayer() {
        return game.gameMode.equals(GameMode.COMPUTER_VS_COMPUTER)
                || (game.gameMode.equals(GameMode.PLAYER_VS_COMPUTER)
                && currentParty.equals(game.getMonsterParty()));
    }

    private void removeRedundantStatusEffects() {
        ArrayList<GameCharacter> effectsToRemove = new ArrayList<>();

        for (GameCharacter character : characterOrderManager.getOrder()) {
            if (character.hasEffect()) {
                if (!character.getEffect().isOnCooldown()) {
                    effectsToRemove.add(character);
                }
            }
        }

        for (GameCharacter character : effectsToRemove) {
            character.setEffect(null);
        }
    }

    // Getters
    public static CooldownManager getCooldownManager() {
        return cooldownManager;
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
