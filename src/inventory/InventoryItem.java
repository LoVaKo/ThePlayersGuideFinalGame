package inventory;

import gamecharacters.GameCharacter;

public interface InventoryItem {
    void use(GameCharacter character, Inventory inventory);
}
