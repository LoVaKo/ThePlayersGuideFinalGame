package GameCharacters.Monsters;

import Attacks.Attack;
import Defenses.Defense;
import GameCharacters.GameCharacter;
import Main.Party;

public abstract class Monster extends GameCharacter {
    private static final Party monsterParty = new Party();
    final int level;

    public Monster(String name, Attack attack, Defense defense, int hP, int level) {
        super(name,
                attack,
                defense,
                monsterParty,
                GameCharacters.Heroes.Hero.getHeroParty(),
                hP);
        this.level = level;

        monsterParty.addCharacter(this);
    }

    public static Party getMonsterParty() {
        return monsterParty;
    }
}
