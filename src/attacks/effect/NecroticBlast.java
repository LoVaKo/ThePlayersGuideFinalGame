package attacks.effect;

import attacks.DamageType;
import statuseffects.Frightened;

public class NecroticBlast extends EffectAttack {

    public NecroticBlast() {
        super("Necrotic Blast",
                DamageType.MAGICAL,
                0.8,
                4,
                new Frightened(),
                3);
    }

    @Override
    protected int getBaseDamage() {
        return MAX_DAMAGE;
    }
}
