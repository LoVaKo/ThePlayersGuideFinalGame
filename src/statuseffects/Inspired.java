package statuseffects;

import gamecharacters.GameCharacter;

public class Inspired extends StatusEffect{
    public Inspired() {
        super(  "Inspired",
                1,
                1);
    }

    @Override
    public void apply(GameCharacter character) {
        System.out.println();
    }
}
