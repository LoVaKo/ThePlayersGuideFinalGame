package Inventories;

import GameCharacters.GameCharacter;

public class HealthPotion implements InventoryItem {

    @Override
    public void use(GameCharacter character, Inventory inventory) {
        // Add 10 healthpoints to the characters current HP OR set to max.
        character.setCurrentHP(Math.min((character.getCurrentHP() + 10), character.getStartingHP()));
        System.out.println(character + " used a Health Potion");
        System.out.println(character + "'s health is now " + character.getCurrentHP() + "/" + character.getStartingHP());

        // Remove potion from inventory
        inventory.inventoryItems.remove(this);
    }

    @Override
    public String toString() {
        return "Health Potion";
    }
}