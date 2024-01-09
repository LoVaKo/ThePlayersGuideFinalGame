package Attacks.Basic;

import Attacks.Basic.BasicAttack;
import Attacks.DamageType;

public class Stab extends BasicAttack {
    public Stab() {
        super("Stab",
                DamageType.NORMAL,
                1.0,
                1);
    }

    @Override
    public int calculateAttackDamage() {
        return 1;
    }
}
