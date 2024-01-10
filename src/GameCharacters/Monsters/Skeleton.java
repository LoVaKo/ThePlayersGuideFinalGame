package GameCharacters.Monsters;

import Attacks.Basic.BoneCrunch;
import Inventories.Equippables.Weapons.WeaponType;

public class Skeleton extends Monster {

    // Constructor
    public Skeleton() {
        super("Skeleton",
                new BoneCrunch(),
                null,
                null,
                5,
                1,
                WeaponType.MELEE);
    }

}
