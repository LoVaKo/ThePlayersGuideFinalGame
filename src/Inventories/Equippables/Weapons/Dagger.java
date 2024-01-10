package Inventories.Equippables.Weapons;

import Attacks.Basic.Stab;

public class Dagger extends Weapon {
    public Dagger() {
        super("Dagger",
                new Stab(),
                1,
                WeaponType.MELEE);
    }

}
