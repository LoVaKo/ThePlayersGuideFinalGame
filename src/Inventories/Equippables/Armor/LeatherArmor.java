package Inventories.Equippables.Armor;

import Defenses.Defense;
import Defenses.LightArmor;

public class LeatherArmor extends Armor {
    public LeatherArmor(String name, Defense defense) {
        super("Leather Armor",
                new LightArmor(),
                2);
    }
}
