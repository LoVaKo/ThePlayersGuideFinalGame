package attacks.basic;

import attacks.DamageType;

import java.util.Random;

public class LightningBolt extends BasicAttack {
    public LightningBolt() {
        super("Lightning Bolt",
                DamageType.MAGICAL,
                0.75,
                3);
    }

    @Override
    protected int calculateAttackDamage() {
        Random random = new Random();
        return random.nextInt(2) + 2;
    }
}
