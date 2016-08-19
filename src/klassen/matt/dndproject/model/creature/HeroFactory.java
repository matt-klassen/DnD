package klassen.matt.dndproject.model.creature;

import klassen.matt.dndproject.model.actions.Action;
import klassen.matt.dndproject.model.creature.exception.IllegalValueException;
import klassen.matt.dndproject.model.creature.exception.LevelException;
import klassen.matt.dndproject.model.creature.exception.NoNameException;
import klassen.matt.dndproject.model.mechanics.Die;
import klassen.matt.dndproject.model.mechanics.Effect;
import klassen.matt.dndproject.model.traits.AbilityScores;

import java.util.HashSet;
import java.util.Set;

/**
 * Creates new Heroes based on user input data
 */
public class HeroFactory {

    public static final int DEFAULT_MOVE_SPEED = 30;
    public static final int DEFAULT_BASE_HP = 10;

    public static Hero makeHero(String name, String heroClass,
                         String race, int level) throws NoNameException {
        try {
            if (name.length() == 0) {
                throw new NoNameException();
            }
            HeroClass starterClass = determineHeroClass(heroClass);
            Hero newHero = new Hero(name, race, starterClass.getAc(),
                    DEFAULT_BASE_HP, DEFAULT_MOVE_SPEED,
                    starterClass.getClassScores(), initBasicActions(),
                    level, starterClass.getClassName());
            newHero.addItem(starterClass.getStarterItem());

            return newHero;
        } catch (LevelException e) {
            throw new RuntimeException();
        } catch (IllegalValueException e) {
            throw new RuntimeException();
        }
    }

    public static Set<Action> initBasicActions() {
        Effect punch = new Effect(new Die("1d1"), "bludgeoning");
        Action punchAction = new Action("Punch", punch);
        Action dodge = new Action("Dodge");

        Set<Action> basicActions = new HashSet<Action>();
        basicActions.add(punchAction);
        basicActions.add(dodge);

        return basicActions;
    }

    private static HeroClass determineHeroClass(String classString) throws
            IllegalValueException {
        switch (classString) {
            case "Barbarian": return HeroClass.BARBARIAN;
            case "Bard": return HeroClass.BARD;
            case "Cleric": return HeroClass.CLERIC;
            case "Druid": return HeroClass.DRUID;
            case "Fighter": return HeroClass.FIGHTER;
            case "Monk": return HeroClass.MONK;
            case "Paladin": return HeroClass.PALADIN;
            case "Ranger": return HeroClass.RANGER;
            case "Rogue": return HeroClass.ROGUE;
            case "Sorcerer": return HeroClass.SORCERER;
            case "Warlock": return HeroClass.WARLOCK;
            case "Wizard": return HeroClass.WIZARD;
            default: throw new IllegalValueException("Not a valid class.");
        }
    }

}
