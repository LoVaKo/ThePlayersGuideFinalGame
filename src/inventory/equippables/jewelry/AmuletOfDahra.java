package inventory.equippables.jewelry;

import defenses.Regeneration;

public class AmuletOfDahra extends Jewelry {
    private final Regeneration regeneration;

    public AmuletOfDahra() {
        super("Amulet of Dahra",
                new Regeneration(),
                3);

        this.regeneration = (Regeneration) getDefense();
    }

    public Regeneration getRegeneration() {
        return regeneration;
    }

}
