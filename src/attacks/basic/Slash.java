package attacks.basic;

import attacks.DamageType;

public class Slash extends BasicAttack{
    public Slash() {
        super(  "Slash",
                DamageType.PHYSICAL,
                1.0,
                3);
    }

    @Override
    protected int getBaseDamage() {
        return 3;
    }
}
