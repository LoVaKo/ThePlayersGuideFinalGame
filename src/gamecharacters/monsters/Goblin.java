package gamecharacters.monsters;

import attacks.basic.Punch;
import attacks.special.Taunt;
import defenses.ToughSkin;
import inventory.equippables.weapons.IronSword;
import inventory.equippables.weapons.WeaponType;

public class Goblin extends Monster {
    public Goblin() {
        super("Goblin",
                new Punch(),
                new Taunt(),
                null,
                new ToughSkin(),
                10,
                3,
                WeaponType.MELEE);
        new IronSword().equipUponCreation(this);
    }
}
