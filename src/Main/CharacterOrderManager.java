package Main;

import GameCharacters.GameCharacter;

import java.util.ArrayList;
import java.util.Collections;

public class CharacterOrderManager {
    private static final ArrayList<GameCharacter> characterOrder = new ArrayList<>();

    public static void remove(GameCharacter character) {
        characterOrder.remove(character);
    }

    public ArrayList<GameCharacter> getOrder() {
        return characterOrder;
    }
    public void determineOrder() {
        // Clearing current character order
        characterOrder.clear();

        // Creating a list with all characters and shuffling it
        ArrayList<GameCharacter> allCharacters = new ArrayList<>();
        allCharacters.addAll(GameCharacters.Heroes.Hero.getHeroParty().getCharacters());
        allCharacters.addAll(GameCharacters.Monsters.Monster.getMonsterParty().getCharacters());
        Collections.shuffle(allCharacters);

        // Copying to characterOrder
        characterOrder.addAll(allCharacters);
    }

    public static void add(GameCharacter character) {
        characterOrder.add(character);
    }

    public static GameCharacter getCharacterAt(int index) {
        return characterOrder.get(index);
    }

    public void clear() {
        characterOrder.clear();
    }
}
