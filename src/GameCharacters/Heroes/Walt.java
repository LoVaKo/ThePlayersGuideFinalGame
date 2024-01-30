package GameCharacters.Heroes;

import Attacks.Basic.Scratch;
import Attacks.Special.ViciousClaw;
import Defenses.FurCoat;
import Inventories.Equippables.Weapons.WeaponType;

import java.util.Random;

public class Walt extends Hero {


    public Walt() {
        super("Walt",
                new Scratch(),
                new ViciousClaw(),
                new FurCoat(),
                25,
                WeaponType.NONE);
    }


    public static String printCharacterInformation() {
        return """
                \n=========================== Walt the Cat ===========================
                BASIC ATTACK:       SCRATCH (1-2)
                                    Success rate 90%
                ATTACK:             VICIOUS CLAW (3-5)
                                    33 % chance for 5 damage
                                    Otherwise 3 damage
                                    Effect: Wounded
                                    Cooldown: 3 turns
                HP:                 20
                INFO:               Is PURRfect. Prefers to fight without weapons.
                                    May take unexpected naps.
                ==================================================================""";
    }

    public boolean takeNap() {
        Random random = new Random();
        int nappingChance = 5;
        int rollForNap = random.nextInt();
        if (rollForNap < nappingChance) {
            System.out.println("Walt decided to take an unexpected nap.");
            return true;
        }
        return false;
    }
}
