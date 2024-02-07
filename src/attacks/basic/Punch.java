package attacks.basic;

import attacks.DamageType;

public class Punch extends BasicAttack {

    public Punch() {
        super("Punch",
                DamageType.PHYSICAL,
                1.0,
                1);
    }

    @Override
    public int getBaseDamage() {
        return 1;
    }
}
