package klassen.matt.dndproject.model.mechanics;

import klassen.matt.dndproject.model.creature.AbstractCreature;

import java.util.List;

/**
 * Represents a battle between two opposing groups of creatures
 */
public class Battle {

    private List<AbstractCreature> firstGroup;
    private List<AbstractCreature> secondGroup;
    private AbstractCreature firstSelectedCreature;
    private AbstractCreature secondSelectedCreature;

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
