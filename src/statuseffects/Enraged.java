package statuseffects;

import gamecharacters.GameCharacter;

public class Enraged extends StatusEffect {
    public Enraged() {
        super("Enraged",
                1,
                1);
    }

    @Override
    public void execute(GameCharacter character) {
        System.out.println("There's a 75% chance an attack will miss, but when it does strike it does double damage.");
    }
}
