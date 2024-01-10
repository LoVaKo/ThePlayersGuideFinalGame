package Defenses;

import Attacks.DamageType;

public class MagicBlock extends Defense {

    public MagicBlock() {
        super("Magic Block",
                "blocks all magic damage",
                0.75,
                DamageType.MAGICAL);
    }

    @Override
    public int calculateDamageReduction() {
        return 100;
    }
}
