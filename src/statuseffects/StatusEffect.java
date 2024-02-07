package statuseffects;

import gamecharacters.GameCharacter;

public abstract class StatusEffect {
    private final String name;
    private final int NUM_ROUNDS_COOLDOWN;
    private final int NUM_ROUNDS_ACTIVE;
    private boolean isOnCooldown;
    private boolean isActive;
    private int activeCounter;
    private int cooldownCounter;

    public StatusEffect(String name, int numOfRoundsActive, int numOfRoundsCoolDown) {
        this.name = name;
        this.NUM_ROUNDS_ACTIVE = numOfRoundsActive;
        this.NUM_ROUNDS_COOLDOWN = numOfRoundsCoolDown;
        this.activeCounter = 0;
        this.cooldownCounter = 0;
        this.isOnCooldown = false;
        this.isActive = false;
    }

    // Mechanics
    @Override
    public String toString() {
        return name.toLowerCase();
    }

    public void apply(GameCharacter character) {

    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public int getNUM_ROUNDS_ACTIVE() {
        return NUM_ROUNDS_ACTIVE;
    }

    public int getNUM_ROUNDS_COOLDOWN() {
        return NUM_ROUNDS_COOLDOWN;
    }

    public int getActiveCounter() {
        return activeCounter;
    }

    public void setActiveCounter(int activeCounter) {
        this.activeCounter = activeCounter;
    }

    public int getCooldownCounter() {
        return cooldownCounter;
    }

    public void setCooldownCounter(int cooldownCounter) {
        this.cooldownCounter = cooldownCounter;
    }

    public void countDownByOneCooldown() {
        this.cooldownCounter--;
    }

    public void countDownByOneActive() {
        this.activeCounter--;
    }

    public boolean isOnCooldown() {
        return isOnCooldown;
    }

    public void setOnCooldown(boolean onCooldown) {
        isOnCooldown = onCooldown;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void removeFromCooldown() {
        // Mark to be removed by setting counters to zero, cooldownmanager handles the removal.
        setCooldownCounter(0);
        setActiveCounter(0);
    }
}
