package attacks;

import actionhandler.ActionMenu;
import attacks.effect.EffectAttack;
import gamecharacters.GameCharacter;
import gamecharacters.Party;
import statuseffects.Enraged;
import statuseffects.Frozen;
import statuseffects.Inspired;

import java.util.Random;
import java.util.Scanner;

public abstract class Attack {
    public final int MAX_DAMAGE;
    private final double SUCCESS_RATE;
    protected String name;
    protected DamageType damageType;


    public Attack(String name, DamageType damageType, double successRate, int MAX_DAMAGE) {
        this.name = name;
        this.damageType = damageType;
        this.SUCCESS_RATE = successRate;
        this.MAX_DAMAGE = MAX_DAMAGE;
    }

    public void useAttack(GameCharacter currentCharacter, Party enemyParty, boolean isComputer) {
        // If it's the computer's turn, computer picks target
        GameCharacter target = isComputer ? pickTargetComputer(enemyParty) : null;

        // If it's a players turn, pickTarget menu opens
        if (!isComputer) {
            int pickedChoice = this.pickTarget(enemyParty);
            if (pickedChoice == 0) {
                // If player picked 0, go back to ActionMenu
                ActionMenu menu = new ActionMenu(currentCharacter);
                menu.print();
                menu.pickAction();
                return;
            } else {
                target = enemyParty.getCharacter(pickedChoice - 1);
            }
        }

        // Use attack
        System.out.println(currentCharacter + " used " + this.name + " on " + target);

        // Resolve attack
        if (isSuccessful(currentCharacter)) {

            // If the attack deals damage, resolve damage done
            if (MAX_DAMAGE > 0) {
                int damage = calculateAttackDamage(currentCharacter, target);

                if (damage == 0) {
                    System.out.println("No damage was done!");
                } else {
                    System.out.println(this.name + " dealt " + damage + " damage to " + target);

                    if (damage < target.getCurrentHP()) {
                        target.setCurrentHP(target.getCurrentHP() - damage);
                        System.out.println(target + " is now at " + target.getCurrentHP() + "/" + target.getStartingHP() + " HP.");
                    } else {
                        System.out.println(target + " has been defeated!");

                        if (target.getEquippedItems().hasGear()) target.lootCharacter(currentCharacter);
                        target.resolveDeath();
                    }
                }
            }

            // Resolve Special Attack
            if (this instanceof EffectAttack
                    && !target.isDead()) {
                ((EffectAttack) this).resolveEffect(target);
            }

        } else {
            System.out.println(currentCharacter.getName() + " missed!");
        }

        // Whether attack is successful or not, add to cooldown manager when necessary
        if (this instanceof EffectAttack) {
            ((EffectAttack) this).addToCooldownManager(currentCharacter);
        }

    }


    private int pickTarget(Party enemyParty) {
        Scanner scanner = new Scanner(System.in);

        // Printing out numbered list of targets
        System.out.println("Pick your target:");
        for (int i = 0; i < enemyParty.getCharacters().size(); i++) {
            int listNumber = i + 1;
            System.out.println(listNumber + ". " + enemyParty.getCharacters().get(i).getCharacterReport());
        }
        System.out.println("0. Go back");
        return scanner.nextInt();
    }

    private GameCharacter pickTargetComputer(Party enemyParty) {
        // If there's only one enemy, pick that enemy
        if (enemyParty.getCharacters().size() == 1) {
            return enemyParty.getCharacter(0);
        }

        // Computer logic: Go for the character with the lowest HP 75% of the time
        // Else, pick random character
        Random random = new Random();

        double rollForCharacterChoice = random.nextDouble();
        double RATE_FOR_LOW_HP_CHARACTER = 0.75;
        int indexOfChoice = 0;

        if (rollForCharacterChoice < RATE_FOR_LOW_HP_CHARACTER) {
            for (int i = 0; i < enemyParty.getCharacters().size() - 1; i++) {
                if (enemyParty.getCharacter(i).getCurrentHP() >= enemyParty.getCharacter(i + 1).getCurrentHP()) {
                    indexOfChoice = i + 1;
                }
            }
        } else {
            int numOfPossibleTargets = enemyParty.getCharacters().size();

            indexOfChoice = random.nextInt(numOfPossibleTargets);
        }

        return enemyParty.getCharacter(indexOfChoice);
    }

    protected abstract int getBaseDamage();

    private int calculateAttackDamage(GameCharacter currentCharacter, GameCharacter target) {
        int baseDamage = getBaseDamage();
        int defenseModifier = getDefenseModifier(target);
        int effectModifier = getEffectModifier(currentCharacter, target);

        return baseDamage + defenseModifier + effectModifier;
    }

    private int getDefenseModifier(GameCharacter target) {
        if (target.hasDefense()) {
            return -target.getDefense().activate(this.damageType);
        } else {
            return 0;
        }
    }

    private int getEffectModifier(GameCharacter currentCharacter, GameCharacter target) {
        int effectModifier = 0;
        if (currentCharacter.hasEffect()) {
            if (currentCharacter.getEffect() instanceof Inspired) {
                System.out.println(currentCharacter.getName() + " is Inspired. Damage is increased by 1.");
                effectModifier = effectModifier + 1;
            } else if (currentCharacter.getEffect() instanceof Enraged) {
                System.out.println(currentCharacter.getName() + " is Enraged. Damage is doubled.");
                effectModifier = getBaseDamage();
            }
        }
        if (target.hasEffect()) {
            if (target.getEffect() instanceof Frozen && this.damageType == DamageType.PHYSICAL) {
                System.out.println(target.getName() + " is frozen and less susceptible to physical damage. Damage is reduced by 1.");
                effectModifier = effectModifier - 1;
            }
        }
        return effectModifier;
    }

    public int getAttackSlot(GameCharacter character) {
        if (this.equals(character.getAttack1())) {
            return 1;
        } else if (this.equals(character.getAttack2())) {
            return 2;
        } else if (this.equals(character.getAttack3())) {
            return 3;
        } else if (character.getEquippedItems().hasWeapon()) {
            if (this.equals(character.getEquippedItems().getWeapon().getAttack())) {
                return 4;
            }
        }
        return 0;
    }

    public boolean isSuccessful(GameCharacter character) {
        Random random = new Random();
        double rollForSuccess = random.nextDouble();

        if (character.hasEffect()) {
            if (character.getEffect() instanceof Enraged) {
                // when enraged 75% of attacks will miss
                return rollForSuccess < 0.25;
            }
        }

        return rollForSuccess <= SUCCESS_RATE;
    }

    public String getName() {
        return this.name;
    }


    public boolean isOnCooldown() {
        if (this instanceof EffectAttack) {
            return this.isOnCooldown();
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
