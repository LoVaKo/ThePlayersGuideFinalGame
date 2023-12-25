package Attacks;

import java.util.Random;

public class UnravelingAttack extends Attack {
    public UnravelingAttack() {
        super("Unraveling Attack",
                DamageType.DECODING,
                1.0,
                4);
    }

    @Override
    public int calculateAttackDamage() {
        Random random = new Random();
        return random.nextInt(5);
    }
}
