package klassen.matt.dndproject.model.creature;

import klassen.matt.dndproject.model.actions.Action;
import klassen.matt.dndproject.model.actions.Item;
import klassen.matt.dndproject.model.actions.Spell;
import klassen.matt.dndproject.model.creature.exception.LevelException;
import klassen.matt.dndproject.model.traits.AbilityScores;
import klassen.matt.dndproject.model.traits.Feature;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Represents a heroic player character or NPC (as opposed to a generic NPC)
 */
public class Hero extends AbstractCreature {

    private int level;
    private String heroClass;

    private String background;
    private int experience;
    private List<Item> items;

    private static Levels[] levels;

    /**
     * Constructor
     *
     * @param name          the name of the creature
     * @param creatureType  the creature's type
     * @param armorClass    the creature's innate (unmodified) armor class
     * @param hitPoints     the creature's innate hit point total (unmodified)
     * @param speed         the creature's innate speed (unmodified)
     * @param flySpeed      the creature's innate flying speed (unmodified)
     * @param abilityScores the creature's set of base ability scores
     * @param senses        the set of the creature's senses
     * @param languages     the set of languages understood and spoken by the creature
     * @param actions       the set of actions available to the creature
     * @param spells        the set of spells the creature knows
     * @param features      the set of features the creature possesses
     * @param level         the level of the hero
     * @param heroClass     the hero's class
     */
    public Hero(String name, String creatureType, int armorClass, int hitPoints,
                int speed, int flySpeed, AbilityScores abilityScores,
                Set<String> senses, Set<String> languages, Set<Action> actions,
                Set<Spell> spells, Set<Feature> features,
                int level, String heroClass) throws LevelException {

        super(name, creatureType, armorClass, hitPoints, speed, flySpeed,
                abilityScores, senses, languages, actions, spells, features);

        setLevel(level);
        this.heroClass = heroClass;
        this.items = new ArrayList<Item>();
        initLevels();
    }


    public void setBackground(String background) {
        this.background = background;
    }

    /**
     * Adds newly gained experience to the hero's pool of experience, then checks
     * to see if the hero should level up.
     *
     * @param experienceGained  the amount of experience gained
     */
    public void gainExperience(int experienceGained) {

        experience += experienceGained;
        checkLevel();
    }

    /**
     * Manually levels up the hero by one level to a maximum of 20 and adjusts
     * experience pool to minimum amount required for the new level.
     *
     * @throws LevelException
     */
    public void levelUp() throws LevelException {

        if (level < 20) {
            int newExp = levels[level].getExpThreshold();
            level += 1;
            experience = newExp;
        } else {
            throw new LevelException("Hero already at maximum level");
        }
    }

    /**
     * Manually sets the hero's level and adjusts experience pool to minimum
     * amount required for the new level. Throws level exception if the level
     * passed as argument is greater than 20 or less than 1.
     *
     * @param level     the new level the hero will be set to
     */
    public void setLevel(int level) throws LevelException {

        if (level < 1 || level > 20) {
            throw new LevelException("Invalid level");
        }
        this.level = level;
        int newExp = levels[level - 1].getExpThreshold();
        experience = newExp;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) { }

    public int getLevel() {
        return level;
    }

    public int getExperience() {
        return experience;
    }

    public int getProfBonus() {
        return levels[level - 1].getProfBonus();
    }

    public String getBackground() {
        return background;
    }

    public String getHeroClass() {
        return heroClass;
    }

    /**
     * Initializes an an array of player levels
     */
    private static void initLevels() {
        levels = new Levels[] {Levels.LVL1, Levels.LVL2, Levels.LVL3, Levels.LVL4,
        Levels.LVL5, Levels.LVL6, Levels.LVL7, Levels.LVL8, Levels.LVL9, Levels.LVL10,
        Levels.LVL11, Levels.LVL12, Levels.LVL13, Levels.LVL14, Levels.LVL15, Levels.LVL16,
        Levels.LVL17, Levels.LVL18, Levels.LVL19, Levels.LVL20};
    }

    /**
     * Checks if hero's experience meets the threshold to level up, then
     * levels up the hero if necessary.
     */
    private void checkLevel() {
        if (level < 20) {
            int expToLevel = levels[level].getExpThreshold();

            if (experience >= expToLevel) {
                levelFromExperience();
            }
        }
    }

    /**
     * Levels up the hero from experience gain, leaving the hero's
     * pool of experience as it is.
     */
    private void levelFromExperience() {
        level += 1;
    }

}
