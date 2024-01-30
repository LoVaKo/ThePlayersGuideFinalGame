package Main;

import Attacks.Special.SpecialAttack;
import StatusEffects.StatusEffect;

import java.util.ArrayList;

public class CooldownManager {
    private final ArrayList<Object> objectsOnCooldown = new ArrayList<>();
    private final ArrayList<Object> objectsToBeRemoved = new ArrayList<>();


    public void add(Object object) {
        // Add to arraylist of objects on cooldown
        // Set onCooldown to true
        // Set counter to right number

        if (object instanceof SpecialAttack) {
            objectsOnCooldown.add(object);
            ((SpecialAttack) object).setOnCooldown(true);
            ((SpecialAttack) object).setCounter(((SpecialAttack) object).getNUM_ROUNDS_COOLDOWN());
        } else if (object instanceof StatusEffect) {
            objectsOnCooldown.add(object);
            ((StatusEffect) object).setOnCooldown(true);
            ((StatusEffect) object).setCounter(((StatusEffect) object).getNUM_ROUNDS_COOLDOWN());
        }
    }

    private void remove() {
        for (Object object : objectsToBeRemoved) {
            if (object instanceof SpecialAttack) {
                objectsOnCooldown.remove(object);
                ((SpecialAttack) object).setOnCooldown(false);
            } else if (object instanceof StatusEffect) {
                objectsOnCooldown.remove(object);
                ((StatusEffect) object).setOnCooldown(false);
            }
        }
        objectsToBeRemoved.clear();
    }

    public void updateCooldowns() {
        // Reduce counter on all objects by 1
        // When the counter reaches zero, remove object from countdown.

        for (Object object : objectsOnCooldown) {
            if (object instanceof SpecialAttack) {
                ((SpecialAttack) object).countDownByOne();
                if (((SpecialAttack) object).getCounter() == 0) {
                    objectsToBeRemoved.add(object);
                }
            }
            if (object instanceof StatusEffect) {
                ((StatusEffect) object).countDownByOne();
                if (((StatusEffect) object).getCounter() == 0) {
                    objectsToBeRemoved.add(object);
                }
            }
        }

        remove();
    }


    public void clear() {
        objectsOnCooldown.clear();
        objectsToBeRemoved.clear();
    }
}
