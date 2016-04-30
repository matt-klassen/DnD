package klassen.matt.dndproject.model.mechanics;

import klassen.matt.dndproject.model.creature.AbstractCreature;

import java.util.List;

/**
 * Represents a battle between two opposing groups of creatures
 */
public class Battle {

    /** The first group of creatures doing battle, oppenents of the second */
    private List<AbstractCreature> firstGroup;
    /** The second group of creatures doing battle, opponents of the first */
    private List<AbstractCreature> secondGroup;
    /** The currently selected creature from the first group */
    private AbstractCreature firstSelectedCreature;
    /** The currently selected creature from the second group */
    private AbstractCreature secondSelectedCreature;

    /**
     * Constructor
     *
     * @param firstGroup the first group in the battle
     * @param secondGroup the second group in the battle*
     */
    public Battle(List<AbstractCreature> firstGroup, List<AbstractCreature> secondGroup) {
        this.firstGroup = firstGroup;
        this.secondGroup = secondGroup;
    }

    public List<AbstractCreature> getFirstGroup() {
        return firstGroup;
    }

    public List<AbstractCreature> getSecondGroup() {
        return secondGroup;
    }

    /**
     * Changes the status of the given creature to selected, either as group 1 selected
     * or group 2 selected, depending on what group that creature is in.
     *
     * @param creature the creature to be selected
     */
    public void selectCreature(AbstractCreature creature) {
        if (firstGroup.contains(creature)) {
            firstSelectedCreature = creature;
        } else if (secondGroup.contains(creature)) {
            secondSelectedCreature = creature;
        }
    }

    public AbstractCreature getFirstSelectedCreature() {
        return firstSelectedCreature;
    }

    public AbstractCreature getSecondSelectedCreature() {
        return secondSelectedCreature;
    }

}
