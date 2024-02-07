package inventory.equippables.weapons;

import attacks.special.QuickShot;

public class Bow extends Weapon {
    public Bow() {
        super("Bow",
                new QuickShot(),
                2,
                WeaponType.RANGED);
    }
}
