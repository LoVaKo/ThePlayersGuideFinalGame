package GameCharacters;

import ActionHandler.ActionMenu;
import Attacks.Attack;
import Attacks.Basic.BasicAttack;
import Attacks.Special.SpecialAttack;
import Defenses.Defense;
import Inventories.Equippables.Gear;
import Inventories.Equippables.GearHandler;
import Inventories.Equippables.Weapons.WeaponType;
import Inventories.HealthPotion;
import Inventories.Inventory;
import Inventories.InventoryItem;
import Main.Party;
import StatusEffects.Blinded;
import StatusEffects.CoolDown;
import StatusEffects.Frightened;
import StatusEffects.StatusEffect;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class GameCharacter {
    protected final String name;
    protected final BasicAttack basicAttack;
    protected final SpecialAttack specialAttack;
    protected final WeaponType preferredWeaponType;
    protected final Defense defense;
    protected final Party ownParty;
    protected final int startingHP;
    private final GearHandler equippedItems = new GearHandler();
    protected Party enemyParty;
    protected int currentHP;
    protected StatusEffect effect;
    private boolean isDead;


    // Constructor
    public GameCharacter(String name, BasicAttack basicAttack, SpecialAttack specialAttack, Defense defense, Party ownParty, Party enemyParty, int hP, WeaponType preferredWeaponType) {
        this.name = name;
        this.basicAttack = basicAttack;
        this.specialAttack = specialAttack;
        this.defense = defense;
        this.ownParty = ownParty;
        this.enemyParty = enemyParty;
        this.startingHP = hP;
        this.currentHP = startingHP;
        this.isDead = false;
        this.effect = null;
        this.preferredWeaponType = preferredWeaponType;
    }

    // ACTIONS
    public void standardAttack() {
        basicAttack.useAttack(this, this.enemyParty, false);
    }

    public void specialAttack() {
        specialAttack.useAttack(this, this.enemyParty, false);
    }

    public void gearBasedAttack() {
        this.equippedItems.getWeapon().getAttack().useAttack(this, this.enemyParty, false);
    }

    public void standardAttackComputer() {
        basicAttack.useAttack(this, this.enemyParty, true);
    }

    public void gearBasedAttackComputer() {
        this.equippedItems.getWeapon().getAttack().useAttack(this, this.enemyParty, true);
    }

    public void doNothing() {
        System.out.println(this.getName() + " did nothing.");
    }

    public void useItem() {
        Scanner scanner = new Scanner(System.in);
        Inventory inventory = this.getOwnParty().getInventory();

        // Printing out current inventory
        System.out.println("Which item would you like to use?");
        System.out.println("Items in inventory: ");
        for (int i = 0; i < inventory.getItems().size(); i++) {
            int index = i + 1;
            System.out.println(index + ". " + inventory.getItems().get(i).toString());
        }
        System.out.println("0. Go Back");

        // Picking item
        int userInput = scanner.nextInt();
        scanner.nextLine();

        if (userInput == 0) {
            // If player picked 0, go back to ActionMenu
            ActionMenu menu = new ActionMenu(this);
            menu.print();
            menu.pickAction();
        } else {
            inventory.getItems().get(userInput - 1).use(this, inventory); // userInput - 1 corresponds to the index in inventory
        }
    }

    public void useHealthPotion() {
        new HealthPotion().use(this, this.getOwnParty().getInventory());
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
        pickedGear.equip(this);
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
        pickedGear.equip(this);
    }

    public void addToPartyInventory(InventoryItem item) {
        getOwnParty().getInventory().getItems().add(item);
    }

    public void removeFromPartyInventory(InventoryItem item) {
        getOwnParty().getInventory().getItems().remove(item);
    }

    public void lootCharacter(GameCharacter looter) {
        // Check if this character has any gear
        if (equippedItems.hasGear()) {
            StringBuilder lootMessage = new StringBuilder();
            lootMessage.append(looter.getName()).append(" has looted ").append(this.name).append(" and recieved: ");

            // now check for different types of gear
            if (equippedItems.hasWeapon()) {
                lootMessage.append("\n- ").append(equippedItems.getWeapon().toString());
                looter.addToPartyInventory(equippedItems.getWeapon());
            }
            if (equippedItems.hasArmor()) {
                lootMessage.append("\n- ").append(equippedItems.getArmor().toString());
                looter.addToPartyInventory(equippedItems.getArmor());
            }
            if (equippedItems.hasJewelry()) {
                lootMessage.append("\n- ").append(equippedItems.getJewelry().toString());
                looter.addToPartyInventory(equippedItems.getJewelry());
            }

            // Print out loot message
            System.out.println(lootMessage);
        }


    }

    // Status Effects
    public boolean isBlinded() {
        return this.effect instanceof Blinded;
    }

    public boolean isFrightened() {
        return this.effect instanceof Frightened;
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

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public int getStartingHP() {
        return startingHP;
    }

    public String getName() {
        return name;
    }

    public Attack getStandardAttack() {
        return basicAttack;
    }

    public SpecialAttack getSpecialAttack() {
        return specialAttack;
    }

    public String getCharacterReport() {
        StringBuilder report = new StringBuilder();

        report.append(getName()); // Name
        if (equippedItems.hasWeapon())
            report.append(" with ").append(equippedItems.getWeapon().toString()); // Equipped with
        report.append(" (").append(getCurrentHP()).append("/").append(getStartingHP()).append(")"); // HP
        if (hasEffect() && !(getEffect() instanceof CoolDown))
            report.append(" >").append(getEffect().getName()).append("<");

        return report.toString();
    }

    public Party getOwnParty() {
        return ownParty;
    }

    public GearHandler getEquippedItems() {
        return equippedItems;
    }

    public boolean hasDefense() {
        return this.defense != null;
    }

    public StatusEffect getEffect() {
        return effect;
    }

    public void setEffect(StatusEffect effect) {
        this.effect = effect;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public boolean hasEffect() {
        return this.effect != null;
    }

    public void checkForStatusEffect() {
        if (this.hasEffect()) {
            System.out.println(this.name + " is " + this.effect.getName() + "!");
            this.effect.apply(this);
            System.out.println("Remaining number of rounds: " + this.effect.getCounter());
            System.out.println();
        }
    }

}