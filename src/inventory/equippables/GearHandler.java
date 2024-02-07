package inventory.equippables;

import inventory.equippables.armor.Armor;
import inventory.equippables.jewelry.Jewelry;
import inventory.equippables.weapons.Weapon;

public class GearHandler {
    private Weapon weapon;
    private Jewelry jewelry;
    private Armor armor;

    public GearHandler() {
        this.weapon = null;
        this.jewelry = null;
        this.armor = null;
    }

    public boolean hasGear() {
        return weapon != null ||
                armor != null ||
                jewelry != null;
    }

    public boolean hasWeapon() {
        return weapon != null;
    }

    public boolean hasArmor() {
        return armor != null;
    }

    public boolean hasJewelry() {
        return jewelry != null;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public Jewelry getJewelry() {
        return jewelry;
    }

    public void setJewelry(Jewelry jewelry) {
        this.jewelry = jewelry;
    }
}
