package Attacks.Special;

import Attacks.DamageType;
import StatusEffects.Wounded;

public class QuickShot extends SpecialAttack {

    public QuickShot() {
        super("Quick Shot",
                DamageType.PHYSICAL,
                0.9,
                3,
                new Wounded(),
                0);
    }

    @Override
    public int calculateAttackDamage() {
        return 3;
    }
}
