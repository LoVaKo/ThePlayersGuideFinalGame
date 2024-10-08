package gamecharacters.heroes;

import attacks.Attack;
import defenses.Defense;
import gamecharacters.GameCharacter;
import gamecharacters.Party;
import inventory.equippables.weapons.WeaponType;
import inventory.usables.HealthPotion;

import java.util.Scanner;

public abstract class Hero extends GameCharacter {
    private static final Party heroParty = new Party();

    public Hero(String name, Attack attack1, Attack attack2, Attack attack3, Defense defense, int hP, WeaponType preferredWeaponType) {
        super(name,
                attack1,
                attack2,
                attack3,
                defense,
                heroParty,
                gamecharacters.monsters.Monster.getMonsterParty(),
                hP,
                preferredWeaponType);

        heroParty.addCharacter(this);
    }

    public static Party getHeroParty() {
        return heroParty;
    }

    public static void setupHeroParty() {
        Scanner scanner = new Scanner(System.in);

        // Printing out available heroes
        Knight.printCharacterInformation();
        Archer.printCharacterInformation();
        Mage.printCharacterInformation();

        // Adding heroes to party
        boolean finishedAdding = false;

        while (heroParty.getCharacters().size() < 5 && !finishedAdding) {
            System.out.println("""
                    \nPlease choose a hero to add to your party:
                    1. Knight
                    2. Archer
                    3. Mage
                    """);
            GameCharacter chosenHero = null;
            int chosenHeroNum = -1;

            while (chosenHeroNum < 0) {
                if (scanner.hasNextInt()) {
                    chosenHeroNum = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character

                    if (chosenHeroNum >= 1 && chosenHeroNum <= 3) {
                        // Process the chosen hero
                        if (chosenHeroNum == 1) {
                            System.out.println("What is the Knight's name?");
                            String name = scanner.nextLine();
                            chosenHero = new Knight(name);
                        } else if (chosenHeroNum == 2) {
                            System.out.println("What is the Archer's name?");
                            String name = scanner.nextLine();
                            chosenHero = new Archer(name);
                        } else if (chosenHeroNum == 3) {
                            System.out.println("What is the Mage's name?");
                            String name = scanner.nextLine();
                            chosenHero = new Mage(name);
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
