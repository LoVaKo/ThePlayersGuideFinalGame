package Attacks;

import StatusEffects.Blinded;

public class Punch extends Attack {

    public Punch() {
        super("Punch",
                DamageType.NORMAL,
                1.0,
                1,
                new Blinded(),
                true);
    }

    @Override
    public int calculateAttackDamage() {
        return 1;
    }
}
