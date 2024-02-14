package gamecharacters.heroes;

import attacks.basic.Punch;
import attacks.special.RallyForTheKing;
import inventory.equippables.armor.IronArmor;
import inventory.equippables.weapons.IronSword;
import inventory.equippables.weapons.WeaponType;

public class Knight extends Hero {
    public Knight(String name) {
        super(name + " the Knight",
                new Punch(),
                new RallyForTheKing(),
                null,
                null,
                20,
                WeaponType.MELEE);
        new IronSword().equipUponCreation(this);
        new IronArmor().equipUponCreation(this);
    }

    public static void printCharacterInformation() {
        System.out.println("""
                \n=========================== The Mage ===========================
                BASIC ATTACK:       PUNCH (1 damage)
                                    Success rate 100%
                GEAR BASED ATTACK:  Slash (3)
                                    Success rate 100%
                ATTACK:             RALLY FOR THE KING (3)
                                    Success rate 80%
                                    Effect: Party members are Inspired for 1 round
                                    Cooldown: 3 turns
                HP:                 20
                INFO:               Comes equipped with Iron Sword and Iron Armor
                ==================================================================""");
    }
}
