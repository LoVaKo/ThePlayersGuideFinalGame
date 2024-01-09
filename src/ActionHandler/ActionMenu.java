package ActionHandler;

import GameCharacters.GameCharacter;
import Inventory.Gear;

import java.util.ArrayList;
import java.util.Scanner;

public class ActionMenu {
    GameCharacter currentCharacter;
    ArrayList<Action> actionArrayList = new ArrayList<>();
    ArrayList<String> stringList = new ArrayList<>();
    ArrayList<String> printableList = new ArrayList<>();

    public ActionMenu(GameCharacter currentCharacter) {
        this.currentCharacter = currentCharacter;

        // Always add standard attack option
        // Exception: Status effect Blinded
        if (!currentCharacter.isBlinded()) {
            actionArrayList.add(currentCharacter::standardAttack);
            stringList.add("Standard attack: " + currentCharacter.getAttack().getName());
        }
        // If character has gear equipped and is NOT Blinded, add Gear based Attack option
        if (currentCharacter.isEquipped() &&
                !currentCharacter.isBlinded()) {
            Gear currentGear = (Gear) currentCharacter.getEquippedItems().getItems().get(0);
            actionArrayList.add(currentCharacter::gearBasedAttack);
            stringList.add(currentGear.toString() + " attack: " + currentGear.getAttack().toString());
        }

        // If party inventory has unused gear in it, add equip gear option
        if (currentCharacter.getOwnParty().getInventory().hasGear()) {
            actionArrayList.add(currentCharacter::equipGearPlayer);
            stringList.add("Equip Gear");
        }

        // if party inventory has items in it, add use item option
        if (!currentCharacter.getOwnParty().getInventory().getItems().isEmpty()) {
            actionArrayList.add(currentCharacter::useItem);
            stringList.add("Use item");
        }

        // Always conclude with option Do Nothing
        actionArrayList.add(currentCharacter::doNothing);
        stringList.add("Do nothing");

        // Convert stringList to printable list
        int LIST_NUMBERS = 1;

        for (String string : stringList) {
            printableList.add(LIST_NUMBERS + ". " + string);
            LIST_NUMBERS++;
        }
    }

    public void print() {
        System.out.println("Pick an action: ");
        for (String string : printableList) {
            System.out.println(string);
        }
    }

    public void pickAction() {
        Scanner scanner = new Scanner(System.in);
        int userInput;
        int action = -1;
        int NUMBER_OF_OPTIONS = actionArrayList.size();

        while (action == -1) {
            if (scanner.hasNextInt()) {
                userInput = scanner.nextInt();
                if (userInput > 0 && userInput <= NUMBER_OF_OPTIONS) {
                    action = userInput - 1; // Adjust to access index
                    scanner.nextLine();
                } else {
                    System.out.println("Invalid input. Please enter a number from the action menu.");
                    scanner.nextLine();
                }
            } else {
                System.out.println("Invalid input. Please enter a number from the action menu.");
                scanner.nextLine();
            }
        }
        actionArrayList.get(action).execute();
    }
}

