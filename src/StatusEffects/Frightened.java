package StatusEffects;

import GameCharacters.GameCharacter;

public class Frightened extends StatusEffect {
    public Frightened() {
        super("Frightened",
                "Unable to perform any actions",
                1,
                3);
    }

    @Override
    public void apply(GameCharacter character) {
        System.out.println("Frozen in shock, " + character.getName() + " is unable to perform any actions.");
    }
}
