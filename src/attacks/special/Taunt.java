package attacks.special;

import attacks.DamageType;
import attacks.effect.EffectAttack;
import statuseffects.Enraged;

public class Taunt extends EffectAttack {

    public Taunt() {
        super("Taunt",
                DamageType.PHYSICAL,
                0.9,
                1,
                new Enraged(),
                2);
    }

    @Override
    protected int getBaseDamage() {
        return 1;
    }

}
