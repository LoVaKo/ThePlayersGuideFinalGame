package statuseffects;

import gamecharacters.GameCharacter;

public class Wounded extends StatusEffect {

    public Wounded() {
        super("Wounded",
                3,
                3);
    }

    @Override
    public void apply(GameCharacter character) {
        System.out.println(character.getName() + "'s bloodloss causes 1 damage.");
        int damage = 1;

        if (damage < character.getCurrentHP()) {
            character.setCurrentHP(character.getCurrentHP() - damage);
            System.out.println(character + " is now at " + character.getCurrentHP() + "/" + character.getStartingHP() + " HP.");
        } else {
            System.out.println(character + " has been defeated!");
            character.dropLoot();
            character.resolveDeath();
        }
    }
}
