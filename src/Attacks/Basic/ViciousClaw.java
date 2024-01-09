package Attacks.Basic;

import Attacks.DamageType;

import java.util.Random;

public class ViciousClaw extends BasicAttack {
    public ViciousClaw() {
        super("Vicious Claw",
                DamageType.NORMAL,
                1.0,
                5);
    }

    @Override
    public int calculateAttackDamage() {
        Random random = new Random();
        int percentage = random.nextInt(100) + 1;
        if (percentage <= 33) {
            return 5;
        }
        return 1;
    }
}
