package attacks.special;

import attacks.DamageType;
import gamecharacters.GameCharacter;
import gamecharacters.Party;
import gamecharacters.heroes.Hero;
import gamecharacters.monsters.Skeleton;
import main.CharacterOrderManager;

public class RaiseSkeleton extends SpecialAttack {
    public RaiseSkeleton() {
        super("raise skeleton",
                DamageType.MAGICAL,
                50,
                0,
                null,
                3);
    }

    @Override
    public void useAttack(GameCharacter character, Party enemyParty, boolean isComputer) {
        System.out.println(character.getName() + " raises the Staff, the skies grow dark, thunder rumbles, and the ground splits open...");
        System.out.println("A Skeleton climbs out from the ground!");
        Skeleton skeleton = new Skeleton();
        CharacterOrderManager.add(skeleton);
        addToCooldownManager();

        // If staff is used by a hero, inform them of their mistake (Skeletons are monsters and always go in the Monster Party)
        if (character instanceof Hero) {
            System.out.println(character.getName() + " made a huge mistake... the Skeleton turns against the heroes!");
        }
    }

    @Override
    protected int getBaseDamage() {
        return 0;
    }
}
