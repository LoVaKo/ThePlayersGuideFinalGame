package Attacks.Special;

import Attacks.DamageType;
import StatusEffects.Wounded;

import java.util.Random;

public class ViciousClaw extends SpecialAttack {
    public ViciousClaw() {
        super("Vicious Claw",
                DamageType.PHYSICAL,
                1.0,
                5,
                new Wounded(),
                3);
    }

    @Override
    public int calculateAttackDamage() {
        Random random = new Random();
        int percentage = random.nextInt(100) + 1;
        if (percentage <= 33) {
            return 5;
        }
        return 3;
    }
}
