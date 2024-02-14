package attacks.effect;

import attacks.DamageType;
import statuseffects.Frozen;


public class FrostStorm extends EffectAttack {
    public FrostStorm() {
        super("Frost Storm",
                DamageType.MAGICAL,
                1.0,
                3,
                new Frozen(),
                2);
    }

    @Override
    protected int getBaseDamage() {
        return 3;
    }
}
