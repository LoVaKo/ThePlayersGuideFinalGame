package Main;

import GameCharacters.GameCharacter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CharacterOrderManager {
    private static final ArrayList<GameCharacter> characterOrder = new ArrayList<>();
    private static final ReadWriteLock lock = new ReentrantReadWriteLock();

    public static void remove(GameCharacter character) {
        lock.writeLock();
        try {
            characterOrder.remove(character);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public static void add(GameCharacter character) {
        lock.writeLock().lock();
        try {
            characterOrder.add(character);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public static GameCharacter getCharacterAt(int index) {
        return characterOrder.get(index);
    }

    public ArrayList<GameCharacter> getOrder() {
        lock.readLock().lock();
        try {
            return new ArrayList<>(characterOrder);
        } finally {
            lock.readLock().unlock();
        }
    }

    public void determineOrder() {
        // Clearing current character order
        clear();

        // Creating a list with all characters and shuffling it
        ArrayList<GameCharacter> allCharacters = new ArrayList<>();
        allCharacters.addAll(GameCharacters.Heroes.Hero.getHeroParty().getCharacters());
        allCharacters.addAll(GameCharacters.Monsters.Monster.getMonsterParty().getCharacters());
        Collections.shuffle(allCharacters);

        // Copying to characterOrder
        lock.writeLock().lock();
        try {
            characterOrder.addAll(allCharacters);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void clear() {
        lock.writeLock().lock();
        try {
            characterOrder.clear();
        } finally {
            lock.writeLock().unlock();
        }
    }
}
