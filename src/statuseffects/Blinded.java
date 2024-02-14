package statuseffects;

import gamecharacters.GameCharacter;

public class Blinded extends StatusEffect {

    public Blinded() {
        super("Blinded",
                2,
                3);
    }

    @Override
    public void execute(GameCharacter character) {
        System.out.println(character.getName() + " is unable to see and can't perform any attacks.");
    }
}
