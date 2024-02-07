package attacks.basic;

import attacks.DamageType;

public class Stab extends BasicAttack {
    public Stab() {
        super("Stab",
                DamageType.PHYSICAL,
                1.0,
                3);
    }

    @Override
    public int calculateAttackDamage() {
        return 1;
    }
}
