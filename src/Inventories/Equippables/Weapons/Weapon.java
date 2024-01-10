package Inventories.Equippables.Weapons;

import Attacks.Attack;
import Inventories.Equippables.Gear;

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
}
