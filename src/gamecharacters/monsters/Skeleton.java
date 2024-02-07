package gamecharacters.monsters;

import attacks.basic.BoneCrunch;
import inventory.equippables.weapons.WeaponType;

public class Skeleton extends Monster {

    // Constructor
    public Skeleton() {
        super("Skeleton",
                new BoneCrunch(),
                null,
                null,
                10,
                2,
                WeaponType.MELEE);
    }

}
