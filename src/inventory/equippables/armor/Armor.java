package inventory.equippables.armor;

import defenses.Defense;
import inventory.equippables.Gear;

public abstract class Armor extends Gear {
    protected Defense defense;

    public Armor(String name, Defense defense, int level) {
        super(name, level);
        this.defense = defense;
    }
}
