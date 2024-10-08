package gamecharacters;

import actionhandler.ActionMenu;
import attacks.Attack;
import attacks.CooldownManager;
import defenses.Defense;
import inventory.Inventory;
import inventory.InventoryItem;
import inventory.equippables.Gear;
import inventory.equippables.GearHandler;
import inventory.equippables.armor.Armor;
import inventory.equippables.jewelry.Jewelry;
import inventory.equippables.weapons.Weapon;
import inventory.equippables.weapons.WeaponType;
import inventory.usables.HealthPotion;
import main.Battle;
import main.CharacterOrderManager;
import statuseffects.CoolDown;
import statuseffects.StatusEffect;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class GameCharacter {
    private final static CharacterOrderManager characterOrderManager = Battle.getCharacterOrderManager();
    protected final CooldownManager cooldownManager = new CooldownManager();
    protected final String name;
    protected final Attack attack1;
    protected final Attack attack2;
    protected final Attack attack3;
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
    public GameCharacter(String name, Attack attack1, Attack attack2, Attack attack3, Defense defense, Party ownParty, Party enemyParty, int hP, WeaponType preferredWeaponType) {
        this.name = name;
        this.attack1 = attack1;
        this.attack2 = attack2;
        this.attack3 = attack3;
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
    public void useAttack1() {
        attack1.useAttack(this, this.enemyParty, false);
    }

    public void useAttack2() {
        attack2.useAttack(this, this.enemyParty, false);
    }

    public void useAttack3() {
        attack3.useAttack(this, this.enemyParty, false);
    }

    public void useGearBasedAttack() {
        this.equippedItems.getWeapon().getAttack().useAttack(this, this.enemyParty, false);
    }

    public void useAttack1Computer() {
        attack1.useAttack(this, this.enemyParty, true);
    }

    public void useAttack2Computer() {
        attack2.useAttack(this, this.enemyParty, true);
    }

    public void useAttack3Computer() {
        attack3.useAttack(this, this.enemyParty, true);
    }

    public void useGearBasedAttackComputer() {
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
        ArrayList<InventoryItem> partyInventory = this.getOwnParty().getInventory().getItems();
        Gear pickedGear = null;
        boolean donePicking = false;

        // Determine the best possible options for each type of gear
        Weapon bestWeapon = getEquippedItems().getWeapon();
        Armor bestArmor = getEquippedItems().getArmor();
        Jewelry bestJewelry = getEquippedItems().getJewelry();

        for (InventoryItem inventoryItem : partyInventory) {
            if (inventoryItem instanceof Weapon weapon) {
                if (weapon.getType().equals(this.preferredWeaponType)) {
                    if (bestWeapon == null) {
                        bestWeapon = weapon;
                    } else if (bestWeapon.getLevel() < weapon.getLevel()) {
                        bestWeapon = weapon;
                    }
                }
            } else if (inventoryItem instanceof Armor armor) {
                if (bestArmor == null) {
                    bestArmor = armor;
                } else if (bestArmor.getLevel() < armor.getLevel()) {
                    bestArmor = armor;
                }
            } else if (inventoryItem instanceof Jewelry jewelry) {
                if (bestJewelry == null) {
                    bestJewelry = jewelry;
                } else if (bestJewelry.getLevel() < jewelry.getLevel()) {
                    bestJewelry = jewelry;
                }
            }
        }

        // Equip better weapon first, then better armor, then better jewelry
        if (bestWeapon != getEquippedItems().getWeapon()) {
            pickedGear = bestWeapon;
            donePicking = true;
        }
        if (bestArmor != getEquippedItems().getArmor()
                && !donePicking) {
            pickedGear = bestArmor;
            donePicking = true;
        }
        if (bestJewelry != getEquippedItems().getJewelry()
                && !donePicking) {
            pickedGear = bestJewelry;
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
            lootMessage.append(looter.getName()).append(" has looted ").append(this.name).append(" and received: ");

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

    public void dropLoot() {
        // Used when character is not directly killed by another character. For instance, because of a status effect or trap

        // Check if this character has any gear
        if (equippedItems.hasGear()) {
            StringBuilder lootMessage = new StringBuilder();
            lootMessage.append(getName()).append(" dropped the following items for it's party's inventory:");

            // now check for different types of gear
            if (equippedItems.hasWeapon()) {
                lootMessage.append("\n- ").append(equippedItems.getWeapon().toString());
                addToPartyInventory(equippedItems.getWeapon());
            }
            if (equippedItems.hasArmor()) {
                lootMessage.append("\n- ").append(equippedItems.getArmor().toString());
                addToPartyInventory(equippedItems.getArmor());
            }
            if (equippedItems.hasJewelry()) {
                lootMessage.append("\n- ").append(equippedItems.getJewelry().toString());
                addToPartyInventory(equippedItems.getJewelry());
            }

            // Print out loot message
            System.out.println(lootMessage);
        }
    }

    public void checkForStatusEffect() {
        if (this.hasEffect()) {
            if (!(this.getEffect() instanceof CoolDown)) {
                System.out.println(this.name + " is " + this.effect.getName() + "!");
                this.effect.execute(this);
                System.out.println("Remaining number of rounds: " + (this.effect.getActiveCounter() - 1));
                System.out.println();
            }
        }
    }

    public void resolveDeath() {
        setDead(true);
        ownParty.removeCharacter(this); // Remove character from party
        characterOrderManager.getOrder().remove(this); // Remove character from character order
        if (hasEffect()) getEffect().removeFromCooldown(); // Remove any active cooldowns from cooldown manager
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

    public WeaponType getPreferredWeaponType() {
        return preferredWeaponType;
    }

    public CooldownManager getCooldownManager() {
        return cooldownManager;
    }

    public int getStartingHP() {
        return startingHP;
    }

    public String getName() {
        return name;
    }

    public Attack getAttack1() {
        return attack1;
    }

    public Attack getAttack2() {
        return attack2;
    }

    public Attack getAttack3() {
        return attack3;
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


}