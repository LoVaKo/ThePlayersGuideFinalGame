package statuseffects;

import gamecharacters.GameCharacter;

public class CoolDown extends StatusEffect {
    private final StatusEffect originalEffect;

    public CoolDown(StatusEffect originalEffect) {
        super("Cool down",
                originalEffect.getCooldownCounter(),
                0);
        this.originalEffect = originalEffect;
    }

    public void checkForCooldown(StatusEffect newEffect, GameCharacter target) {
        if (originalEffect != newEffect) {
            System.out.println(target + " has been " + newEffect.getName() + "!");
            target.setEffect(newEffect);
        } else {
            System.out.println(target.getName() + " cannot be " + originalEffect.getName() + " for " + getNUM_ROUNDS_ACTIVE() + " rounds.");
        }
    }
}
