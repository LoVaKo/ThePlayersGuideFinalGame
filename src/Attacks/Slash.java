package Attacks;

public class Slash extends Attack {
    public Slash() {
        super("Slash",
                DamageType.NORMAL,
                1.0,
                2);
    }

    @Override
    public int calculateAttackDamage() {
        return 2;
    }

}
