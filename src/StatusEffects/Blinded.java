package StatusEffects;

import GameCharacters.GameCharacter;

public class Blinded extends StatusEffect {

    public Blinded() {
        super("Blinded",
                "Whilst unable to see, this character can't perform any attacks",
                2,
                3);
    }

    @Override
    public void apply(GameCharacter character) {
        System.out.println(character.getName() + " is unable to see and can't perform any attacks.");
    }
}
