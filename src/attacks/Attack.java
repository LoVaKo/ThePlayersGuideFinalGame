package attacks;

import actionhandler.ActionMenu;
import attacks.special.SpecialAttack;
import gamecharacters.GameCharacter;
import gamecharacters.Party;

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

    public void useAttack(GameCharacter character, Party enemyParty, boolean isComputer) {
        // If it's the computer's turn, computer picks target
        GameCharacter target = isComputer ? pickTargetComputer(enemyParty) : null;

        // If it's a players turn, pickTarget menu opens
        if (!isComputer) {
            int pickedChoice = this.pickTarget(enemyParty);
            if (pickedChoice == 0) {
                // If player picked 0, go back to ActionMenu
                ActionMenu menu = new ActionMenu(character);
                menu.print();
                menu.pickAction();
                return;
            } else {
                target = enemyParty.getCharacter(pickedChoice - 1);
            }
        }

        // Use attack
        System.out.println(character + " used " + this.name + " on " + target);

        // Resolve attack
        if (isSuccessful()) {
            // Calculate attack damage
            int damage = calculateAttackDamage();

            // Check for character defenses and adjust damage accordingly
            if (target.hasDefense()) {
                int damagereduction = target.getDefense().activate(this.damageType);

                if (damagereduction >= damage) {
                    System.out.println("No damage was done!");
                    return;
                } else {
                    damage -= damagereduction;
                }
            }


            // Resolving damage done
            if (MAX_DAMAGE > 0) {
                System.out.println(this.name + " dealt " + damage + " damage to " + target);

                if (damage < target.getCurrentHP()) {
                    target.setCurrentHP(target.getCurrentHP() - damage);
                    System.out.println(target + " is now at " + target.getCurrentHP() + "/" + target.getStartingHP() + " HP.");
                } else {
                    System.out.println(target + " has been defeated!");

                    if (target.getEquippedItems().hasGear()) target.lootCharacter(character);
                    target.resolveDeath();
                }
            }

            // Resolve Special Attack
            if (this instanceof SpecialAttack
                    && !character.isDead()) {
                ((SpecialAttack) this).resolveEffect(target);
            }

        } else {
            System.out.println(character.getName() + " missed!");
        }

        // Whether attack is successful or not, add to cooldownmanager when necessary
        if (this instanceof SpecialAttack) {
            ((SpecialAttack) this).addToCooldownManager();
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

    protected int calculateAttackDamage() {
        return 0;
    }

    public boolean isSuccessful() {
        Random random = new Random();
        double rollForSuccess = random.nextDouble();
        return rollForSuccess <= SUCCESS_RATE;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return name;
    }
}
