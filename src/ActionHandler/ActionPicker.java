package ActionHandler;

import GameCharacters.GameCharacter;
import Main.Party;

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
        int chanceToEquipGear = 50;
        return !currentCharacter.isEquipped()
                && currentCharacter.getOwnParty().getInventory().hasGear()
                && random.nextInt(100) + 1 < chanceToEquipGear;
    }

    private boolean shouldUseGearAttack() {
        return currentCharacter.isEquipped()
                && currentCharacter.getAttack().MAX_DAMAGE < currentCharacter.getEquippedGear().getAttack().MAX_DAMAGE;
    }

    private boolean shouldUseStandardAttack() {
        return !currentCharacter.isBlinded();
    }
}
