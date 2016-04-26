package klassen.matt.dndproject.ui;

import klassen.matt.dndproject.model.actions.Action;
import klassen.matt.dndproject.model.actions.Item;
import klassen.matt.dndproject.model.actions.Spell;
import klassen.matt.dndproject.model.actions.exception.SpellException;
import klassen.matt.dndproject.model.creature.Hero;
import klassen.matt.dndproject.model.creature.exception.LevelException;
import klassen.matt.dndproject.model.mechanics.Die;
import klassen.matt.dndproject.model.mechanics.Effect;
import klassen.matt.dndproject.model.traits.AbilityScores;
import klassen.matt.dndproject.model.traits.Feature;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

/**
 * The main UI window
 */
public class DnDCombat extends JFrame {

    private Hero pregen1;
    private Hero pregen2;
    private Hero pregen3;
    private Hero pregen4;

    public DnDCombat() {
        super("D&D Combat Sim v.0.1");
        initHeroes();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        pack();
        centerOnScreen();
        setVisible(true);
    }

    private void centerOnScreen() {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screen.width - getWidth()) / 2, (screen.height - getHeight()) / 2);
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

