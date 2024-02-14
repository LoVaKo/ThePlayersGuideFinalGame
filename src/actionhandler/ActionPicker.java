package actionhandler;

import attacks.effect.EffectAttack;
import gamecharacters.GameCharacter;
import gamecharacters.Party;
import inventory.Inventory;
import inventory.InventoryItem;
import inventory.equippables.armor.Armor;
import inventory.equippables.jewelry.Jewelry;
import inventory.equippables.weapons.Weapon;
import statuseffects.Blinded;

import java.util.Random;

public class ActionPicker {
    private final GameCharacter currentCharacter;
    private final Party currentParty;
    private final Random random;

    public ActionPicker(GameCharacter currentCharacter, Party currentParty) {
        this.currentCharacter = currentCharacter;
        this.currentParty = currentParty;
        this.random = new Random();
    }

    public void pickAction() {

        if (shouldUseHealthPotion()) {
            currentCharacter.useHealthPotion();
        } else if (shouldEquipGear()) {
            currentCharacter.equipGearComputer();
        } else if (shouldUseGearAttack()) {
            currentCharacter.gearBasedAttackComputer();
        } else if (shouldUseSpecialAttack()) {
            currentCharacter.specialAttackComputer();
        } else if (shouldUseStandardAttack()) {
            currentCharacter.standardAttackComputer();
        } else {
            currentCharacter.doNothing();
        }
    }

    private boolean shouldUseHealthPotion() {
        int chanceToUseHealthPotion = 25;
        return currentCharacter.getCurrentHP() <= currentCharacter.getStartingHP() / 2
                && currentParty.getInventory().hasHealthPotion()
                && random.nextInt(100) + 1 <= chanceToUseHealthPotion;
    }

    private boolean shouldEquipGear() {
        Inventory inventory = currentCharacter.getOwnParty().getInventory();

        // Check if there is gear in party inventory, else return
        if (!inventory.hasGear()) return false;

        // Roll for 75 % chance to equip gear
        int chanceToEquipGear = 75;
        boolean succesfullRoll = random.nextInt(100) + 1 < chanceToEquipGear;

        if (!succesfullRoll) return false;

        // Check if the player inventory has already equipped gear of this type
        // If not, return true
        // If the level of the current item is lower, return true

        for (InventoryItem item : inventory.getItems()) {
            if (item instanceof Weapon) {
                if (!currentCharacter.getEquippedItems().hasWeapon() ||
                        currentCharacter.getEquippedItems().getWeapon().getLevel() < ((Weapon) item).getLevel()
                                && currentCharacter.getPreferredWeaponType() == ((Weapon) item).getType()) {
                    return true;
                }
            }

            if (item instanceof Armor) {
                if (!currentCharacter.getEquippedItems().hasArmor() ||
                        currentCharacter.getEquippedItems().getArmor().getLevel() < ((Armor) item).getLevel()) {
                    return true;
                }
            }

            if (item instanceof Jewelry) {
                if (!currentCharacter.getEquippedItems().hasJewelry() ||
                        currentCharacter.getEquippedItems().getJewelry().getLevel() < ((Jewelry) item).getLevel()) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean shouldUseGearAttack() {
        if (currentCharacter.getEquippedItems().getWeapon() != null) {
            if (currentCharacter.getEquippedItems().getWeapon().getAttack() instanceof EffectAttack selectedAttack) {
                if (selectedAttack.isOnCooldown()) return false;
            }
        }


        return currentCharacter.getEquippedItems().hasWeapon()
                && currentCharacter.getAttack1().MAX_DAMAGE <= currentCharacter.getEquippedItems().getWeapon().getAttack().MAX_DAMAGE
                && !(currentCharacter.getEffect() instanceof Blinded);
    }

    private boolean shouldUseSpecialAttack() {

        return !(currentCharacter.getEffect() instanceof Blinded) &&
                currentCharacter.getAttack2() != null &&
                !currentCharacter.getAttack2().isOnCooldown();
    }

    private boolean shouldUseStandardAttack() {
        return !(currentCharacter.getEffect() instanceof Blinded);
    }
}
