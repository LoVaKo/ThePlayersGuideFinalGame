package gamecharacters.monsters;

import attacks.basic.BasicAttack;
import attacks.special.SpecialAttack;
import defenses.Defense;
import gamecharacters.GameCharacter;
import gamecharacters.Party;
import inventory.equippables.weapons.WeaponType;
import main.Game;

public abstract class Monster extends GameCharacter {
    private static final Party monsterParty = new Party();
    final int level;

    public Monster(String name, BasicAttack basicAttack, SpecialAttack specialAttack, Defense defense, int hP, int level, WeaponType preferredWeaponType) {
        super(name,
                basicAttack,
                specialAttack,
                defense,
                monsterParty,
                gamecharacters.heroes.Hero.getHeroParty(),
                hP,
                preferredWeaponType);
        this.level = level;
        monsterParty.addCharacter(this);
    }

    public static Party getMonsterParty() {
        return monsterParty;
    }

    public static void setupMonsterParty(int numOfHeroes, Game game) {
        new Necromancer();
        //new Skeleton();

//        int numOfSkeletons = 0;
//        int numOfHealthPotions = 0;
//        int numOfDaggers = 0;
//
//        switch (game.getNumOfBattles()) {
//            case 0:
//                numOfSkeletons = numOfHeroes;
//                numOfHealthPotions = 1;
//                numOfDaggers = numOfHeroes;
//                break;
//            case 1:
//                numOfSkeletons = (int) Math.ceil(numOfHeroes * 1.5);
//                numOfHealthPotions = 2;
//                numOfDaggers = numOfSkeletons;
//                break;
//            case 2:
//                numOfSkeletons = numOfHeroes * 2;
//                numOfHealthPotions = 3;
//                numOfDaggers = numOfSkeletons;
//                break;
//            default:
//                // Handle default case if needed
//                break;
//        }
//
//        // Adding monsters
//        for (int i = 0; i < numOfSkeletons; i++) {
//            new Skeleton();
//        }
//
//        // Adding Health potions
//        for (int i = 0; i < numOfHealthPotions; i++) {
//            monsterParty.addInventoryItem(new HealthPotion());
//        }
//
//        // Adding Daggers
//        for (int i = 0; i < numOfDaggers; i++) {
//            monsterParty.addInventoryItem(new Dagger());
//        }
    }
}
