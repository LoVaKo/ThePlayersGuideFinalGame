package Inventories.Equippables.Jewelry;

import Defenses.Defense;
import Inventories.Equippables.Gear;

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
