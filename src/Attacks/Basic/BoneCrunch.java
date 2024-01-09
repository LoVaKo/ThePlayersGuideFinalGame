package Attacks.Basic;

import Attacks.Basic.BasicAttack;
import Attacks.DamageType;

import java.util.Random;

public class BoneCrunch extends BasicAttack {
    public BoneCrunch() {
        super("Bone Crunch",
                DamageType.NORMAL,
                0.9,
                3);
    }

    @Override
    public int calculateAttackDamage() {
        Random random = new Random();
        return random.nextInt(3) + 1;
    }
}
