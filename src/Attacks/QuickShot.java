package Attacks;

public class QuickShot extends Attack {

    public QuickShot() {
        super("Quick Shot",
                DamageType.NORMAL,
                0.5,
                3);
    }

    @Override
    public int calculateAttackDamage() {
        return 3;
    }
}
