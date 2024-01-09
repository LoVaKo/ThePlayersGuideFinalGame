package Attacks.Special;

import Attacks.DamageType;
import Attacks.Special.SpecialAttack;
import StatusEffects.Blinded;

public class QuickShot extends SpecialAttack {

    public QuickShot() {
        super("Quick Shot",
                DamageType.RANGED,
                0.5,
                3,
                new Blinded());
    }

    @Override
    public int calculateAttackDamage() {
        return 3;
    }
}
