package Inventories.Equippables.Armor;

import Defenses.Defense;
import Inventories.Equippables.Gear;

public abstract class Armor extends Gear {
    protected Defense defense;

    public Armor(String name, Defense defense, int level) {
        super(name, level);
        this.defense = defense;
    }
}
