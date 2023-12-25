package GameCharacters;

import Attacks.Attack;
import Defenses.Defense;
import Inventory.Gear;
import Inventory.HealthPotion;
import Inventory.Inventory;
import Inventory.InventoryItem;
import Main.Party;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class GameCharacter {
    protected final String name;
    protected final Attack attack;
    protected final Defense defense;
    protected final Party ownParty;
    protected final int startingHP;
    protected Party enemyParty;
    protected int currentHP;
    protected Inventory equippedItems = new Inventory();
    private boolean isDead;


    // Constructor
    public GameCharacter(String name, Attack attack, Defense defense, Party ownParty, Party enemyParty, int hP) {
        this.name = name;
        this.attack = attack;
        this.defense = defense;
        this.ownParty = ownParty;
        this.enemyParty = enemyParty;
        this.startingHP = hP;
        this.currentHP = startingHP;
        this.isDead = false;

    }

    // ACTIONS
    public void standardAttack() {
        attack.useAttack(this, this.enemyParty, false);
    }

    public void gearBasedAttack() {
        this.getEquippedGear().getAttack().useAttack(this, this.enemyParty, false);
    }

    public void standardAttackComputer() {
        attack.useAttack(this, this.enemyParty, true);
    }

    public void gearBasedAttackComputer() {
        this.getEquippedGear().getAttack().useAttack(this, this.enemyParty, true);
    }

    public void doNothing() {
        System.out.println(this.getName() + " did nothing.");
    }

    public void useItem() {
        Scanner scanner = new Scanner(System.in);
        Inventory inventory = this.getOwnParty().getInventory();

        // Printing out current inventory
        System.out.println("Items in inventory: ");
        for (int i = 0; i < inventory.getItems().size(); i++) {
            int index = i + 1;
            System.out.println(index + ". " + inventory.getItems().get(i).toString());
        }

        // Picking item
        System.out.println("Which item would you like to use?");
        int userInput = scanner.nextInt();
        scanner.nextLine();

        inventory.getItems().get(userInput - 1).useItem(this, inventory); // userInput - 1 corresponds to the index in inventory

    }

    public void useHealthPotion() {
        new HealthPotion().useItem(this, this.getOwnParty().getInventory());
    }

    public void equipGearPlayer() {
        Scanner scanner = new Scanner(System.in);

        // Filtering inventory for instances of Gear
        ArrayList<InventoryItem> partyInventory = this.getOwnParty().getInventory().getItems();
        ArrayList<Gear> availableGear = new ArrayList<>();

        for (InventoryItem inventoryItem : partyInventory) {
            if (inventoryItem instanceof Gear gear) {
                availableGear.add(gear);
            }
        }
        // Printing out available gear:
        System.out.println("Available gear in party inventory:");
        for (int i = 0; i < availableGear.size(); i++) {
            int LIST_NUMBER = i + 1;
            System.out.println(LIST_NUMBER + ". " + availableGear.get(i).toString());
        }

        // Player picks item
        System.out.println("Which item would you like to equip?");
        int userInput = scanner.nextInt();
        scanner.nextLine();
        int indexOfItem = userInput - 1;
        Gear pickedGear = availableGear.get(indexOfItem);

        // Equip item
        pickedGear.equipItem(this, this.getOwnParty().getInventory());
    }

    public void equipGearComputer() {
        // Looking for inventory for the first instance of Gear
        ArrayList<InventoryItem> partyInventory = this.getOwnParty().getInventory().getItems();
        Gear pickedGear = null;

        for (InventoryItem inventoryItem : partyInventory) {
            if (inventoryItem instanceof Gear gear) {
                pickedGear = gear;
                break;
            }
        }
        assert pickedGear != null;
        pickedGear.equipItem(this, this.ownParty.getInventory());
    }

    public void lootCharacter(GameCharacter character) {
        Gear equippedGear = this.getEquippedGear();
        System.out.println(character.getName() + " has looted " + this.name + " and recieved: " + equippedGear.toString());
        character.getOwnParty().getInventory().getItems().add(equippedGear);
    }


    // Getters and setters
    public int getCurrentHP() {
        return currentHP;
    }

    public void setCurrentHP(int currentHP) {
        this.currentHP = currentHP;
    }

    public Defense getDefense() {
        return defense;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public boolean getIsDead() {
        return isDead;
    }

    public int getStartingHP() {
        return startingHP;
    }

    public String getName() {
        return name;
    }

    public Attack getAttack() {
        return attack;
    }

    public String getCharacterReport() {
        if (isEquipped()) {
            return getName() + " with " + equippedItems.getItems().get(0).toString() + " (" + getCurrentHP() + "/" + getStartingHP() + ")";
        }

        return getName() + "(" + getCurrentHP() + "/" + getStartingHP() + ")";
    }

    public Party getOwnParty() {
        return ownParty;
    }

    public Inventory getEquippedItems() {
        return equippedItems;
    }

    public Gear getEquippedGear() {
        return (Gear) equippedItems.getItems().get(0);
    }

    public boolean hasDefense() {
        return this.defense != null;
    }

    // Other
    public String printCharacterInformation() {
        return "";
    }

    @Override
    public String toString() {
        return this.name;
    }

    public boolean isEquipped() {
        return !equippedItems.getItems().isEmpty();
    }

}