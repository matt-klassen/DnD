package klassen.matt.dndproject.ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import klassen.matt.dndproject.model.WorldDND;
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

import java.util.HashSet;
import java.util.Set;

/**
 * @author Matt Klassen <matt.klassen@yahoo.ca>
 * @version 0.1
 * @since 2016-05-01
 *
 * The main UI window
 */
public class DnDCombat extends Application {
    // TODO add Javadoc
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final double VERTICAL_GAP = 8;
    public static final double HORIZONTAL_GAP = 10;
    public static final Insets LARGE_INSET = new Insets(10, 10, 10, 10);
    public static final Insets SMALL_INSET = new Insets(4, 4, 4, 4);
    private static final BorderStroke[] BORDERS = { new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
            new CornerRadii(1), BorderStroke.THIN) };
    public static final Border BORDER = new Border(BORDERS);

    private Stage window;
    private Scene scene;
    private GridPane grid;
    private BorderPane layout;
    private MenuBar menuBar;
    private WorldDND world;
    private GroupBox partyVBox;
    private GroupBox monsterVBox;
    private TextArea combatLog;
    private ActionBox heroActionsVBox;
    private ActionBox monsterActionsVBox;
    private PregenBox pregenVBox;

    private Hero pregen1;
    private Hero pregen2;
    private Hero pregen3;
    private Hero pregen4;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        initHeroes();
        initGrid();
        initMenu();
        initLayout();
        initWindow();
    }

    private void initGrid() {
        grid = new GridPane();
        grid.setPadding(LARGE_INSET);
        grid.setVgap(VERTICAL_GAP);
        grid.setHgap(HORIZONTAL_GAP);
        initGridItems();

        grid.getChildren().addAll(partyVBox, monsterVBox, combatLog,
                heroActionsVBox, monsterActionsVBox, pregenVBox);
    }

    private void initGridItems() {
        // Creating grid items
        partyVBox = new GroupBox("Heroes");
        monsterVBox = new GroupBox("Monsters");
        combatLog = new CombatLog();
        heroActionsVBox = new ActionBox();
        monsterActionsVBox = new ActionBox();
        pregenVBox = new PregenBox();
        partyVBox.pair(heroActionsVBox);
        monsterVBox.pair(monsterActionsVBox);

        // Setting grid margins and constraints
        GridPane.setConstraints(partyVBox, 0, 0);
        GridPane.setMargin(partyVBox, new Insets(0, 0, 0, 10));
        GridPane.setConstraints(monsterVBox, 1, 0);
        GridPane.setConstraints(combatLog, 2, 0);
        GridPane.setConstraints(heroActionsVBox, 0, 1);
        GridPane.setMargin(heroActionsVBox, new Insets(0, 0, 0, 10));
        GridPane.setConstraints(monsterActionsVBox, 1, 1);
        GridPane.setConstraints(pregenVBox, 2, 1);

        // TODO testing area below
        partyVBox.addCreature(pregen1);
        partyVBox.addCreature(pregen2);
        partyVBox.addCreature(pregen3);
        partyVBox.addCreature(pregen4);
    }

    private void initMenu() {
        MenuItem newFile = new MenuItem("_New");
        // MenuItem saveFile = new MenuItem("Save");
        // MenuItem loadFile = new MenuItem("Load");
        Menu fileMenu = new Menu("_File");
        fileMenu.getItems().addAll(newFile); // TODO: add save/load functionality

        MenuItem newHero = new MenuItem("New _Hero...");
        MenuItem newMonster = new MenuItem("New _Monster...");
        Menu customizeMenu = new Menu("_Customize");
        customizeMenu.getItems().addAll(newHero, newMonster);

        // Menu optionsMenu = new Menu("_Options"); // TODO: style option functionality

        menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, customizeMenu);
    }

    private void initLayout() {
        layout = new BorderPane();
        layout.setTop(menuBar);
        layout.setCenter(grid);
    }

    private void initWindow() {
        scene = new Scene(layout, WIDTH, HEIGHT);
        window.setScene(scene);
        window.setTitle("D&D Combat Sim v0.1");
        window.show();
    }

    // TODO move non-ui & pregen character behaviours to WorldDnD class

    public void initHeroes() {
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

    public Set<Action> initBasicActions() {
        Effect punch = new Effect(new Die("1d1"), "bludgeoning");
        Action punchAction = new Action("Punch", punch);
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

    public Set<Spell> initStarterSpells() {
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

