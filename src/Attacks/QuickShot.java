package Attacks;

public class QuickShot extends Attack {

    public QuickShot() {
        super("Quick Shot",
                DamageType.RANGED,
                0.5,
                3,
                null,
                false);
    }

    @Override
    public int calculateAttackDamage() {
        return 3;
    }
}
