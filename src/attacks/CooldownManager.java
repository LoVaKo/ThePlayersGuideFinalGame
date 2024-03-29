package attacks;

import attacks.effect.EffectAttack;
import statuseffects.StatusEffect;

import java.util.ArrayList;

public class CooldownManager {

    private final ArrayList<EffectAttack> specialAttackCooldown = new ArrayList<>();
    private final ArrayList<StatusEffect> activeStatusEffects = new ArrayList<>();
    private final ArrayList<StatusEffect> statusEffectCooldown = new ArrayList<>();
    private final ArrayList<EffectAttack> attacksToBeRemoved = new ArrayList<>();
    private final ArrayList<StatusEffect> activeEffectsToBeRemoved = new ArrayList<>();
    private final ArrayList<StatusEffect> cooldownEffectsToBeRemoved = new ArrayList<>();

    public CooldownManager() {

    }

    public void add(Object object) {

        if (object instanceof EffectAttack) {
            addSpecialAttack((EffectAttack) object);
        } else if (object instanceof StatusEffect) {
            addStatusEffect((StatusEffect) object);
        }
    }

    private void remove() {
        for (StatusEffect effect : cooldownEffectsToBeRemoved) {
            statusEffectCooldown.remove(effect);
            effect.setOnCooldown(false);
        }

        for (StatusEffect effect : activeEffectsToBeRemoved) {
            activeStatusEffects.remove(effect);
            effect.setActive(false);
        }

        for (EffectAttack attack : attacksToBeRemoved) {
            specialAttackCooldown.remove(attack);
            attack.setOnCooldown(false);
        }

        cooldownEffectsToBeRemoved.clear();
        activeEffectsToBeRemoved.clear();
        attacksToBeRemoved.clear();
    }

    public void updateCooldowns() {
        // When the counter reaches zero, remove object from countdown.
        for (StatusEffect effect : statusEffectCooldown) {
            if (effect.getCooldownCounter() == 0) {
                cooldownEffectsToBeRemoved.add(effect);
            }
        }

        for (StatusEffect effect : activeStatusEffects) {
            if (effect.getActiveCounter() == 0) {
                activeEffectsToBeRemoved.add(effect);
            }
        }

        for (EffectAttack attack : specialAttackCooldown) {
            if (attack.getCounter() == 0) {
                attacksToBeRemoved.add(attack);
            }
        }

        remove();
    }

    public void clear() {
        cooldownEffectsToBeRemoved.clear();
        activeEffectsToBeRemoved.clear();
        attacksToBeRemoved.clear();
        specialAttackCooldown.clear();
        statusEffectCooldown.clear();
        activeStatusEffects.clear();
    }

    public void addSpecialAttack(EffectAttack attack) {
        specialAttackCooldown.add(attack);
        attack.setOnCooldown(true);
        attack.setCounter(attack.getNUM_ROUNDS_COOLDOWN());
    }

    public void addStatusEffect(StatusEffect effect) {
        statusEffectCooldown.add(effect);
        activeStatusEffects.add(effect);
        effect.setOnCooldown(true);
        effect.setActive(true);
        effect.setActiveCounter(effect.getNUM_ROUNDS_ACTIVE());
        effect.setCooldownCounter(effect.getNUM_ROUNDS_COOLDOWN());
    }

}
