package GameCharacters.Heroes;

import Attacks.Basic.ViciousClaw;
import Defenses.FurCoat;

public class Walt extends Hero {


    public Walt() {
        super("Walt",
                new ViciousClaw(),
                null,
                new FurCoat(),
                88,
                null);
    }


    public static String printCharacterInformation() {
        return """
                \n================= Walt the Cat =================
                ATTACK:             Vicious Claw (1-5)
                                    33 % chance for 5 damage
                                    Otherwise 1 damage
                HP:                 88
                EXTRA:              Is PURRfect.
                ================================================""";
    }
}
