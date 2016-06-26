package klassen.matt.dndproject.ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import klassen.matt.dndproject.model.WorldDND;
import klassen.matt.dndproject.model.actions.Action;
import klassen.matt.dndproject.model.actions.Item;
import klassen.matt.dndproject.model.creature.Hero;
import klassen.matt.dndproject.model.creature.HeroFactory;
import klassen.matt.dndproject.model.creature.exception.LevelException;
import klassen.matt.dndproject.model.mechanics.Die;
import klassen.matt.dndproject.model.mechanics.Effect;
import klassen.matt.dndproject.model.traits.AbilityScores;
import klassen.matt.dndproject.ui.exception.TooManyCreaturesException;

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

    public void heroPopup() {
        NewHeroPopup hpopup = new NewHeroPopup(this);
        hpopup.display();
    }

    public void monsterPopup() {

    }

    protected GroupBox getPartyBox() {
        return partyVBox;
    }

    private void initGrid() {
        grid = new GridPane();
        initGridDimensions();
        initGridItems();
        grid.getChildren().addAll(partyVBox, monsterVBox, combatLog,
                heroActionsVBox, monsterActionsVBox, pregenVBox);
    }

    private void initGridDimensions() {
        grid.setPadding(LARGE_INSET);
        grid.setVgap(VERTICAL_GAP);
        grid.setHgap(HORIZONTAL_GAP);
    }

    private void initGridItems() {
        heroActionsVBox = new ActionBox(this);
        monsterActionsVBox = new ActionBox(this);
        partyVBox = new GroupBox("Heroes", heroActionsVBox, this);
        monsterVBox = new GroupBox("Monsters", monsterActionsVBox, this);
        combatLog = new CombatLog(this);
        pregenVBox = new PregenBox(this);
        initItemDimensions();
        // TODO testing area below
        try {
            partyVBox.addCreature(pregen1);
            partyVBox.addCreature(pregen2);
            partyVBox.addCreature(pregen3);
            partyVBox.addCreature(pregen4);
        } catch (TooManyCreaturesException e) {
            throw new RuntimeException();
        }
    }

    private void initItemDimensions() {
        GridPane.setConstraints(partyVBox, 0, 0);
        GridPane.setMargin(partyVBox, new Insets(0, 0, 0, 10));
        GridPane.setConstraints(monsterVBox, 1, 0);
        GridPane.setConstraints(combatLog, 2, 0);
        GridPane.setConstraints(heroActionsVBox, 0, 1);
        GridPane.setMargin(heroActionsVBox, new Insets(0, 0, 0, 10));
        GridPane.setConstraints(monsterActionsVBox, 1, 1);
        GridPane.setConstraints(pregenVBox, 2, 1);
    }

    private void initMenu() {
        Menu fileMenu = createFileMenu();
        Menu customizeMenu = createCustomizeMenu();
        menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, customizeMenu);
    }

    private Menu createFileMenu() {
        Menu fileMenu = new Menu("_File");
        MenuItem newFile = new MenuItem("_New");
        fileMenu.getItems().addAll(newFile);
        return fileMenu;
    }

    private Menu createCustomizeMenu() {
        Menu customizeMenu = new Menu("_Customize");
        MenuItem newHero = new MenuItem("New _Hero...");
        MenuItem newMonster = new MenuItem("New _Monster...");
        newHero.setOnAction(e -> heroPopup());
        customizeMenu.getItems().addAll(newHero, newMonster);
        return customizeMenu;
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
//       try {
            pregen1 = HeroFactory.makeHero("Crush",
                    "Barbarian", "Dragonborn", 1);
            pregen2 = HeroFactory.makeHero("Quofiz",
                    "Wizard", "Gnome", 1);
            pregen3 = HeroFactory.makeHero("Vei",
                    "Monk", "Human", 1);
            pregen4 = HeroFactory.makeHero("Tain",
                    "Fighter", "Human", 1);
//        } catch (LevelException e) {
//            throw new RuntimeException(); // TODO: catch input error
//        }
    }

}

