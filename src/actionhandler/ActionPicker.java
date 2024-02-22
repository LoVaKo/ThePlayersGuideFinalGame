package actionhandler;

import attacks.Attack;
import attacks.effect.EffectAttack;
import gamecharacters.GameCharacter;
import gamecharacters.Party;
import inventory.Inventory;
import inventory.InventoryItem;
import inventory.equippables.armor.Armor;
import inventory.equippables.jewelry.Jewelry;
import inventory.equippables.weapons.Weapon;
import statuseffects.Blinded;
import statuseffects.Frightened;
import statuseffects.Frozen;
import statuseffects.StatusEffect;

import java.util.ArrayList;
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
        } else if (shouldAttack()) {
            Attack bestAttack = pickBestAttack();

            int attackSlot = bestAttack.getAttackSlot(currentCharacter);

            switch (attackSlot) {
                case 1 -> currentCharacter.useAttack1Computer();
                case 2 -> currentCharacter.useAttack2Computer();
                case 3 -> currentCharacter.useAttack3Computer();
                case 4 -> currentCharacter.useGearBasedAttackComputer();
            }

        } else {
            currentCharacter.doNothing();
        }
    }

    private Attack pickBestAttack() {
        ArrayList<Attack> allAttacks = new ArrayList<>();
        Attack attack1 = currentCharacter.getAttack1();
        Attack attack2 = currentCharacter.getAttack2();
        Attack attack3 = currentCharacter.getAttack3();
        Attack gearAttack = null;

        if (currentCharacter.getEquippedItems().hasWeapon()) {
            gearAttack = currentCharacter.getEquippedItems().getWeapon().getAttack();
        }

        if (attack1 != null && !attack1.isOnCooldown()) allAttacks.add(attack1);
        if (attack2 != null && !attack2.isOnCooldown()) allAttacks.add(attack2);
        if (attack3 != null && !attack3.isOnCooldown()) allAttacks.add(attack3);
        if (gearAttack != null && !gearAttack.isOnCooldown()) allAttacks.add(gearAttack);


        return getBestAttack(allAttacks);
    }

    private Attack getBestAttack(ArrayList<Attack> availableAttacks) {
        Attack bestAttack = currentCharacter.getAttack1();
        for (Attack attack : availableAttacks) {
            if (attack != null) {
                // Prefer attacks with effect over attacks without effect
                if (attack instanceof EffectAttack) {
                    if (!(bestAttack instanceof EffectAttack)) {
                        bestAttack = attack;
                    } else if (attack.MAX_DAMAGE > bestAttack.MAX_DAMAGE) {
                        bestAttack = attack;
                    }
                } else {
                    if (!(bestAttack instanceof EffectAttack)
                            && attack.MAX_DAMAGE > bestAttack.MAX_DAMAGE) {
                        bestAttack = attack;
                    }
                }
            }
        }
        return bestAttack;
    }

    private boolean shouldAttack() {
        StatusEffect currentEffect = null;
        if (currentCharacter.hasEffect()) currentEffect = currentCharacter.getEffect();

        return !(currentEffect instanceof Frightened)
                && !(currentEffect instanceof Frozen)
                && !(currentEffect instanceof Blinded);
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

}
