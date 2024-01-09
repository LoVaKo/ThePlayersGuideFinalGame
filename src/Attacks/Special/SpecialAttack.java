package Attacks.Special;

import Attacks.Attack;
import Attacks.DamageType;
import GameCharacters.GameCharacter;
import StatusEffects.CoolDown;
import StatusEffects.StatusEffect;

public abstract class SpecialAttack extends Attack {
    private final StatusEffect effect;

    public SpecialAttack(String name, DamageType damageType, double successRate, int MAX_DAMAGE, StatusEffect effect) {
        super(  name,
                damageType,
                successRate,
                MAX_DAMAGE,
                true);
        this.effect = effect;
    }

    public static void resolveEffect(GameCharacter target, SpecialAttack attack) {
        if (!target.hasEffect()) {
            System.out.println(target + " has been " + attack.effect + "!");
            target.setEffect(attack.effect);
        } else if (target.getEffect() instanceof CoolDown) {
            ((CoolDown) target.getEffect()).checkForCooldown(attack.effect, target);
        }
    }
}
