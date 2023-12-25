package Inventory;

import Attacks.Slash;

public class Sword extends Gear {
    public Sword() {
        super("Sword",
                new Slash());
    }
}
