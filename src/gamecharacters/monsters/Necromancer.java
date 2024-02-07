package gamecharacters.monsters;

import attacks.basic.RaiseShade;
import attacks.special.NecroticBlast;
import inventory.equippables.jewelry.BonyAmulet;
import inventory.equippables.weapons.StaffOfResurrection;
import inventory.equippables.weapons.WeaponType;

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
        new StaffOfResurrection().equipUponCreation(this);
    }
}
