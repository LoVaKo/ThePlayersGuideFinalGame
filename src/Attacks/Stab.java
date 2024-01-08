package Attacks;

public class Stab extends Attack {
    public Stab() {
        super("Stab",
                DamageType.NORMAL,
                1.0,
                1,
                null,
                false);
    }

    @Override
    public int calculateAttackDamage() {
        return 1;
    }
}
