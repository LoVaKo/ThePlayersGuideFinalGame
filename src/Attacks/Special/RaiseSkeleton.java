package Attacks.Special;

import Attacks.DamageType;
import GameCharacters.GameCharacter;
import GameCharacters.Monsters.Skeleton;
import Main.CharacterOrderManager;
import Main.Party;

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
    }
}
