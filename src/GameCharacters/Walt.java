package GameCharacters;

import Attacks.ViciousClaw;
import Defenses.FurCoat;
import Main.Party;

public class Walt extends GameCharacter {


    public Walt(Party ownParty, Party enemyParty) {
        super("Walt",
                new ViciousClaw(),
                new FurCoat(),
                ownParty,
                enemyParty,
                88);
    }

    @Override
    public String printCharacterInformation() {
        String characterInformation = """
                \n================= Walt the Cat =================
                ATTACK:             Vicious Claw (1-5)
                                    33 % chance for 5 damage
                                    Otherwise 1 damage
                HP:                 88
                EXTRA:              Is PURRfect.
                ================================================""";
        return characterInformation;
    }
}
