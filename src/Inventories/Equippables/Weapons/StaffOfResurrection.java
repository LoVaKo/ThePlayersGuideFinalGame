package Inventories.Equippables.Weapons;

import Attacks.Special.RaiseSkeleton;

public class StaffOfResurrection extends Weapon {

    public StaffOfResurrection() {
        super("Staff of Resurrection",
                new RaiseSkeleton(),
                4,
                WeaponType.MAGICAL);
    }
}
