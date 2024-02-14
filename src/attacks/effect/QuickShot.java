package attacks.effect;

import attacks.DamageType;
import statuseffects.Wounded;

public class QuickShot extends EffectAttack {

    public QuickShot() {
        super("Quick Shot",
                DamageType.PHYSICAL,
                0.9,
                3,
                new Wounded(),
                0);
    }

    @Override
    public int getBaseDamage() {
        return 3;
    }
}
