package klassen.matt.dndproject.tests;

import klassen.matt.dndproject.model.actions.Action;
import klassen.matt.dndproject.model.actions.Item;
import klassen.matt.dndproject.model.actions.Spell;
import klassen.matt.dndproject.model.actions.exception.SpellException;
import klassen.matt.dndproject.model.creature.Hero;
import klassen.matt.dndproject.model.creature.Monster;
import klassen.matt.dndproject.model.creature.exception.LevelException;
import klassen.matt.dndproject.model.mechanics.Battle;
import klassen.matt.dndproject.model.mechanics.Die;
import klassen.matt.dndproject.model.mechanics.Effect;
import klassen.matt.dndproject.model.traits.AbilityScores;
import klassen.matt.dndproject.model.traits.Feature;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.*;

/**
 * Created by Matt on 4/30/2016.
 */
public class BattleTest {

    private Battle testBattle;
    private List<Hero> heroes;
    private List<Monster> monsters;
    private Hero pregen1;
    private Hero pregen2;
    private Hero pregen3;
    private Hero pregen4;
    private Monster testMonster1;
    private Monster testMonster2;
    private Monster testMonster3;
    private Monster testMonster4;


    @Before
    public void setUp() {
        heroes = new ArrayList<>();
        monsters = new ArrayList<>();
        initHeroes();
        initMonsters();
        testBattle = new Battle(heroes, monsters);
    }

    @Test
    public void battleTest() {
        testBattle.selectCreature(pregen1);
        testBattle.selectCreature(testMonster1);

        Set<Action> heroActions = testBattle.getSelectedHero().getActions();
        Set<Action> monsterActions = testBattle.getSelectedMonster().getActions();
        assertTrue(heroActions.size() > 0 && monsterActions.size() > 0);
    }

    private void initHeroes() {
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

        heroes.add(pregen1);
        heroes.add(pregen2);
        heroes.add(pregen3);
        heroes.add(pregen4);
    }

    private void initMonsters() {
        AbilityScores as = new AbilityScores(10, 12, 10, 8, 10, 10);
        Set<String> sensesM = new HashSet<>();
        sensesM.add("Darkvision");
        Set<String> languagesM = new HashSet<>();
        languagesM.add("Draconic");
        Set<Action> actionsM = initActions();
        Set<Spell> spellsM = new HashSet<>();
        Set<Feature> featuresM = new HashSet<>();
        testMonster1 = new Monster("Kobold", "draconic", 10, 8, 30, 0, as, sensesM,
                languagesM, actionsM, spellsM, featuresM, 1, 50);
        testMonster2 = new Monster("Winged Kobold", "draconic", 10, 8, 30, 30, as, sensesM,
                languagesM, actionsM, spellsM, featuresM, 1, 75);
        testMonster3 = new Monster("Angry Kobold", "draconic", 11, 8, 30, 0, as, sensesM,
                languagesM, actionsM, spellsM, featuresM, 1, 50);
        testMonster4 = new Monster("Sleepy Kobold", "draconic", 9, 8, 30, 0, as, sensesM,
                languagesM, actionsM, spellsM, featuresM, 1, 50);

        monsters.add(testMonster1);
        monsters.add(testMonster2);
        monsters.add(testMonster3);
        monsters.add(testMonster4);
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

    private Set<Action> initActions() {
        Effect sword = new Effect(new Die("1d6"), "slashing");
        Action swordAction = new Action("Short Sword", sword);
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
