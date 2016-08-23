package klassen.matt.dndproject.model.creature;

import klassen.matt.dndproject.model.actions.Action;
import klassen.matt.dndproject.model.actions.Item;
import klassen.matt.dndproject.model.creature.exception.HitPointException;
import klassen.matt.dndproject.model.creature.exception.IllegalValueException;
import klassen.matt.dndproject.model.traits.AbilityScores;
import klassen.matt.dndproject.ui.CombatLog;

import java.util.*;

/**
 * Represents a creature
 */
public abstract class AbstractCreature extends Observable {

    /** The creature's name */
    private String name;
    /** The creature's type */
    private String creatureType;
    /** The armor class that must be met or exceeded to hit the creature  */
    private int armorClass;
    /** The hit point maximum of the creature */
    private int hitPoints;
    /** The current hit points of the creature */
    private int cHitPoints;
    /** Movement speed */
    private int speed;
    /** The creature's innate ability scores */
    private AbilityScores abilityScores;
    /** The actions the creature can perform */
    private Set<Action> actions;
    /** The spells the creature knows */
    private Set<Item> items;
    /** Whether the creature is alive (true) or dead (false) */
    private boolean alive;
    /** The global combat log */
    private CombatLog combatLog;

    /**
     * Constructor
     *
     * @param name              the name of the creature
     * @param creatureType      the creature's type
     * @param armorClass        the creature's innate (unmodified) armor class
     * @param hitPoints         the creature's innate hit point total (unmodified)
     * @param speed             the creature's innate speed (unmodified)
     * @param abilityScores     the creature's set of base ability scores
     * @param actions           the set of actions available to the creature
     */
    public AbstractCreature(String name, String creatureType,
                            int armorClass, int hitPoints, int speed,
                            AbilityScores abilityScores,
                            Set<Action> actions) {
        this.name = name;
        this.creatureType = creatureType;
        this.armorClass = armorClass;
        this.hitPoints = hitPoints;
        cHitPoints = hitPoints;
        this.speed = speed;
        this.abilityScores = abilityScores;
        this.actions = actions;
        items = new HashSet<Item>();
        alive = true;
        combatLog = CombatLog.getInstance();
        // TODO: Observers for dead/alive status
    }

    /**
     * Subtracts the damage taken from the creature's pool of current HitPoints
     *
     * @param dmg the amount of damage to be taken by the creature
     * @throws IllegalValueException
     */
    public void takeDamage(int dmg) throws IllegalValueException {
        if (dmg < 0) { throw new IllegalValueException(); }
        int newHP = cHitPoints - dmg;

        combatLog.message(name + " takes " + dmg + " points of damage.");
        try {
            setCHitPoints(newHP);
        } catch (HitPointException e) {
            cHitPoints = 0;
        } finally {
            if (cHitPoints == 0) {
                alive = false;
                combatLog.message(name + " has died!");
            }
        }
    }

    /**
     * Adds the amount healed to the creature's pool of current HitPoints
     *
     * @param heal the amount of hitpoints to restored to the creature
     * @throws IllegalValueException
     */
    public void heal(int heal) throws IllegalValueException {
        if (heal < 0) { throw new IllegalValueException(); }
        int newHP = cHitPoints + heal;
        combatLog.message(name + " heals for " + heal + " hit points.");
        try {
            setCHitPoints(newHP);
        } catch (HitPointException e) {
            cHitPoints = hitPoints;
        }
    }

    /**
     * Sets the value of current hitpoints to an int from 0 to the hitpoint
     * maximum (inclusive), or throws a HitPointException
     *
     * @param newHP the new HP value to be set
     * @throws HitPointException
     */
    public void setCHitPoints(int newHP) throws HitPointException {
        if (newHP < 0) {
            throw new HitPointException("Cannot set HP to a negative value.");
        } else if (newHP > hitPoints) {
            throw new HitPointException("Cannot set current HP above HP maximum.");
        }
        cHitPoints = newHP;
    }

    /**
     * Changes the alive/dead status of the creature, where
     * the creature is alive if true, dead if false
     *
     * @param alive the new alive status for the creature
     */
    public void setAlive(boolean alive) {
        this.alive = alive;
        this.setChanged();
        this.notifyObservers();
    }

    public void addItem(Item i) {
        items.add(i);
        i.setOwner(this);
    }

    public void removeItem(Item i) {
        items.remove(i);
        i.setOwner(null);
    }

    public Set<Item> getItems() {
        return items;
    }

    public String getName() {
        return name;
    }

    public String getCreatureType() {
        return creatureType;
    }

    public int getArmorClass() {
        return armorClass;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public int getCHitPoints() { return cHitPoints; }

    public int getSpeed() {
        return speed;
    }

    public AbilityScores getAbilityScores() {
        return abilityScores;
    }

    public Set<Action> getActions() {
        return Collections.unmodifiableSet(actions);
    }

    public boolean getAlive() { return alive; }
}
