package klassen.matt.dndproject.tests;

import klassen.matt.dndproject.model.actions.Action;
import klassen.matt.dndproject.model.actions.Spell;
import klassen.matt.dndproject.model.creature.exception.IllegalValueException;
import klassen.matt.dndproject.model.mechanics.Die;
import klassen.matt.dndproject.model.mechanics.Effect;
import klassen.matt.dndproject.model.traits.AbilityScores;
import klassen.matt.dndproject.model.traits.Feature;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import klassen.matt.dndproject.model.creature.Monster;
import static org.junit.Assert.fail;
import java.util.HashSet;
import java.util.Set;

/**
 * Tests for Monster class
 */
public class MonsterTest {

    private Monster testMonster;
    private Action swordAction;

    @Before
    public void setUp() {
        AbilityScores as = new AbilityScores(10, 12, 10, 8, 10, 10);
        Set<String> sensesM = new HashSet<>();
        sensesM.add("Darkvision");
        Set<String> languagesM = new HashSet<>();
        languagesM.add("Draconic");
        Set<Action> actionsM = initActions();
        Set<Spell> spellsM = new HashSet<>();
        Set<Feature> featuresM = new HashSet<>();
        testMonster = new Monster("Kobold", "draconic", 10, 8, 30, 0, as, sensesM, languagesM, actionsM, spellsM, featuresM, 1, 50);
    }

    @Test
    public void testMonsterConstructor() {
        assertTrue(testMonster.getActions().contains(swordAction));
        assertEquals("Kobold", testMonster.getName());
    }

    @Test
    public void testMonsterDamage() {
        try {
            testMonster.takeDamage(10);
        } catch (IllegalValueException e) {
            fail("Should not throw exception");
        }
        assertTrue(!testMonster.getAlive());
    }

    private Set<Action> initActions() {
        Effect sword = new Effect(new Die("1d6"), "slashing");
        swordAction = new Action("Short Sword", sword);
        Action dash = new Action("Dash");
        Action disengage = new Action("Disengage");
        Action dodge = new Action("Dodge");
        Action hide = new Action("Hide");

        Set<Action> actions = new HashSet<Action>();
        actions.add(swordAction);
        actions.add(dash);
        actions.add(disengage);
        actions.add(dodge);
        actions.add(hide);

        return actions;
    }

}
