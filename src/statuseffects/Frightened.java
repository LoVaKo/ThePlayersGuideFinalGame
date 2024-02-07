package statuseffects;

import gamecharacters.GameCharacter;

public class Frightened extends StatusEffect {
    public Frightened() {
        super("Frightened",
                1,
                3);
    }

    @Override
    public void apply(GameCharacter character) {
        System.out.println("Frozen in shock, " + character.getName() + " is unable to perform any actions.");
    }
}
