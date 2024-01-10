package Attacks.Basic;

import Attacks.DamageType;

public class Punch extends BasicAttack {

    public Punch() {
        super("Punch",
                DamageType.PHYSICAL,
                1.0,
                1);
    }

    @Override
    public int calculateAttackDamage() {
        return 1;
    }
}
