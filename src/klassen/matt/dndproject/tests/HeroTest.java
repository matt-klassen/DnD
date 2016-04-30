package klassen.matt.dndproject.tests;
import klassen.matt.dndproject.model.actions.Action;
import klassen.matt.dndproject.model.actions.Item;
import klassen.matt.dndproject.model.actions.Spell;
import klassen.matt.dndproject.model.actions.exception.SpellException;
import klassen.matt.dndproject.model.creature.Hero;
import klassen.matt.dndproject.model.creature.exception.IllegalValueException;
import klassen.matt.dndproject.model.creature.exception.LevelException;
import klassen.matt.dndproject.model.mechanics.Die;
import klassen.matt.dndproject.model.mechanics.Effect;
import klassen.matt.dndproject.model.traits.AbilityScores;
import klassen.matt.dndproject.model.traits.Feature;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Tests for Hero class
 */
public class HeroTest {

    private Hero pregen1;
    private Hero pregen2;
    private Hero pregen3;
    private Hero pregen4;


    @Before
    public void setUp() {
        AbilityScores ascores1 = new AbilityScores(15, 13, 14, 8, 12, 10);
        AbilityScores ascores2 = new AbilityScores(8, 12, 13, 15, 10, 11);
        AbilityScores ascores3 = new AbilityScores(10, 15, 12, 8, 14, 13);
        AbilityScores ascores4 = new AbilityScores(16, 14, 12, 8, 10, 10);
        Set<String> sensesWithDarkvision = new HashSet<String>();
        Set<String> sensesWithoutDarkvision = new HashSet<String>();
        sensesWithDarkvision.add("Darkvision");
        Set<Action> actionSet = initBasicActions();
        Set<String> languages = new HashSet<String>();
        languages.add("Common");
        Set<Spell> emptySpellSet = new HashSet<Spell>();
        Set<Spell> spellSet = initStarterSpells();
        Set<Feature> featureSet = new HashSet<Feature>();
        Effect qsAttack = new Effect(new Die("1d8"), "bludgeoning");
        Effect lsAttack = new Effect(new Die("2d6"), "slashing");
        Effect gaAttack = new Effect(new Die("1d12"), "slashing");
        Item quarterstaff = new Item("Quarterstaff", qsAttack, false);
        Item longSword = new Item("Longsword", lsAttack, false);
        Item greatAxe = new Item("Great Axe", gaAttack, false);

        try {
            pregen1 = new Hero("Crush", "Dragonborn", 13, 12, 30, 0, ascores1, sensesWithDarkvision,
                    languages, actionSet, emptySpellSet, featureSet, 1, "Barbarian");
            pregen1.addItem(greatAxe);
            pregen2 = new Hero("Quofiz", "Gnome", 11, 8, 25, 0, ascores2, sensesWithDarkvision,
                    languages, actionSet, spellSet, featureSet, 1, "Wizard");
            pregen3 = new Hero("Vei", "Human", 14, 10, 30, 0, ascores3, sensesWithoutDarkvision,
                    languages, actionSet, emptySpellSet, featureSet, 1, "Monk");
            pregen3.addItem(quarterstaff);
            pregen4 = new Hero("Tain", "Human", 13, 15, 30, 0, ascores4, sensesWithoutDarkvision,
                    languages, actionSet, emptySpellSet, featureSet, 1, "Fighter");
            pregen4.addItem(longSword);
        } catch (LevelException e) {
            throw new RuntimeException(); // TODO: probably not good to keep this
        }
    }

    @Test
    public void constructionTest() {
        assertEquals("Crush", pregen1.getName());
        assertEquals("Dragonborn", pregen1.getCreatureType());
        assertTrue(pregen1.getSenses().contains("Darkvision"));
        assertTrue(pregen1.getSpells().isEmpty());
        assertTrue(pregen1.getItems().size() > 0);
        assertEquals("Barbarian", pregen1.getHeroClass());
    }

    @Test
    public void damageTest(){
        try {
            pregen1.takeDamage(100);
            assertEquals(0, pregen1.getCHitPoints());
            pregen1.heal(100);
            assertEquals(pregen1.getHitPoints(), pregen1.getCHitPoints());
            pregen1.takeDamage(5);
            assertEquals(pregen1.getHitPoints() - 5, pregen1.getCHitPoints());
        } catch (IllegalValueException e) {
            fail("No exception should be thrown");
        }
    }

    @Test
    public void levelTest() {
        try {
            pregen2.gainExperience(299);
            assertEquals(1, pregen2.getLevel());
            pregen2.gainExperience(100);
            assertEquals(2, pregen2.getLevel());
            assertEquals(399, pregen2.getExperience());
            pregen2.setLevel(3);
            assertEquals(900, pregen2.getExperience());
            assertEquals(3, pregen2.getLevel());
        } catch (IllegalValueException e) {
            fail("No exception should be thrown");
        }
    }








    private Set<Action> initBasicActions() {
        Effect punch = new Effect(new Die("1d1"), "bludgeoning");
        Action punchAction = new Action("punch", punch);
        Action dash = new Action("Dash");
        Action disengage = new Action("Disengage");
        Action dodge = new Action("Dodge");
        Action hide = new Action("Hide");

        Set<Action> basicActions = new HashSet<Action>();
        basicActions.add(punchAction);
        basicActions.add(dash);
        basicActions.add(disengage);
        basicActions.add(dodge);
        basicActions.add(hide);

        return basicActions;
    }

    private Set<Spell> initStarterSpells() {
        Set<Spell> basicSpells = new HashSet<Spell>();
        try {
            Effect mm = new Effect(new Die("3d4"), "force");
            Spell magicMissile = new Spell("Magic Missile", 1, mm);
            Effect fb = new Effect(new Die("1d10"), "fire");
            Spell fireBolt = new Spell("Firebolt", fb);
        } catch (SpellException e) {
            Effect fb = new Effect(new Die("1d10"), "fire");
            Spell fireBolt = new Spell("Firebolt", fb);
            basicSpells.add(fireBolt);
        }
        return basicSpells;
    }

}
