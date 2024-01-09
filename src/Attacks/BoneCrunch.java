package Attacks;

import StatusEffects.Blinded;

import java.util.Random;

public class BoneCrunch extends Attack {
    public BoneCrunch() {
        super("Bone Crunch",
                DamageType.NORMAL,
                0.9,
                3,
                new Blinded(),
                true);
    }

    @Override
    public int calculateAttackDamage() {
        Random random = new Random();
        return random.nextInt(3) + 1;
    }
}
