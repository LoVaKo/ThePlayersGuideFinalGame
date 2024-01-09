package Main;

import GameCharacters.GameCharacter;
import GameCharacters.Heroes.VinFletcher;
import GameCharacters.Heroes.Walt;
import GameCharacters.Monsters.Skeleton;
import Inventory.Dagger;
import Inventory.HealthPotion;

import java.util.Scanner;

public class Game {
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
        Scanner scanner = new Scanner(System.in);

        // Setting GameMode
        GameMode gameMode = null;
        System.out.println("""
                Welcome to the final battle. Pick a game mode:
                 1. Player VS Computer
                 2. Computer VS Computer
                 3. Player VS Player""");

        int pickedMode = scanner.nextInt();
        scanner.nextLine();

        switch (pickedMode) {
            case 1 -> gameMode = GameMode.PLAYER_VS_COMPUTER;
            case 2 -> gameMode = GameMode.COMPUTER_VS_COMPUTER;
            case 3 -> gameMode = GameMode.PLAYER_VS_PLAYER;
        }

        System.out.println("Game mode set to: " + gameMode);

        Game game = new Game(gameMode);
        game.setupHeroParty();
        int numOfHeroes = game.heroParty.getCharacters().size();

        while (!game.gameOver) {
            game.setupMonsterParty(numOfHeroes);

            // Run battle
            Battle Battle = new Battle(game);
            Battle.execute();

            // End game if heroes were defeated in the last round OR boss has been defeated
            if (game.numOfBattles == 3 && game.monsterParty.isEmpty()) {
                System.out.println();
                System.out.println("All enemies have finally been defeated! The heroes win.");
                game.gameOver = true;
            }
            if (game.gameOver) break;

        }

    }

    // Main Game mechanics
    public void setupHeroParty() {
        Scanner scanner = new Scanner(System.in);

        // Printing out available heroes
        System.out.println(Walt.printCharacterInformation());
        System.out.println(VinFletcher.printCharacterInformation());

        // Adding heroes to party
        boolean finishedAdding = false;

        while (heroParty.getCharacters().size() < 5 && !finishedAdding) {
            System.out.println("""
                    \nPlease choose a hero to add to your party:
                    1. Walt
                    2. Vin Fletcher
                    """);
            GameCharacter chosenHero = null;
            int chosenHeroNum = -1;

            while (chosenHeroNum < 0) {
                if (scanner.hasNextInt()) {
                    chosenHeroNum = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character

                    if (chosenHeroNum >= 1 && chosenHeroNum <= 2) {
                        // Process the chosen hero
                        if (chosenHeroNum == 1) {
                            chosenHero = new Walt();
                        } else if (chosenHeroNum == 2) {
                            chosenHero = new VinFletcher();
                        }
                    } else {
                        System.out.println("Please pick a number from the character list.");
                        chosenHeroNum = -1;
                    }
                } else {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.nextLine();
                }
            }

            System.out.println(chosenHero.getName() + " has been added to the hero party!");

            System.out.println("\nCurrent party:");
            for (GameCharacter character : heroParty.getCharacters()) {
                System.out.println(character.getName());
            }

            if (heroParty.getCharacters().size() < 4) {
                System.out.println("\nWould you like to add another hero to your party? [y/n]");
                String userInput = scanner.nextLine();
                if (userInput.equalsIgnoreCase("n")) finishedAdding = true;
            }
        }

        //Adding inventory items and gear
        heroParty.addInventoryItem(new HealthPotion());
        heroParty.addInventoryItem(new HealthPotion());
        heroParty.addInventoryItem(new HealthPotion());
    }

    public void setupMonsterParty(int numOfHeroes) {
        int numOfSkeletons = 0;
        int numOfHealthPotions = 0;
        int numOfDaggers = 0;

        switch (this.numOfBattles) {
            case 0:
                numOfSkeletons = numOfHeroes;
                numOfHealthPotions = 1;
                numOfDaggers = numOfHeroes;
                break;
            case 1:
                numOfSkeletons = (int) Math.ceil(numOfHeroes * 1.5);
                numOfHealthPotions = 2;
                numOfDaggers = numOfSkeletons;
                break;
            case 2:
                numOfSkeletons = numOfHeroes * 2;
                numOfHealthPotions = 3;
                numOfDaggers = numOfSkeletons;
                break;
            default:
                // Handle default case if needed
                break;
        }

        // Adding monsters
        for (int i = 0; i < numOfSkeletons; i++) {
            new Skeleton();
        }

        // Adding Health potions
        for (int i = 0; i < numOfHealthPotions; i++) {
            monsterParty.addInventoryItem(new HealthPotion());
        }

        // Adding Daggers
        for (int i = 0; i < numOfDaggers; i++) {
            monsterParty.addInventoryItem(new Dagger());
        }
    }

    public void setNumOfBattles(int numberToAdd) {
        this.numOfBattles += numberToAdd;
    }

    // Getters
    public Party getHeroParty() {
        return heroParty;
    }

    public Party getMonsterParty() {
        return monsterParty;
    }

}
