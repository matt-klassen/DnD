package klassen.matt.dndproject.model.creature;


import klassen.matt.dndproject.model.actions.Action;
import klassen.matt.dndproject.model.creature.exception.NoNameException;
import klassen.matt.dndproject.model.traits.AbilityScores;

import java.util.Set;

/**
 * Represents a monster in D&D 5e
 */
public class Monster extends AbstractCreature {

    private int challengeRating;
    private int experience;

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
     * @param challengeRating   the monster's challenge rating
     * @param experience        the experience awarded for defeating the monster
     */
    public Monster(String name, String creatureType, int armorClass,
                   int hitPoints, int speed, AbilityScores abilityScores,
                   Set<Action> actions, int challengeRating, int experience)
        throws NoNameException {

        super(name, creatureType, armorClass, hitPoints, speed,
                abilityScores, actions);

        this.challengeRating = challengeRating;
        this.experience = experience;
    }

    public int getChallengeRating() {
        return challengeRating;
    }

    public int getExperience() {
        return experience;
    }

}
