package StatusEffects;

import GameCharacters.GameCharacter;

public class Wounded extends StatusEffect {

    public Wounded() {
        super("Wounded",
                "lose 1 hp every round for 3 rounds",
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
            if (character.getEquippedItems().hasGear()) character.lootCharacter(character);
            character.getOwnParty().removeCharacter(character);
            character.setDead(true);
        }
    }
}
