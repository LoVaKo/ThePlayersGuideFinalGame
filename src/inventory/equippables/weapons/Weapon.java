package inventory.equippables.weapons;

import attacks.Attack;
import inventory.equippables.Gear;

public abstract class Weapon extends Gear {
    protected Attack attack;
    protected WeaponType type;

    public Weapon(String name, Attack attack, int level, WeaponType type) {
        super(name, level);
        this.attack = attack;
        this.type = type;

    }

    public Attack getAttack() {
        return attack;
    }

    public WeaponType getType() {
        return type;
    }
}
