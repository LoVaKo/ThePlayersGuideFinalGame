package gamecharacters;

import inventory.Inventory;
import inventory.InventoryItem;

import java.util.ArrayList;
import java.util.List;

public class Party {
    private final ArrayList<GameCharacter> characters = new ArrayList<>();
    private final Inventory inventory = new Inventory();


    public void addCharacter(GameCharacter character) {
        characters.add(character);
    }

    public void removeCharacter(GameCharacter character) {
        characters.remove(character);
    }

    public void addInventoryItem(InventoryItem item) {
        inventory.addItem(item);
    }

    public void loot(Inventory currentPartyInventory) {
        System.out.println("After looting the bodies, the following items have been transferred to your party inventory:");

        List<InventoryItem> itemsToTransfer = new ArrayList<>();

        for (InventoryItem item : this.inventory.getItems()) {
            System.out.println("- " + item.toString());
            itemsToTransfer.add(item);
        }

        for (InventoryItem item : itemsToTransfer) {
            currentPartyInventory.addItem(item);
            this.inventory.getItems().remove(item);
        }
    }

    // Getters and setters

    public GameCharacter getCharacter(int index) {
        return characters.get(index);
    }

    public ArrayList<GameCharacter> getCharacters() {
        return characters;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public boolean isEmpty() {
        return characters.isEmpty();
    }
}
