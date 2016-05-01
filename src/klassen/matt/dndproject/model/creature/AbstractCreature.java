package klassen.matt.dndproject.model.creature;

import klassen.matt.dndproject.model.actions.Action;
import klassen.matt.dndproject.model.actions.Item;
import klassen.matt.dndproject.model.actions.Spell;
import klassen.matt.dndproject.model.creature.exception.HitPointException;
import klassen.matt.dndproject.model.creature.exception.IllegalValueException;
import klassen.matt.dndproject.model.traits.AbilityScores;
import klassen.matt.dndproject.model.traits.Feature;

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
    /** Movement speed while flying, 0 if creature cannot fly */
    private int flySpeed;
    /** The creature's innate ability scores */
    private AbilityScores abilityScores;
    /** Special seeing, hearing, sensing traits */
    private Set<String> senses;
    /** Set of languages the creature speaks or understands */
    private Set<String> languages;
    /** The actions the creature can perform */
    private Set<Action> actions;
    /** The spells the creature knows */
    private Set<Spell> spells;
    /** Passive traits that effect the creature */
    private Set<Feature> features;
    /** The set of items the creature possesses*/
    private Set<Item> items;
    /** Whether the creature is alive (true) or dead (false) */
    private boolean alive;

    /**
     * Constructor
     *
     * @param name              the name of the creature
     * @param creatureType      the creature's type
     * @param armorClass        the creature's innate (unmodified) armor class
     * @param hitPoints         the creature's innate hit point total (unmodified)
     * @param speed             the creature's innate speed (unmodified)
     * @param flySpeed          the creature's innate flying speed (unmodified)
     * @param abilityScores     the creature's set of base ability scores
     * @param senses            the set of the creature's senses
     * @param languages         the set of languages understood and spoken by the creature
     * @param actions           the set of actions available to the creature
     * @param spells            the set of spells the creature knows
     * @param features          the set of features the creature possesses
     */
    public AbstractCreature(String name, String creatureType, int armorClass,
                            int hitPoints, int speed, int flySpeed,
                            AbilityScores abilityScores, Set<String> senses,
                            Set<String> languages, Set<Action> actions,
                            Set<Spell> spells, Set<Feature> features) {
        this.name = name;
        this.creatureType = creatureType;
        this.armorClass = armorClass;
        this.hitPoints = hitPoints;
        cHitPoints = hitPoints;
        this.speed = speed;
        this.flySpeed = flySpeed;
        this.abilityScores = abilityScores;
        this.senses = senses;
        this.languages = languages;
        this.actions = actions;
        this.spells = spells;
        this.features = features;
        items = new HashSet<Item>();
        alive = true;
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

        try {
            setCHitPoints(newHP);
        } catch (HitPointException e) {
            cHitPoints = 0;
        } finally {
            if (cHitPoints == 0) {
                alive = false;
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

    public int getFlySpeed() {
        return flySpeed;
    }

    public AbilityScores getAbilityScores() {
        return abilityScores;
    }

    public Set<String> getSenses() {
        return Collections.unmodifiableSet(senses);
    }

    public Set<String> getLanguages() {
        return Collections.unmodifiableSet(languages);
    }

    public Set<Spell> getSpells() { return Collections.unmodifiableSet(spells); }

    public Set<Action> getActions() {
        return Collections.unmodifiableSet(actions);
    }

    public Set<Feature> getFeatures() {
        return Collections.unmodifiableSet(features);
    }

    public boolean getAlive() { return alive; }

    public void setDead() {
        alive = false;
        this.setChanged();
        this.notifyObservers();
    }

    public void setAlive() { alive = true; }

}
