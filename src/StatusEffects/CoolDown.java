package StatusEffects;

import GameCharacters.GameCharacter;

public class CoolDown extends StatusEffect {
    private final StatusEffect originalEffect;

    public CoolDown(StatusEffect originalEffect) {
        super("Cool down",
                "Temporarily invulnerable to status effects",
                1,
                0);
        this.originalEffect = originalEffect;
    }

    public void checkForCooldown(StatusEffect newEffect, GameCharacter target) {
        if (originalEffect != newEffect) {
            System.out.println(target + " has been " + newEffect.getName() + "!");
            target.setEffect(newEffect);
        } else {
            System.out.println(target.getName() + " cannot be " + originalEffect.getName() + " for " + getNumOfRounds() + " rounds.");
        }
    }
}
