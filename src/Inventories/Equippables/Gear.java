package Inventories.Equippables;

import GameCharacters.GameCharacter;
import Inventories.Equippables.Armor.Armor;
import Inventories.Equippables.Jewelry.Jewelry;
import Inventories.Equippables.Weapons.Weapon;
import Inventories.Inventory;
import Inventories.InventoryItem;

public abstract class Gear implements InventoryItem {
    String name;
    int level;

    public Gear(String name, int level) {
        this.name = name;
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public void equip(GameCharacter character) {

        // Check if character has already equipped an item of this type, if so move that item to party inventory
        switch (this) {
            case Weapon weapon -> {
                if (character.getEquippedItems().hasWeapon()) {
                    Weapon currentWeapon = character.getEquippedItems().getWeapon();
                    character.addToPartyInventory(currentWeapon);
                    System.out.println(currentWeapon.toString() + " has been moved to party inventory.");
                }
                character.getEquippedItems().setWeapon(weapon);
            }
            case Jewelry jewelry -> {
                if (character.getEquippedItems().hasJewelry()) {
                    Jewelry currentJewelry = character.getEquippedItems().getJewelry();
                    character.addToPartyInventory(currentJewelry);
                    System.out.println(currentJewelry.toString() + " has been moved to party inventory.");
                }
                character.getEquippedItems().setJewelry(jewelry);
            }
            case Armor armor -> {
                if (character.getEquippedItems().hasArmor()) {
                    Armor currentArmor = character.getEquippedItems().getArmor();
                    character.addToPartyInventory(currentArmor);
                    System.out.println(currentArmor.toString() + " has been moved to party inventory.");
                }
                character.getEquippedItems().setArmor(armor);
            }
            default -> throw new IllegalStateException("Unexpected value: " + this);
        }

        System.out.println(character + " has equipped " + this.name);

        // Remove equipped item from party inventory
        character.removeFromPartyInventory(this);
    }

    public void equipUponCreation(GameCharacter character) {
        switch (this) {
            case Weapon weapon -> character.getEquippedItems().setWeapon(weapon);
            case Jewelry jewelry -> character.getEquippedItems().setJewelry(jewelry);
            case Armor armor -> character.getEquippedItems().setArmor(armor);
            default -> {
            }
        }
    }

    @Override
    public void use(GameCharacter character, Inventory inventory) {
        equip(character);
    }

    @Override
    public String toString() {
        return this.name;
    }


}
