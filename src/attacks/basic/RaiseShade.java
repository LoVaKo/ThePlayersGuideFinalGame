package attacks.basic;

import attacks.DamageType;
import gamecharacters.GameCharacter;
import gamecharacters.Party;
import gamecharacters.monsters.Shade;
import main.CharacterOrderManager;

public class RaiseShade extends BasicAttack {
    public RaiseShade() {
        super("Raise Shade",
                DamageType.MAGICAL,
                0.9,
                0);
    }

    @Override
    public void useAttack(GameCharacter character, Party enemyParty, boolean isComputer) {
        System.out.println(character.getName() + " mumbles a spell, and darkness gathers...");
        System.out.println("A Shade forms!");
        Shade shade = new Shade();
        CharacterOrderManager.add(shade);
        // A shade is a type of GameCharacter and Monster, when a new instance is formed it is added to MonsterParty
    }

    @Override
    protected int getBaseDamage() {
        return 0;
    }
}
