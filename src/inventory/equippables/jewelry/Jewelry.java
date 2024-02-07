package inventory.equippables.jewelry;

import defenses.Defense;
import inventory.equippables.Gear;

public abstract class Jewelry extends Gear {
    protected Defense defense;

    public Jewelry(String name, Defense defense, int level) {
        super(name, level);
        this.defense = defense;
    }

    public Defense getDefense() {
        return defense;
    }
}
