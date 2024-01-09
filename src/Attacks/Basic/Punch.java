package Attacks.Basic;

import Attacks.Basic.BasicAttack;
import Attacks.DamageType;

public class Punch extends BasicAttack {

    public Punch() {
        super("Punch",
                DamageType.NORMAL,
                1.0,
                1);
    }

    @Override
    public int calculateAttackDamage() {
        return 1;
    }
}
