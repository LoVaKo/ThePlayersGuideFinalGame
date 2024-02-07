package attacks.special;

import attacks.DamageType;
import statuseffects.Frozen;


public class FrostStorm extends SpecialAttack {
    public FrostStorm() {
        super("Frost Storm",
                DamageType.MAGICAL,
                1.0,
                3,
                new Frozen(),
                2);
    }

    @Override
    protected int calculateAttackDamage() {
        return 3;
    }
}
