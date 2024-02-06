package Defenses;

import Attacks.DamageType;
import GameCharacters.GameCharacter;

import java.util.Random;

public class Regeneration extends Defense {
    public Regeneration() {
        super("Regeneration",
                "Spontaneous Regeneration possibilty each round",
                0.2,
                DamageType.NONE);
    }

    public void regenerate(GameCharacter character) {
        Random random = new Random();
        double CHANCE_TO_REGENERATE = 0.2;
        double ROLL_TO_REGENERATE = random.nextDouble();
        if (ROLL_TO_REGENERATE < CHANCE_TO_REGENERATE) {

            if (character.getCurrentHP() > (character.getStartingHP() - 3)) {
                character.setCurrentHP(character.getStartingHP());
                System.out.println("The amulet of Dahra glows with a golden light. " + character.getName() + "'s health has been restored to full.");
            } else {
                character.setCurrentHP(character.getCurrentHP() + 3);
                System.out.println("The amulet of Dahra glows with a golden light. " + character.getName() + "'s health has been restored by 3 points.");
            }
        }
    }
}
