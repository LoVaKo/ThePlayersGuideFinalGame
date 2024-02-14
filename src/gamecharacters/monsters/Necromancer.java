package gamecharacters.monsters;

import attacks.effect.NecroticBlast;
import attacks.special.RaiseShade;
import inventory.equippables.jewelry.BonyAmulet;
import inventory.equippables.weapons.StaffOfResurrection;
import inventory.equippables.weapons.WeaponType;

public class Necromancer extends Monster {
    public Necromancer() {
        super("Necromancer",
                new RaiseShade(),
                new NecroticBlast(),
                null,
                null,
                15,
                5,
                WeaponType.MAGICAL);

        new BonyAmulet().equipUponCreation(this);
        new StaffOfResurrection().equipUponCreation(this);
    }
}
