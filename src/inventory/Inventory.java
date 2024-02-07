package inventory;

import inventory.equippables.Gear;
import inventory.usables.HealthPotion;

import java.util.ArrayList;

public class Inventory {
    private final ArrayList<InventoryItem> inventoryItems = new ArrayList<>();

    public Inventory() {

    }

    public void addItem(InventoryItem item) {
        inventoryItems.add(item);
    }

    public ArrayList<InventoryItem> getItems() {
        return inventoryItems;
    }

    public boolean hasHealthPotion() {
        for (InventoryItem item : inventoryItems) {
            if (item instanceof HealthPotion) return true;
        }
        return false;
    }

    public boolean hasGear() {
        for (InventoryItem item : inventoryItems) {
            if (item instanceof Gear) return true;
        }
        return false;
    }
}
