package defenses;

import attacks.DamageType;

public class HeavyArmor extends Defense{
    public HeavyArmor() {
        super(  "heavy armor",
                "protects against physical attacks",
                1.0,
                DamageType.PHYSICAL);
    }

    @Override
    public int calculateDamageReduction() {
        return 2;
    }
}
