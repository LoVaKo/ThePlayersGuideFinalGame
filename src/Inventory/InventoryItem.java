package Inventory;

import GameCharacters.GameCharacter;

public interface InventoryItem {
    void useItem(GameCharacter character, Inventory inventory);
}
