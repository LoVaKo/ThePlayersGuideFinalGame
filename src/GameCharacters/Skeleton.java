package GameCharacters;

import Attacks.BoneCrunch;
import Main.Party;

public class Skeleton extends GameCharacter {

    // Constructor
    public Skeleton(Party ownParty, Party enemyParty) {
        super("Skeleton",
                new BoneCrunch(),
                null,
                ownParty,
                enemyParty,
                5);
    }

}
