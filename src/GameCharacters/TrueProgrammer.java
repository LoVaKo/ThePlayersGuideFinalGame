package GameCharacters;

import Attacks.Punch;
import Defenses.ObjectSight;
import Inventory.Sword;
import Main.Party;

public class TrueProgrammer extends GameCharacter {

    // Constructor
    public TrueProgrammer(String name, Party ownParty, Party enemyParty) {
        super(name,
                new Punch(),
                new ObjectSight(),
                ownParty,
                enemyParty,
                25);

        equippedItems.addItem(new Sword());
    }

    @Override
    public String printCharacterInformation() {
        String characterInformation = """
                \n============== The True Programmer =============
                ATTACK:             Punch (1 damage)
                GEARBASED ATTACK:   Slash (3 damage)
                HP:                 25
                EXTRA:              Comes equipped with Sword
                ================================================""";
        return characterInformation;
    }
}
