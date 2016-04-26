package klassen.matt.dndproject.model.creature;


import klassen.matt.dndproject.model.actions.Action;
import klassen.matt.dndproject.model.actions.Spell;
import klassen.matt.dndproject.model.traits.AbilityScores;
import klassen.matt.dndproject.model.traits.Feature;

import java.util.List;
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
     * @param flySpeed          the creature's innate flying speed (unmodified)
     * @param abilityScores     the creature's set of base ability scores
     * @param senses            the set of the creature's senses
     * @param languages         the set of languages understood and spoken by the creature
     * @param actions           the set of actions available to the creature
     * @param spells            the set of spells the creature knows
     * @param features          the set of features the creature possesses
     * @param challengeRating   the monster's challenge rating
     * @param experience        the experience awarded for defeating the monster
     */
    public Monster(String name, String creatureType, int armorClass,
                   int hitPoints, int speed, int flySpeed,
                   AbilityScores abilityScores, Set<String> senses,
                   Set<String> languages, Set<Action> actions,
                   Set<Spell> spells, Set<Feature> features,
                   int challengeRating, int experience) {

        super(name, creatureType, armorClass, hitPoints, speed, flySpeed,
                abilityScores, senses, languages, actions, spells, features);

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
