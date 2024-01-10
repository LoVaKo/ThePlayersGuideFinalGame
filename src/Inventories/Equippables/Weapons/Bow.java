package Inventories.Equippables.Weapons;

import Attacks.Special.QuickShot;

public class Bow extends Weapon {
    public Bow() {
        super("Bow",
                new QuickShot(),
                2,
                WeaponType.RANGED);
    }
}
