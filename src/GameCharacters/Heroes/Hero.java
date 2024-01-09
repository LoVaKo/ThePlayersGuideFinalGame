package GameCharacters.Heroes;

import Attacks.Attack;
import Defenses.Defense;
import GameCharacters.GameCharacter;
import Inventory.HealthPotion;
import Main.Party;

import java.util.Scanner;

public abstract class Hero extends GameCharacter {
    private static final Party heroParty = new Party();

    public Hero(String name, Attack attack, Defense defense, int hP) {
        super(name,
                attack,
                defense,
                heroParty,
                GameCharacters.Monsters.Monster.getMonsterParty(),
                hP);

        heroParty.addCharacter(this);
    }

    public static Party getHeroParty() {
        return heroParty;
    }

    public static void setupHeroParty() {
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
}
