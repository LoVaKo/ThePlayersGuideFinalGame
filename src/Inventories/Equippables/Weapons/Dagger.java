package Inventories.Equippables.Weapons;

import Attacks.Attack;
import Attacks.Basic.Stab;

public class Dagger extends Weapon {
    public Dagger() {
        super("Dagger",
                new Stab(),
                1,
                WeaponType.MELEE);
    }

}
