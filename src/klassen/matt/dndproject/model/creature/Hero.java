package klassen.matt.dndproject.model.creature;

import klassen.matt.dndproject.model.actions.Action;
import klassen.matt.dndproject.model.creature.exception.LevelException;
import klassen.matt.dndproject.model.creature.exception.NoNameException;
import klassen.matt.dndproject.model.traits.AbilityScores;
import klassen.matt.dndproject.model.traits.Levels;
import klassen.matt.dndproject.ui.CombatLog;

import java.util.Set;

/**
 * Represents a heroic player character or NPC (as opposed to a generic NPC)
 */
public class Hero extends AbstractCreature {

    private int level;
    private String heroClass;
    private String background;
    private int experience;
    private static Levels[] levels;

    /**
     * Constructor
     *
     * @param name          the name of the creature
     * @param creatureType  the creature's type
     * @param armorClass    the creature's innate (unmodified) armor class
     * @param hitPoints     the creature's innate hit point total (unmodified)
     * @param speed         the creature's innate speed (unmodified)
     * @param abilityScores the creature's set of base ability scores
     * @param actions       the set of actions available to the creature
     * @param level         the level of the hero
     * @param heroClass     the hero's class
     */
    public Hero(String name, String creatureType, int armorClass,
                int hitPoints, int speed, AbilityScores abilityScores,
                Set<Action> actions, int level, String heroClass)
            throws LevelException, NoNameException {

        super(name, creatureType, armorClass, hitPoints,
                speed, abilityScores, actions);

        initLevels();
        setLevel(level);
        this.heroClass = heroClass;
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
        CombatLog combatLog = CombatLog.getInstance();
        combatLog.message(this.getName() + " has gained "
                + experienceGained + " experience.");
        checkLevel();
    }

    /**
     * Manually levels up the hero by one level to a maximum of 20 and adjusts
     * experience pool to minimum amount required for the new level.
     *
     * @throws LevelException
     */
    public void levelUp() {

        if (level < 20) {
            int newExp = levels[level].getExpThreshold();
            level += 1;
            experience = newExp;
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

            while (experience >= expToLevel && level < 20) {
                levelFromExperience();
                expToLevel = levels[level].getExpThreshold();
            }
        }
    }

    /**
     * Levels up the hero from experience gain, leaving the hero's
     * pool of experience as it is.
     */
    private void levelFromExperience() {
        level += 1;
        CombatLog combatLog = CombatLog.getInstance();
        combatLog.message(this.getName() + " has reached level " + level + ".");
    }

}
