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

    private static int abilityScoreBonus(int score) {
        int bonus = score;
        bonus -= 10;
        bonus = bonus/2;
        return bonus;
    }

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

    public static void handleHeal(AbstractCreature creature, Effect actionEffect) {
        try {
            creature.heal(actionEffect.rollDie());
            heroUpdate(creature);
        } catch (IllegalValueException e) {
            throw new IllegalArgumentException();
        }
    }

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
