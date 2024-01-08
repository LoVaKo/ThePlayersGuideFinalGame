package Defenses;

import Attacks.DamageType;

public class ObjectSight extends Defense {
    public ObjectSight() {
        super("Object Sight",
                "The Coding attack was spotted! Object sight lessened the impact of the attack.",
                0.7,
                DamageType.DECODING);
    }

    @Override
    public int calculateDamageReduction() {
        return 2;
    }
}
