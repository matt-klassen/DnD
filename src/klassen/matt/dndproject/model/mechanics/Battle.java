package klassen.matt.dndproject.model.mechanics;

import klassen.matt.dndproject.model.actions.Item;
import klassen.matt.dndproject.model.creature.AbstractCreature;
import klassen.matt.dndproject.model.creature.Hero;
import klassen.matt.dndproject.model.creature.Monster;
import klassen.matt.dndproject.model.creature.exception.IllegalValueException;
import klassen.matt.dndproject.ui.CombatLog;
import klassen.matt.dndproject.ui.DnDCombat;
import klassen.matt.dndproject.ui.GroupBox;
import klassen.matt.dndproject.ui.HeroInfo;

import java.util.List;

/**
 * Methods for battle between two opposing creatures
 */
public class Battle {

    /**
     * Calculates the roll bonus modifier from a given ability score
     * @param score the given ability score
     * @return the roll adjustment modifier
     */
    private static int abilityScoreBonus(int score) {
        int bonus = score;
        bonus -= 10;
        bonus = bonus/2;
        return bonus;
    }

    /**
     * Handles an attempt of an attacker to hit a target. D20 roll plus modifiers
     * and bonuses are tried against a target's armor class.
     *
     * @param attacker the attacking creature
     * @param target the attacking creature's target
     * @return true if attacker hit the target, false otherwise
     */
    public static boolean rollToHit(AbstractCreature attacker, AbstractCreature target) {

        CombatLog combatLog = CombatLog.getInstance();
        int toHit = target.getArmorClass();
        int bonus = 0;
        if (attacker.getClass() == Hero.class) {
            Hero heroAttacker = (Hero) attacker;
            bonus += heroAttacker.getProfBonus();
        }
        bonus += Battle.abilityScoreBonus(attacker.getAbilityScores().getKeyScore());

        int roll = CommonDie.D20.getDie().roll();
        combatLog.message(attacker.getName() + " rolls to hit: " + roll + " + (modifier) " + bonus);
        roll += bonus;
        if (roll > toHit) {
            return true;
        } else {
            combatLog.message(attacker.getName() + " misses " + target.getName() + "!");
            return false;
        }
    }

    /**
     * Handles a creature's attempt to heal itself with a healing action
     *
     * @param creature the creature healing itself
     * @param actionEffect the effect of the heal
     */
    public static void handleHeal(AbstractCreature creature, Effect actionEffect) {
        try {
            creature.heal(actionEffect.rollDie());
            heroUpdate(creature);
        } catch (IllegalValueException e) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Handles an attack by an attacker on a target creature. Rolls to hit then applies
     * effects of attack if hit was successful. A creature that is killed is removed from
     * its UI group box and ownership of its items is transferred to the attacker
     *
     * @param attacker the attacking creature
     * @param target the attacker's target
     * @param actionEffect the effect of the attack
     * @param otherBox the UI box containing the target (for targets removal if killed)
     */

    public static void handleAttack(AbstractCreature attacker, AbstractCreature target,
                              Effect actionEffect, GroupBox otherBox) {
        if (rollToHit(attacker, target)) {
            try {
                target.takeDamage(actionEffect.rollDie() +
                        abilityScoreBonus(attacker.getAbilityScores().getKeyScore()));
                if (target.getClass() == Monster.class) {
                    if (!target.getAlive()) {
                        Hero hero = (Hero) attacker;
                        Monster monster = (Monster) target;
                        hero.gainExperience(monster.getExperience());
                    }
                }
                if (!target.getAlive()) { // delete target if killed
                    otherBox.removeCreature(target);
                    for (Item i : target.getItems()) {
                        gainItem(attacker, i);
                    }
                }
            } catch (IllegalValueException e) {
                throw new IllegalArgumentException();
            }
            heroUpdate(attacker);
            heroUpdate(target);
        }
    }

    /**
     * Transfers ownership of an item from a slain creature to the attacking creature
     * and refreshes UI to reflect newly accessible item
     *
     * @param attacker the creature that is obtaining a killed creature's item
     * @param i the item the creature is obtaining
     */
    private static void gainItem(AbstractCreature attacker, Item i) {
        if (!attacker.getItems().contains(i)) {
            attacker.addItem(i);
            CombatLog.getInstance().message(attacker.getName() +
                    " picks up " + i.getName() + ".");
        }
        if (attacker.getClass() == Hero.class) {
            Hero h = (Hero) DnDCombat.getInstance().getPartyBox().getSelectedCreature();
            DnDCombat.getInstance().getPartyBox().setSelectedCreature(h);
        } else {
            Monster m = (Monster) DnDCombat.getInstance().getMonsterBox().getSelectedCreature();
            DnDCombat.getInstance().getMonsterBox().setSelectedCreature(m);
        }
    }

    /**
     * If a given creature is a Hero, its information is updated in the UI
     * Hero information text box
     * @param creature the hero to be updated
     */
    private static void heroUpdate(AbstractCreature creature) {
        if (creature.getClass() == Hero.class) {
            if (!creature.getAlive()) {
                HeroInfo.getInstance(null).clear();
            } else {
                HeroInfo.displaySelectedHero((Hero) creature);
            }
        }
    }

}
