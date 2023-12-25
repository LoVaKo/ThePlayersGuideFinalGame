package Defenses;

import Attacks.DamageType;

public class FurCoat extends Defense {

    public FurCoat() {
        super("Fur coat",
                "The thick fur coat lessened the impact of the attack!",
                1.0,
                DamageType.ALL);
    }


    @Override
    public int calculateDamageReduction() {
        return 1;
    }
}
