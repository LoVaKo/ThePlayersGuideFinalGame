package GameCharacters.Heroes;

import Attacks.Basic.Punch;
import Inventories.Equippables.Weapons.Bow;
import Inventories.Equippables.Weapons.WeaponType;

public class VinFletcher extends Hero {

    public VinFletcher() {
        super(
                "Vin Fletcher",
                new Punch(),
                null,
                null,
                15,
                WeaponType.RANGED);

        getEquippedItems().setWeapon(new Bow());
    }

    public static String printCharacterInformation() {
        return """
                \n================= Vin Fletcher =================
                ATTACK:             Punch (1 damage)
                GEARBASED ATTACK:   Quickshot (3 damage)
                                    (50% chance)
                HP:                 15
                EXTRA:              Comes equipped with Vins Bow
                ================================================""";
    }
}
