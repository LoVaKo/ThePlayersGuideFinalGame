package inventory.equippables.weapons;

import attacks.special.RaiseSkeleton;

public class StaffOfResurrection extends Weapon {

    public StaffOfResurrection() {
        super("Staff of Resurrection",
                new RaiseSkeleton(),
                4,
                WeaponType.MAGICAL);
    }
}
