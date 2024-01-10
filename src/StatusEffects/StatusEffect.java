package StatusEffects;

public abstract class StatusEffect {
    private final String name;
    private final String description;
    private final int NUM_ROUNDS_COOLDOWN;
    private boolean isOnCooldown;
    private final int NUM_ROUNDS_ACTIVE;
    private int counter;

    public StatusEffect(String name, String description, int numOfRounds, int coolDown) {
        this.name = name;
        this.description = description;
        this.NUM_ROUNDS_ACTIVE = numOfRounds;
        this.NUM_ROUNDS_COOLDOWN = coolDown;
        this.counter = 0;
        this.isOnCooldown = false;
    }

    // Mechanics
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

    public int getNUM_ROUNDS_ACTIVE() {
        return NUM_ROUNDS_ACTIVE;
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

    public void countDownByOne() {
        this.counter--;
    }

    public boolean isOnCooldown() {
        return isOnCooldown;
    }

    public void setOnCooldown(boolean onCooldown) {
        isOnCooldown = onCooldown;
    }
}
