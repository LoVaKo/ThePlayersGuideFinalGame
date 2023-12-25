package Attacks;

public class Punch extends Attack {

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
