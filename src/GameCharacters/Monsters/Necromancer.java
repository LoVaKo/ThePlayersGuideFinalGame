package GameCharacters.Monsters;

import Attacks.Basic.Punch;
import Attacks.Special.QuickShot;
import Inventories.Equippables.Jewelry.BonyAmulet;
import Inventories.Equippables.Weapons.WeaponType;

public class Necromancer extends Monster {
    public Necromancer() {
        super("Necromancer",
                new Punch(),
                new QuickShot(),
                null,
                15,
                5,
                WeaponType.MAGICAL);

        new BonyAmulet().equipUponCreation(this);
    }
}
