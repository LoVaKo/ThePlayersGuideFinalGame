package gamecharacters.monsters;


import attacks.basic.PhantomScream;
import inventory.equippables.weapons.WeaponType;

public class Shade extends Monster {
    public Shade() {
        super("Shade",
                new PhantomScream(),
                null,
                null,
                3,
                1,
                WeaponType.NONE);
    }
}
