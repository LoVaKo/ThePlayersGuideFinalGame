package Attacks.Special;

import Attacks.DamageType;
import StatusEffects.Frightened;

public class NecroticBlast extends SpecialAttack {

    public NecroticBlast() {
        super("Necrotic Blast",
                DamageType.MAGICAL,
                0.8,
                4,
                new Frightened(),
                3);
    }

    @Override
    protected int calculateAttackDamage() {
        return MAX_DAMAGE;
    }
}
