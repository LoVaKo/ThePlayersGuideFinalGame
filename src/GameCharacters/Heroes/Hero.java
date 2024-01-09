package GameCharacters.Heroes;

import Attacks.Attack;
import Defenses.Defense;
import GameCharacters.GameCharacter;
import Main.Party;

public class Hero extends GameCharacter {
    private static final Party heroParty = new Party();

    public Hero(String name, Attack attack, Defense defense, int hP) {
        super(name,
                attack,
                defense,
                heroParty,
                GameCharacters.Monsters.Monster.getMonsterParty(),
                hP);

        heroParty.addCharacter(this);
    }

    public static Party getHeroParty() {
        return heroParty;
    }
}
