package inventory.equippables.armor;

import defenses.HeavyArmor;

public class IronArmor extends Armor {
    public IronArmor() {
        super("Iron Armor",
                new HeavyArmor(),
                3);
    }
}
