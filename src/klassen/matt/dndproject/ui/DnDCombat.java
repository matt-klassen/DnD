package klassen.matt.dndproject.ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import klassen.matt.dndproject.model.creature.Hero;
import klassen.matt.dndproject.model.creature.HeroFactory;
import klassen.matt.dndproject.model.creature.exception.NoNameException;
import klassen.matt.dndproject.ui.exception.TooManyCreaturesException;

/**
 * @author Matt Klassen <matt.klassen@yahoo.ca>
 * @version 1.0
 * @since 2016-06-01
 *
 * The main UI window
 */
public class DnDCombat extends Application {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final double VERTICAL_GAP = 8;
    public static final double HORIZONTAL_GAP = 10;
    public static final Insets LARGE_INSET = new Insets(10, 10, 10, 10);
    public static final Insets SMALL_INSET = new Insets(4, 4, 4, 4);
    private static final BorderStroke[] BORDERS = { new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
            new CornerRadii(1), BorderStroke.THIN) };
    public static final Border BORDER = new Border(BORDERS);

    private static DnDCombat instance;
    private Stage window;
    private Scene scene;
    private GridPane grid;
    private BorderPane layout;
    private MenuBar menuBar;
    private GroupBox partyVBox;
    private GroupBox monsterVBox;
    private CombatLog combatLog;
    private TextArea infoField;
    private ActionBox heroActionsVBox;
    private ActionBox monsterActionsVBox;
    private Hero pregens[];

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        initPregenHeroes();
        initGrid();
        initMenu();
        initLayout();
        initWindow();
        this.instance = this;
    }

    public static DnDCombat getInstance() {
        return instance;
    }

    public void heroPopup() {
        NewHeroPopup hpopup = new NewHeroPopup(this);
        hpopup.display();
    }

    public void monsterPopup() {
        NewMonsterPopup mpopup = new NewMonsterPopup(this);
        mpopup.display();
    }

    public GroupBox getPartyBox() {
        return partyVBox;
    }

    public GroupBox getMonsterBox() { return monsterVBox; }

    private void initGrid() {
        grid = new GridPane();
        initGridDimensions();
        initGridItems();
        grid.getChildren().addAll(partyVBox, monsterVBox, combatLog,
                heroActionsVBox, monsterActionsVBox, infoField);
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
        combatLog = CombatLog.getInstance();
        infoField = HeroInfo.getInstance(partyVBox);
        initItemDimensions();
        try {
            partyVBox.addCreature(pregens[0]);
            partyVBox.addCreature(pregens[1]);
            partyVBox.addCreature(pregens[2]);
        } catch (TooManyCreaturesException e) {
            throw new RuntimeException();
        }
    }

    private void initItemDimensions() {
        GridPane.setConstraints(partyVBox, 0, 0);
        GridPane.setMargin(partyVBox, new Insets(0, 0, 0, 10));
        GridPane.setConstraints(monsterVBox, 1, 0);
        GridPane.setConstraints(infoField, 2, 0);
        GridPane.setConstraints(combatLog, 2, 1);
        GridPane.setConstraints(heroActionsVBox, 0, 1);
        GridPane.setMargin(heroActionsVBox, new Insets(0, 0, 0, 10));
        GridPane.setConstraints(monsterActionsVBox, 1, 1);
        combatLog.setMinHeight(ActionBox.HEIGHT);
        infoField.setMaxWidth(combatLog.WIDTH);
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
        newFile.setOnAction(e -> restart());
        fileMenu.getItems().addAll(newFile);
        return fileMenu;
    }

    private Menu createCustomizeMenu() {
        Menu customizeMenu = new Menu("_Customize");
        MenuItem newHero = new MenuItem("New _Hero...");
        MenuItem newMonster = new MenuItem("New _Monster...");
        newHero.setOnAction(e -> heroPopup());
        newMonster.setOnAction(e -> monsterPopup());
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
        window.setTitle("Tabletop RPG Combat Sim v0.1");
        window.show();
    }

    private void restart() {
        partyVBox.removeAllCreatures();
        monsterVBox.removeAllCreatures();
        combatLog.reset();
        HeroInfo.getInstance(partyVBox).clear();
    }

    public void initPregenHeroes() {
        pregens = new Hero[3];
       try {
           pregens[0] = HeroFactory.makeHero("Blorpo",
                   "Barbarian", "Human", 1);
           pregens[1] = HeroFactory.makeHero("Paula",
                   "Sorcerer", "Human", 1);
           pregens[2] = HeroFactory.makeHero("Iados",
                   "Rogue", "Tiefling", 1);
       } catch (NoNameException e) {
           return;
       }
    }

}

