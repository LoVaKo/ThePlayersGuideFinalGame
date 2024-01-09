package Main;

import GameCharacters.*;
import Inventory.Dagger;
import Inventory.HealthPotion;

import java.util.Scanner;

public class Game {
    private final Party heroParty = new Party();
    private final Party monsterParty = new Party();
    public boolean gameOver = false;
    public GameMode gameMode;
    private int numOfBattles = 0;


    // Constructor
    public Game(GameMode gameMode) {
        this.gameMode = gameMode;
    }

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        // Setting GameMode
        GameMode gameMode;
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
            default -> gameMode = GameMode.PLAYER_VS_COMPUTER;
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
                System.out.println("The Uncoded One has finally been defeated! The heroes win.");
                game.gameOver = true;
            }
            if (game.gameOver) break;

        }

    }

    // Main Game mechanics
    public void setupHeroParty() {
        Scanner scanner = new Scanner(System.in);

        // Printing out available heroes
        System.out.println(new Walt(heroParty, monsterParty).printCharacterInformation());
        System.out.println(new VinFletcher(heroParty, monsterParty).printCharacterInformation());

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

            while (chosenHeroNum < 0 || chosenHeroNum > 3) {
                if (scanner.hasNextInt() &&
                        scanner.nextInt() > 0 && scanner.nextInt() < 3) {
                    chosenHeroNum = scanner.nextInt();
                    scanner.nextLine();
                } else {
                    System.out.println("Please pick a number from the character list.");
                }
            }

            switch (chosenHeroNum) {
                case 1 -> chosenHero = new Walt(heroParty, monsterParty);
                case 2 -> chosenHero = new VinFletcher(heroParty, monsterParty);
            }

            heroParty.addCharacter(chosenHero);
            assert chosenHero != null;
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
        switch (numOfHeroes) {
            case 1:
                if (this.numOfBattles == 0) {
                    // Adding characters
                    Skeleton skeleton1 = new Skeleton(monsterParty, heroParty);
                    monsterParty.addCharacter(skeleton1);

                    // Equipping gear
                    skeleton1.getEquippedItems().addItem(new Dagger());

                    // Adding inventory items
                    monsterParty.addInventoryItem(new HealthPotion());

                } else if (this.numOfBattles == 1) {
                    // Adding monsters
                    monsterParty.addCharacter(new Skeleton(monsterParty, heroParty));
                    monsterParty.addCharacter(new Skeleton(monsterParty, heroParty));

                    // Adding inventoryItems
                    monsterParty.addInventoryItem(new HealthPotion());
                    monsterParty.addInventoryItem(new Dagger());
                    monsterParty.addInventoryItem(new Dagger());

                } else if (this.numOfBattles == 2) {
                    // Adding end boss
                    monsterParty.addCharacter(new TheUncodedOne(monsterParty, heroParty));

                    // Adding inventoryitems
                    monsterParty.addInventoryItem(new HealthPotion());
                }
                break;
            case 2:
                if (this.numOfBattles == 0) {
                    // Adding characters
                    Skeleton skeleton1 = new Skeleton(monsterParty, heroParty);
                    monsterParty.addCharacter(new Skeleton(monsterParty, heroParty));
                    monsterParty.addCharacter(skeleton1);

                    // Equipping gear
                    skeleton1.getEquippedItems().addItem(new Dagger());

                    // Adding inventory items
                    monsterParty.addInventoryItem(new HealthPotion());

                } else if (this.numOfBattles == 1) {
                    // Adding monsters
                    monsterParty.addCharacter(new Skeleton(monsterParty, heroParty));
                    monsterParty.addCharacter(new Skeleton(monsterParty, heroParty));
                    monsterParty.addCharacter(new Skeleton(monsterParty, heroParty));

                    // Adding inventoryItems
                    monsterParty.addInventoryItem(new HealthPotion());
                    monsterParty.addInventoryItem(new HealthPotion());
                    monsterParty.addInventoryItem(new Dagger());
                    monsterParty.addInventoryItem(new Dagger());
                    monsterParty.addInventoryItem(new Dagger());

                } else if (this.numOfBattles == 2) {
                    // Adding end boss
                    monsterParty.addCharacter(new TheUncodedOne(monsterParty, heroParty));
                    monsterParty.addCharacter(new Skeleton(monsterParty, heroParty));

                    // Adding inventoryitems
                    monsterParty.addInventoryItem(new HealthPotion());
                    monsterParty.addInventoryItem(new HealthPotion());
                    monsterParty.addInventoryItem(new Dagger());
                }
                break;
            case 3:
                if (this.numOfBattles == 0) {
                    // Adding characters
                    Skeleton skeleton1 = new Skeleton(monsterParty, heroParty);
                    monsterParty.addCharacter(new Skeleton(monsterParty, heroParty));
                    monsterParty.addCharacter(new Skeleton(monsterParty, heroParty));
                    monsterParty.addCharacter(skeleton1);

                    // Equipping gear
                    skeleton1.getEquippedItems().addItem(new Dagger());

                    // Adding inventory items
                    monsterParty.addInventoryItem(new HealthPotion());

                } else if (this.numOfBattles == 1) {
                    // Adding monsters
                    monsterParty.addCharacter(new Skeleton(monsterParty, heroParty));
                    monsterParty.addCharacter(new Skeleton(monsterParty, heroParty));
                    monsterParty.addCharacter(new Skeleton(monsterParty, heroParty));
                    monsterParty.addCharacter(new Skeleton(monsterParty, heroParty));

                    // Adding inventoryItems
                    monsterParty.addInventoryItem(new HealthPotion());
                    monsterParty.addInventoryItem(new HealthPotion());
                    monsterParty.addInventoryItem(new Dagger());
                    monsterParty.addInventoryItem(new Dagger());
                    monsterParty.addInventoryItem(new Dagger());
                    monsterParty.addInventoryItem(new Dagger());

                } else if (this.numOfBattles == 2) {
                    // Adding end boss
                    monsterParty.addCharacter(new TheUncodedOne(monsterParty, heroParty));
                    monsterParty.addCharacter(new Skeleton(monsterParty, heroParty));
                    monsterParty.addCharacter(new Skeleton(monsterParty, heroParty));

                    // Adding inventoryitems
                    monsterParty.addInventoryItem(new HealthPotion());
                    monsterParty.addInventoryItem(new HealthPotion());
                    monsterParty.addInventoryItem(new Dagger());
                    monsterParty.addInventoryItem(new Dagger());
                }
                break;
            case 4:
                if (this.numOfBattles == 0) {
                    // Adding characters
                    Skeleton skeleton1 = new Skeleton(monsterParty, heroParty);
                    monsterParty.addCharacter(new Skeleton(monsterParty, heroParty));
                    monsterParty.addCharacter(new Skeleton(monsterParty, heroParty));
                    monsterParty.addCharacter(new Skeleton(monsterParty, heroParty));
                    monsterParty.addCharacter(skeleton1);

                    // Equipping gear
                    skeleton1.getEquippedItems().addItem(new Dagger());

                    // Adding inventory items
                    monsterParty.addInventoryItem(new HealthPotion());

                } else if (this.numOfBattles == 1) {
                    // Adding monsters
                    monsterParty.addCharacter(new Skeleton(monsterParty, heroParty));
                    monsterParty.addCharacter(new Skeleton(monsterParty, heroParty));
                    monsterParty.addCharacter(new Skeleton(monsterParty, heroParty));
                    monsterParty.addCharacter(new Skeleton(monsterParty, heroParty));
                    monsterParty.addCharacter(new Skeleton(monsterParty, heroParty));

                    // Adding inventoryItems
                    monsterParty.addInventoryItem(new HealthPotion());
                    monsterParty.addInventoryItem(new HealthPotion());
                    monsterParty.addInventoryItem(new Dagger());
                    monsterParty.addInventoryItem(new Dagger());
                    monsterParty.addInventoryItem(new Dagger());
                    monsterParty.addInventoryItem(new Dagger());
                    monsterParty.addInventoryItem(new Dagger());


                } else if (this.numOfBattles == 2) {
                    // Adding end boss
                    monsterParty.addCharacter(new TheUncodedOne(monsterParty, heroParty));
                    monsterParty.addCharacter(new Skeleton(monsterParty, heroParty));
                    monsterParty.addCharacter(new Skeleton(monsterParty, heroParty));
                    monsterParty.addCharacter(new Skeleton(monsterParty, heroParty));

                    // Adding inventoryitems
                    monsterParty.addInventoryItem(new HealthPotion());
                    monsterParty.addInventoryItem(new HealthPotion());
                    monsterParty.addInventoryItem(new Dagger());
                    monsterParty.addInventoryItem(new Dagger());
                    monsterParty.addInventoryItem(new Dagger());
                }
                break;
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
