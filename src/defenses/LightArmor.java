package defenses;

import attacks.DamageType;

public class LightArmor extends Defense {
    public LightArmor() {
        super("Light Armor",
                "protects against physical attacks",
                0.5,
                DamageType.PHYSICAL);
    }

    @Override
    public int calculateDamageReduction() {
        return 1;
    }
}
