package GameCharacters.Monsters;


import Attacks.Basic.PhantomScream;
import Inventories.Equippables.Weapons.WeaponType;

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
