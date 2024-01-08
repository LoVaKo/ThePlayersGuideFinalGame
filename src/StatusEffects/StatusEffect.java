package StatusEffects;

import GameCharacters.GameCharacter;

public abstract class StatusEffect {
    private final String name;
    private final String description;
    private int numOfRounds;

    public StatusEffect(String name, String description, int numOfRounds) {
        this.name = name;
        this.description = description;
        this.numOfRounds = numOfRounds;
    }

    public void endRound(GameCharacter character) {
        numOfRounds--;
        if (numOfRounds == 0) {
            character.setEffect(null);
        }
    }

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
