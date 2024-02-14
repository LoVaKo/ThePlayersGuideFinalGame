package actionhandler;

import attacks.Attack;
import attacks.basic.BasicAttack;
import attacks.effect.EffectAttack;
import gamecharacters.GameCharacter;
import statuseffects.Blinded;
import statuseffects.StatusEffect;

import java.util.ArrayList;
import java.util.Scanner;

public class ActionMenu {
    GameCharacter currentCharacter;
    StatusEffect currentEffect;
    ArrayList<Action> actionArrayList = new ArrayList<>();
    ArrayList<String> stringList = new ArrayList<>();
    ArrayList<String> printableList = new ArrayList<>();

    public ActionMenu(GameCharacter currentCharacter) {
        this.currentCharacter = currentCharacter;

        // Check for status effects
        boolean canAttack = true;

        if (currentCharacter.hasEffect()) {
            currentEffect = currentCharacter.getEffect();
            if (currentEffect instanceof Blinded) canAttack = false;
        }

        // If character CAN attack, check for all available attacks
        ArrayList<Attack> attackList = new ArrayList<>();
        attackList.add(currentCharacter.getAttack1());
        attackList.add(currentCharacter.getAttack2());
        attackList.add(currentCharacter.getAttack3());
        if (currentCharacter.getEquippedItems().hasWeapon()) {
            attackList.add(currentCharacter.getEquippedItems().getWeapon().getAttack());
        }

        if (canAttack) {
            for (Attack attack : attackList) {
                if (attack != null &&
                        !attack.isOnCooldown()) {

                    // Check for attackslot to add right action
                    int attackSlot = attack.getAttackSlot(currentCharacter);
                    switch (attackSlot) {
                        case 1 -> actionArrayList.add(currentCharacter::useAttack1);
                        case 2 -> actionArrayList.add(currentCharacter::useAttack2);
                        case 3 -> actionArrayList.add(currentCharacter::useAttack3);
                        case 4 -> actionArrayList.add(currentCharacter::useGearBasedAttack);
                    }

                    // Determine description
                    String attackType;
                    String attackName = attack.getName();
                    if (attack instanceof BasicAttack) {
                        attackType = "Basic Attack";
                    } else if (attack instanceof EffectAttack) {
                        attackType = "Effect Attack";
                    } else {
                        attackType = "Special Attack";
                    }

                    if (attackSlot == 4) {
                        attackType = currentCharacter.getEquippedItems().getWeapon().toString() + " attack";
                    }

                    stringList.add(attackType + ": " + attackName);
                }
            }
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
        System.out.println("Pick an action ");
        System.out.println("--------------------------------------");
        for (String string : printableList) {
            System.out.println(string);
        }
        System.out.println("--------------------------------------");
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

