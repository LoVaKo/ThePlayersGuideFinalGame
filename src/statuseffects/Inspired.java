package statuseffects;

import gamecharacters.GameCharacter;

public class Inspired extends StatusEffect {
    public Inspired() {
        super("Inspired",
                1,
                1);
    }

    @Override
    public void execute(GameCharacter character) {
        System.out.println(character.getName() + " is Inspired and will deal 1 extra damage this round.");
    }
}
