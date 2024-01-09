package Main;

import ActionHandler.ActionMenu;
import ActionHandler.ActionPicker;
import GameCharacters.GameCharacter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;

public class Battle {
    private final ArrayList<GameCharacter> characterOrder;
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
        this.characterOrder = determineCharacterOrder();
        this.currentCharacter = characterOrder.get(0);
        this.allEnemiesDefeated = false;
    }

    // Main.Battle Mechanics
    public void execute() {
        Iterator<GameCharacter> characterIterator = characterOrder.iterator();
        Scanner scanner = new Scanner(System.in);

        while (characterIterator.hasNext() && !allEnemiesDefeated && !game.gameOver) {

            // Setting up current character
            setCurrentCharacter(characterIterator.next());
            battleReport();
            System.out.println();
            System.out.println("Its " + getCurrentCharacter() + "'s turn...");
            getCurrentCharacter().checkForStatusEffect();

            // Player picks action or computer picks action.
            if (isComputerPlayer()) {
                ActionPicker actionPicker = new ActionPicker(currentCharacter, currentParty);
                actionPicker.pickAction();
            } else {
                ActionMenu menu = new ActionMenu(currentCharacter);
                menu.print();
                menu.pickAction();
            }

            // Checking to see if any characters need to be removed from characterOrder
            if (getCurrentCharacter().getIsDead()) {
                characterIterator.remove();
            }

            // If current character has a Status Effect, handle end round for status effects.
            if (currentCharacter.hasEffect()) {
                currentCharacter.getEffect().endRound(currentCharacter);
            }

            // Checking to see if all enemies are defeated and battle should end
            if (currentEnemy.isEmpty()) {
                System.out.println("\nAll enemies in this round have been defeated!");
                System.out.println();
                allEnemiesDefeated = true;

                currentEnemy.loot(currentParty.getInventory());

                int ADD_BATTLE = 1;
                game.setNumOfBattles(ADD_BATTLE);

                System.out.println("\nPress ENTER to continue");
                scanner.nextLine();
            }

            // End battle if all enemies are defeated
            if (allEnemiesDefeated) break;

            // Checking to see if all heroes are defeated and battle should end
            if (game.getHeroParty().isEmpty()) {
                System.out.println();
                System.out.println("All heroes have been defeated! The dark forces have prevailed.");
                game.gameOver = true;
            }
            // End battle if all heroes are defeated
            if (game.gameOver) break;

            // If all characters have taken their turn, back to the first character
            if (!characterIterator.hasNext()) {
                characterIterator = characterOrder.iterator();
            }

            System.out.println("\nPress ENTER to continue");
            scanner.nextLine();
        }
    }


    public ArrayList<GameCharacter> determineCharacterOrder() {
        ArrayList<GameCharacter> allCharacters = new ArrayList<>();
        allCharacters.addAll(game.getHeroParty().getCharacters());
        allCharacters.addAll(game.getMonsterParty().getCharacters());
        Collections.shuffle(allCharacters);
        return allCharacters;
    }

    public void battleReport() {
        System.out.println("===================== THE BATTLE =====================");
        for (GameCharacter character : game.getHeroParty().getCharacters()) {
            if (character.equals(currentCharacter)) {
                System.out.println("\u001B[33m" +
                        "[" + (getCharacterOrder().indexOf(character) + 1) + "] " +
                        character.getCharacterReport() + "\u001B[0m");
            } else {
                System.out.println("[" + (getCharacterOrder().indexOf(character) + 1) + "] " +
                        character.getCharacterReport());
            }
        }
        System.out.println("------------------------- VS -------------------------");
        for (GameCharacter character : game.getMonsterParty().getCharacters()) {
            if (character.equals(currentCharacter)) {
                System.out.println("\u001B[33m" +
                        "[" + (getCharacterOrder().indexOf(character) + 1) + "] " +
                        character.getCharacterReport() + "\u001B[0m");
            } else {
                System.out.println("[" + (getCharacterOrder().indexOf(character) + 1) + "] " +
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


    // Getters
    public ArrayList<GameCharacter> getCharacterOrder() {
        return characterOrder;
    }

    public void setCurrentParty(Party currentParty) {
        this.currentParty = currentParty;
    }

    public GameCharacter getCurrentCharacter() {
        return currentCharacter;
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

}
