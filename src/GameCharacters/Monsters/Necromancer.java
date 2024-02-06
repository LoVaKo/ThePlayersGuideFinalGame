package GameCharacters.Monsters;

import Attacks.Basic.RaiseShade;
import Attacks.Special.NecroticBlast;
import Inventories.Equippables.Jewelry.BonyAmulet;
import Inventories.Equippables.Weapons.WeaponType;

public class Necromancer extends Monster {
    public Necromancer() {
        super("Necromancer",
                new RaiseShade(),
                new NecroticBlast(),
                null,
                15,
                5,
                WeaponType.MAGICAL);

        new BonyAmulet().equipUponCreation(this);
        //new StaffOfResurrection().equipUponCreation(this);
    }
}
