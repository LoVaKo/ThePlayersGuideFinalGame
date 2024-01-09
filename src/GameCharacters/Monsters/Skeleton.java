package GameCharacters.Monsters;

import Attacks.BoneCrunch;

public class Skeleton extends Monster {

    // Constructor
    public Skeleton() {
        super("Skeleton",
                new BoneCrunch(),
                null,
                5,
                1);
    }

}
