package inventory.equippables.weapons;

import attacks.basic.Stab;

public class Dagger extends Weapon {
    public Dagger() {
        super("Dagger",
                new Stab(),
                1,
                WeaponType.MELEE);
    }

}
