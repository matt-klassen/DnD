package klassen.matt.dndproject.model.mechanics;

import klassen.matt.dndproject.model.creature.AbstractCreature;
import klassen.matt.dndproject.model.creature.Hero;
import klassen.matt.dndproject.model.creature.Monster;

import java.util.List;

/**
 * Represents a battle between two opposing groups of creatures
 */
public class Battle {

    /** The first group of creatures doing battle, opponents of the second */
    private List<Hero> heroes;
    /** The second group of creatures doing battle, opponents of the first */
    private List<Monster> monsters;
    /** The currently selected creature from the first group */
    private Hero selectedHero;
    /** The currently selected creature from the second group */
    private Monster selectedMonster;

    /**
     * Constructor
     *
     * @param heroes the first group in the battle (the heroes)
     * @param monsters the second group in the battle (the monsters)
     */
    public Battle(List<Hero> heroes, List<Monster> monsters) {
        this.heroes = heroes;
        this.monsters = monsters;
    }

    public List<Hero> getHeroes() {
        return heroes;
    }

    public List<Monster> getMonsters() {
        return monsters;
    }

    /**
     * Changes the status of the given creature to selected, either as group 1 selected
     * or group 2 selected, depending on what group that creature is in.
     *
     * @param creature the creature to be selected
     */
    public void selectCreature(AbstractCreature creature) {
        if (heroes.contains(creature)) {
            selectedHero = (Hero) creature;
        } else if (monsters.contains(creature)) {
            selectedMonster = (Monster) creature;
        }
    }

    public AbstractCreature getSelectedHero() {
        return selectedHero;
    }

    public AbstractCreature getSelectedMonster() {
        return selectedMonster;
    }
//TODO evaluate if I need this class even...
}
