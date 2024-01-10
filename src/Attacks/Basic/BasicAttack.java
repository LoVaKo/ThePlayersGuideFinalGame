package Attacks.Basic;

import Attacks.Attack;
import Attacks.DamageType;

public abstract class BasicAttack extends Attack {

    public BasicAttack(String name, DamageType damageType, double successRate, int MAX_DAMAGE) {
        super(name,
                damageType,
                successRate,
                MAX_DAMAGE,
                false);
    }
}
