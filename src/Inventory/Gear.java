package Inventory;

import Attacks.Attack;
import GameCharacters.GameCharacter;

public abstract class Gear implements InventoryItem {
    String name;
    Attack attack;

    public Gear(String name, Attack attack) {
        this.name = name;
        this.attack = attack;
    }

    public void equipItem(GameCharacter character, Inventory partyInventory) {

        // Check if character has already equipped an item, if so move that item to party inventory
        if (character.isEquipped()) {
            InventoryItem equippedItem = character.getEquippedItems().getItems().get(0);
            character.getEquippedItems().getItems().remove(equippedItem);
            partyInventory.getItems().add(equippedItem);
            System.out.println(equippedItem + " has been moved to party inventory.");
        }

        // Equip chosen item and remove from party inventory.
        character.getEquippedItems().getItems().add(this);
        partyInventory.getItems().remove(this);

        System.out.println(character + " has equipped " + this.name);
    }

    @Override
    public void useItem(GameCharacter character, Inventory inventory) {
        equipItem(character, inventory);
    }

    @Override
    public String toString() {
        return this.name;
    }

    public Attack getAttack() {
        return attack;
    }
}
