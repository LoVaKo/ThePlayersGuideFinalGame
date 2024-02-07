package inventory.equippables.weapons;

import attacks.basic.Slash;

public class IronSword extends Weapon{
    public IronSword() {
        super(  "Iron Sword",
                new Slash(),
                2,
                WeaponType.MELEE);
    }
}
