package attacks.special;

import attacks.DamageType;
import attacks.effect.EffectAttack;
import statuseffects.StatusEffect;

public abstract class SpecialAttack extends EffectAttack {

    public SpecialAttack(String name, DamageType damageType, double successRate, int MAX_DAMAGE, StatusEffect effect, int coolDown) {
        super(name, damageType, successRate, MAX_DAMAGE, effect, coolDown);
    }

    @Override
    protected int getBaseDamage() {
        return 0;
    }
}
