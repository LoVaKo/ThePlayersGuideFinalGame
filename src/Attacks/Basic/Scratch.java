package Attacks.Basic;

import Attacks.DamageType;

import java.util.Random;

public class Scratch extends BasicAttack {
    public Scratch() {
        super("Scratch",
                DamageType.PHYSICAL,
                0.9,
                2
        );
    }

    @Override
    protected int calculateAttackDamage() {
        Random random = new Random();
        return random.nextInt(2) + 1;
    }
}
