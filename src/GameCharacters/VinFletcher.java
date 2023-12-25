package GameCharacters;

import Attacks.Punch;
import Inventory.VinsBow;
import Main.Party;

public class VinFletcher extends GameCharacter {

    public VinFletcher(Party ownParty, Party enemyParty) {
        super(
                "Vin Fletcher",
                new Punch(),
                null,
                ownParty,
                enemyParty,
                15);

        equippedItems.addItem(new VinsBow());
    }

    @Override
    public String printCharacterInformation() {
        String characterInformation = """
                \n================= Vin Fletcher =================
                ATTACK:             Punch (1 damage)
                GEARBASED ATTACK:   Quickshot (3 damage)
                                    (50% chance)
                HP:                 15
                EXTRA:              Comes equipped with Vins Bow
                ================================================""";
        return characterInformation;
    }
}
