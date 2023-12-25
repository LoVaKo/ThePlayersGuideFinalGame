package GameCharacters;

import Attacks.UnravelingAttack;
import Main.Party;

public class TheUncodedOne extends GameCharacter {

    public TheUncodedOne(Party ownParty, Party enemyParty) {
        super("The Uncoded One",
                new UnravelingAttack(),
                null,
                ownParty,
                enemyParty,
                15);
    }
}
