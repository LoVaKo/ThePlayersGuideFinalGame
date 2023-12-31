package GameCharacters.Monsters;

import Attacks.Attack;
import Defenses.Defense;
import GameCharacters.GameCharacter;
import Inventory.Dagger;
import Inventory.HealthPotion;
import Main.Game;
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

    public static void setupMonsterParty(int numOfHeroes, Game game) {
        int numOfSkeletons = 0;
        int numOfHealthPotions = 0;
        int numOfDaggers = 0;

        switch (game.getNumOfBattles()) {
            case 0:
                numOfSkeletons = numOfHeroes;
                numOfHealthPotions = 1;
                numOfDaggers = numOfHeroes;
                break;
            case 1:
                numOfSkeletons = (int) Math.ceil(numOfHeroes * 1.5);
                numOfHealthPotions = 2;
                numOfDaggers = numOfSkeletons;
                break;
            case 2:
                numOfSkeletons = numOfHeroes * 2;
                numOfHealthPotions = 3;
                numOfDaggers = numOfSkeletons;
                break;
            default:
                // Handle default case if needed
                break;
        }

        // Adding monsters
        for (int i = 0; i < numOfSkeletons; i++) {
            new Skeleton();
        }

        // Adding Health potions
        for (int i = 0; i < numOfHealthPotions; i++) {
            monsterParty.addInventoryItem(new HealthPotion());
        }

        // Adding Daggers
        for (int i = 0; i < numOfDaggers; i++) {
            monsterParty.addInventoryItem(new Dagger());
        }
    }
}
