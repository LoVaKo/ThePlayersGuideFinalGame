package GameCharacters.Heroes;

import Attacks.Basic.LightningBolt;
import Attacks.Special.FrostStorm;
import Inventories.Equippables.Jewelry.AmuletOfDahra;
import Inventories.Equippables.Weapons.WeaponType;

public class Mage extends Hero {
    public Mage(String name) {
        super(name,
                new LightningBolt(),
                new FrostStorm(),
                null,
                10,
                WeaponType.MAGICAL);
        getEquippedItems().setJewelry(new AmuletOfDahra());
    }

    public static void printCharacterInformation() {
        System.out.println("""
                \n=========================== The Mage ===========================
                BASIC ATTACK:       LIGHTNING BOLT (2-3)
                                    Success rate 75%
                ATTACK:             FROST STORM (3)
                                    Success rate 100%
                                    Effect: Frozen for 2 rounds
                                    Cooldown: 3 turns
                HP:                 10
                INFO:               Comes equipped with Amulet of Dahra
                ==================================================================""");
    }
}
