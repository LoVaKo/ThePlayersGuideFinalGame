package GameCharacters.Heroes;

import Attacks.Basic.Punch;
import Inventories.Equippables.Armor.LeatherArmor;
import Inventories.Equippables.Weapons.Bow;
import Inventories.Equippables.Weapons.WeaponType;

public class Archer extends Hero {

    public Archer(String name) {
        super(
                name,
                new Punch(),
                null,
                null,
                15,
                WeaponType.RANGED);

        getEquippedItems().setWeapon(new Bow());
        getEquippedItems().setArmor(new LeatherArmor());
    }

    public static void printCharacterInformation() {
        System.out.println("""
                \n=========================== The Archer ===========================
                BASIC ATTACK:       PUNCH (1 damage)
                                    Success rate 100%
                GEARBASED ATTACK:   QUICK SHOT (3 damage)
                                    Success rate 90%
                                    Effect: Wounded for 3 rounds
                                    Cooldown: None
                HP:                 15
                INFO:               Comes equipped with Bow and Leather Armor
                ==================================================================""");
    }
}
