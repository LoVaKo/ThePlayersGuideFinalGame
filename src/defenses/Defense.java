package defenses;


import attacks.DamageType;

import java.util.Random;

public abstract class Defense {
    private final String name;
    private final String description;
    private final double SUCCES_RATE;
    private final DamageType protectedDamageType;

    protected Defense(String name, String description, double succesRate, DamageType protectedDamageType) {
        this.name = name;
        this.description = description;
        this.SUCCES_RATE = succesRate;
        this.protectedDamageType = protectedDamageType;
    }

    public int activate(DamageType damageType) {
        Random random = new Random();
        if (random.nextDouble() < SUCCES_RATE
                && (this.protectedDamageType.equals(damageType)
                || protectedDamageType.equals(DamageType.ALL))) {
            System.out.println(description);
            return calculateDamageReduction();
        } else {
            return 0;
        }

    }

    public int calculateDamageReduction() {
        return 0;
    }

}


