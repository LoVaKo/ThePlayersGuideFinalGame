package StatusEffects;

import GameCharacters.GameCharacter;

public abstract class StatusEffect {
    private final String name;
    private final String description;
    private final int coolDown;
    private int numOfRounds;

    public StatusEffect(String name, String description, int numOfRounds, int coolDown) {
        this.name = name;
        this.description = description;
        this.numOfRounds = numOfRounds;
        this.coolDown = coolDown;
    }

    // Mechanics
    public void endRound(GameCharacter character) {
        numOfRounds--;
        if (numOfRounds == 0) {
            if (coolDown != 0) {
                StatusEffect previousEffect = character.getEffect();
                character.setEffect(new CoolDown(previousEffect));
            }
        }
    }

    @Override
    public String toString() {
        return name.toLowerCase();
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getNumOfRounds() {
        return numOfRounds;
    }
}
