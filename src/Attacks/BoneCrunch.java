package Attacks;

import java.util.Random;

public class BoneCrunch extends Attack {
    public BoneCrunch() {
        super("Bone Crunch",
                DamageType.NORMAL,
                1.0,
                1);
    }

    @Override
    public int calculateAttackDamage() {
        Random random = new Random();
        return random.nextInt(2);
    }
}
