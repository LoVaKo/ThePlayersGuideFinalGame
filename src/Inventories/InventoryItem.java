package Inventories;

import GameCharacters.GameCharacter;

public interface InventoryItem {
    void use(GameCharacter character, Inventory inventory);
}
