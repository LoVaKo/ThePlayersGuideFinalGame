package attacks.basic;

import attacks.DamageType;

import java.util.Random;

public class BoneCrunch extends BasicAttack {
    public BoneCrunch() {
        super("Bone Crunch",
                DamageType.PHYSICAL,
                0.9,
                3);
    }

    @Override
    public int getBaseDamage() {
        Random random = new Random();
        return random.nextInt(3) + 1;
    }
}
