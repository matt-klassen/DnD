package klassen.matt.dndproject.tests;

import klassen.matt.dndproject.model.actions.Action;
import klassen.matt.dndproject.model.creature.exception.IllegalValueException;
import klassen.matt.dndproject.model.creature.exception.NoNameException;
import klassen.matt.dndproject.model.mechanics.Die;
import klassen.matt.dndproject.model.mechanics.Effect;
import klassen.matt.dndproject.model.traits.AbilityScores;
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
    public void setUp() throws NoNameException {
        AbilityScores as = new AbilityScores(10, 12, 10, 8, 10, 10);
        Set<Action> actionsM = initActions();
        testMonster = new Monster("Kobold", "draconic", 10, 8, 30, as, actionsM, 1, 50);
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
