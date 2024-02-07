package attacks.special;

import attacks.DamageType;
import gamecharacters.GameCharacter;
import gamecharacters.Party;
import statuseffects.Inspired;
import statuseffects.StatusEffect;

public class RallyForTheKing extends SpecialAttack {
    public RallyForTheKing() {
        super("Rally for the King",
                DamageType.NONE,
                0.8,
                0,
                new Inspired(),
                3);
    }

    @Override
    public void useAttack(GameCharacter character, Party enemyParty, boolean isComputer) {
        System.out.println(character.getName() + " put's his fist in the air, screaming: For the King!");
        System.out.println("The entire party is Inspired!");

        // Apply Inspired on all party members
        for (GameCharacter partyMember : character.getOwnParty().getCharacters()) {
            Inspired inspiredEffect = new Inspired();
            partyMember.setEffect(inspiredEffect);
            cooldownManager.add(inspiredEffect);

            if (partyMember.equals(character)) {
                // When the effect is put on the character itself, account for immediate removal.
                StatusEffect effect = character.getEffect();
                int cooldownCounter = effect.getCooldownCounter();
                int activeCounter = effect.getActiveCounter();

                effect.setCooldownCounter(cooldownCounter + 1);
                effect.setActiveCounter(activeCounter + 1);
            }
        }
    }

    @Override
    protected int getBaseDamage() {
        return 0;
    }
}
