package Attacks.Special;

import Attacks.Attack;
import Attacks.DamageType;
import GameCharacters.GameCharacter;
import Main.CooldownManager;
import StatusEffects.CoolDown;
import StatusEffects.StatusEffect;

public abstract class SpecialAttack extends Attack {
    private final StatusEffect effect;
    private final int NUM_ROUNDS_COOLDOWN;
    private final CooldownManager cooldownManager = Main.Battle.getCooldownManager();
    private boolean isOnCooldown;
    private int counter;

    public SpecialAttack(String name, DamageType damageType, double successRate, int MAX_DAMAGE, StatusEffect effect, int coolDown) {
        super(name,
                damageType,
                successRate,
                MAX_DAMAGE
        );
        this.effect = effect;
        this.NUM_ROUNDS_COOLDOWN = coolDown;
        this.isOnCooldown = false;
        this.counter = 0;
    }

    public void resolveEffect(GameCharacter target) {
        if (!target.hasEffect()) {
            System.out.println(target + " has been " + effect + "!");
            target.setEffect(effect);
            cooldownManager.add(effect);

        } else if (target.getEffect() instanceof CoolDown) {
            ((CoolDown) target.getEffect()).checkForCooldown(effect, target);
        }
    }

    public int getNUM_ROUNDS_COOLDOWN() {
        return NUM_ROUNDS_COOLDOWN;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public boolean isOnCooldown() {
        return isOnCooldown;
    }

    public void setOnCooldown(boolean onCooldown) {
        isOnCooldown = onCooldown;
    }

    public void countDownByOne() {
        this.counter--;
    }

    public void addToCooldownManager() {
        cooldownManager.add(this);
    }
}
