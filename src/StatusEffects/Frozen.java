package StatusEffects;

import GameCharacters.GameCharacter;

public class Frozen extends StatusEffect {
    public Frozen() {
        super("Frozen",
                2,
                3);
    }

    @Override
    public void apply(GameCharacter character) {
        System.out.println("Frozen solid, " + character.getName() + " is unable to perform any actions for two rounds.");
    }
}
