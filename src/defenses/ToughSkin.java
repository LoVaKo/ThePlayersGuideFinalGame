package defenses;

import attacks.DamageType;

public class ToughSkin extends Defense {
    public ToughSkin() {
        super("Tough Skin",
                "tough skin protects against spells",
                0.5,
                DamageType.MAGICAL);
    }
}


