package GameCharacters.Heroes;

import Attacks.Punch;
import Inventory.VinsBow;

public class VinFletcher extends Hero {

    public VinFletcher() {
        super(
                "Vin Fletcher",
                new Punch(),
                null,
                15);

        equippedItems.addItem(new VinsBow());
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
