package attacks.basic;

import attacks.DamageType;

public class PhantomScream extends BasicAttack {
    public PhantomScream() {
        super("Phantom Scream",
                DamageType.PHYSICAL,
                0.5,
                1);
    }

    @Override
    protected int calculateAttackDamage() {
        return 1;
    }
}
