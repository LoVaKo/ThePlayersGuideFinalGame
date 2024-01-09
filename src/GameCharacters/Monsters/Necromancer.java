package GameCharacters.Monsters;

import Attacks.Attack;
import Attacks.Basic.Punch;
import Attacks.Special.QuickShot;
import Defenses.Defense;

public class Necromancer extends Monster{
    public Necromancer() {
        super(  "Necromancer",
                new Punch(),
                new QuickShot(),
                null,
                15,
                5);
    }
}
