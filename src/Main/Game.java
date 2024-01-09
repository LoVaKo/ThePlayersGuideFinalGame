package Main;

import java.util.Scanner;

import static GameCharacters.Heroes.Hero.setupHeroParty;
import static GameCharacters.Monsters.Monster.setupMonsterParty;

public class Game {
    private static final Scanner scanner = new Scanner(System.in);
    private final Party heroParty = GameCharacters.Heroes.Hero.getHeroParty();
    private final Party monsterParty = GameCharacters.Monsters.Monster.getMonsterParty();
    public boolean gameOver = false;
    public GameMode gameMode;
    private int numOfBattles = 0;


    // Constructor
    public Game(GameMode gameMode) {
        this.gameMode = gameMode;
    }

    public static void main(String[] args) {
        // Setting GameMode
        Game game = new Game(pickGameMode());
        setupHeroParty();
        game.execute();

    }

    public static GameMode pickGameMode() {
        System.out.println("""
                Welcome to the final battle. Pick a game mode:
                 1. Player VS Computer
                 2. Computer VS Computer
                 3. Player VS Player""");

        // Receiving user input
        int pickedMode = -1;

        while (pickedMode == -1) {
            if (scanner.hasNextInt()) {
                int userInput = scanner.nextInt();
                scanner.nextLine();
                if (userInput >= 1 && userInput <= 3) {
                    pickedMode = userInput;
                } else {
                    System.out.println("Invalid input. Please pick 1, 2 or 3.");
                }
            } else {
                System.out.println("Invalid input. Please pick 1, 2 or 3.");
            }
        }

        // Resolving user input
        switch (pickedMode) {
            case 1 -> {
                return GameMode.PLAYER_VS_COMPUTER;
            }
            case 2 -> {
                return GameMode.COMPUTER_VS_COMPUTER;
            }
            case 3 -> {
                return GameMode.PLAYER_VS_PLAYER;
            }
        }
        return GameMode.PLAYER_VS_COMPUTER;
    }

    public void execute() {
        int numOfHeroes = heroParty.getCharacters().size();

        while (!gameOver) {
            // Setup monster party
            setupMonsterParty(numOfHeroes, this);

            // Run battle
            Battle Battle = new Battle(this);
            Battle.execute();

            // End game if heroes were defeated in the last round OR boss has been defeated
            if (numOfBattles == 3 && monsterParty.isEmpty()) {
                System.out.println();
                System.out.println("All enemies have finally been defeated! The heroes win.");
                gameOver = true;
            }
            if (gameOver) break;

        }
    }

    // Getters
    public Party getHeroParty() {
        return heroParty;
    }

    public Party getMonsterParty() {
        return monsterParty;
    }

    public int getNumOfBattles() {
        return numOfBattles;
    }

    // Main Game mechanics
    public void setNumOfBattles(int numberToAdd) {
        this.numOfBattles += numberToAdd;
    }


}
