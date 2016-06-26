package klassen.matt.dndproject.ui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import klassen.matt.dndproject.model.creature.Hero;
import klassen.matt.dndproject.model.creature.HeroFactory;
import klassen.matt.dndproject.ui.exception.TooManyCreaturesException;


/**
 * Created by Matt on 5/1/2016.
 */
public class NewHeroPopup {

    private DnDCombat parent;
    private Stage window;
    private GroupBox partyBox;
    private TextField nameField;
    private String name;
    private String creatureType;
    private String heroClass;
    private int level;

    public NewHeroPopup(DnDCombat parent) {
        this.parent = parent;
    }

    public void display() { // TODO: fix throwing exception with default choices
        window = initWindow();
        partyBox = parent.getPartyBox();
        Label label = new Label("Create a Hero");
        VBox inputList = initInputList();
        HBox buttonArea = initButtonArea();
        VBox layout = initLayout(label, inputList, buttonArea);
        Scene scene = new Scene(layout);

        window.setScene(scene);
        window.showAndWait();
    }

    private HBox initButtonArea() {
        Button submitButton = initSubmitButton();
        Button cancelButton = initCancelButton();
        HBox buttonArea = new HBox();
        buttonArea.getChildren().addAll(submitButton, cancelButton);
        initButtonAreaDimensions(buttonArea);
        return buttonArea;
    }

    private Button initSubmitButton() {
        Button submitButton = new Button("Create");
        submitButton.setOnAction(e -> submitAction());
        return submitButton;
    }

    private Button initCancelButton() {
        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e -> window.close());
        return cancelButton;
    }

    private void submitAction() { //TODO: Exception handling for no name given
        Hero newHero;
        name = nameField.getText();
        newHero = HeroFactory.makeHero(name, heroClass, creatureType, level);
        try {
            partyBox.addCreature(newHero);
        } catch (TooManyCreaturesException e) {
            showFullAlert();
        } finally {
            window.close(); // If hero was successfully added, otherwise give error dialogue
        }
    }

    private static void showFullAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Party full");
        alert.setHeaderText(null);
        alert.setGraphic(null);
        alert.setContentText("You cannot add any more Heroes to this party");
        alert.showAndWait();
    }

    private static void initButtonAreaDimensions(HBox buttonArea) {
        buttonArea.setSpacing(10);
        buttonArea.setPadding(DnDCombat.LARGE_INSET);
    }

    private Stage initWindow() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(270);
        window.setMinHeight(200);
        window.setTitle("Create a Hero");
        return window;
    }

    private VBox initLayout(Label label, VBox inputList, HBox buttonArea) {
        VBox layout = new VBox();
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(label, inputList, buttonArea);
        layout.setSpacing(15);
        layout.setPadding(DnDCombat.LARGE_INSET);
        return layout;
    }

    private VBox initInputList() {
        Label nameLabel = new Label("Name");
        nameField = new TextField();
        Label heroClassLabel = new Label("Class");
        ChoiceBox<String> classSelector = makeClassSelector();
        Label raceLabel = new Label("Hero Race");
        ChoiceBox<String> raceSelector = makeRaceSelector();
        Label startingLevelLabel = new Label("Starting Level");
        ChoiceBox<Integer> levelSelector = makeLevelSelector();

        VBox inputList = new VBox();
        inputList.getChildren().addAll(nameLabel, nameField, heroClassLabel, classSelector,
                raceLabel, raceSelector, startingLevelLabel, levelSelector);

        return inputList;
    }

    private ChoiceBox<String> makeClassSelector() {
        ChoiceBox<String> classSelector = new ChoiceBox<>();
        classSelector.getItems().addAll("Barbarian", "Bard", "Cleric", "Druid",
                "Fighter", "Monk", "Paladin", "Ranger", "Rogue", "Sorcerer",
                "Warlock", "Wizard");
        classSelector.setValue("Barbarian");
        heroClass = classSelector.getSelectionModel().getSelectedItem();
        classSelector.setOnAction(e ->
            selectClass(classSelector.getSelectionModel().getSelectedItem()));
        return classSelector;
    }

    private void selectClass(String heroClass) {
        this.heroClass = heroClass;
    }

    private ChoiceBox<String> makeRaceSelector() {
        ChoiceBox<String> raceSelector = new ChoiceBox<>();
        raceSelector.getItems().addAll("Human", "Dwarf", "Elf", "Half-Elf",
                "Dragonborn", "Tiefling", "Halfling", "Gnome", "Half-Orc");
        raceSelector.setValue("Human");
        creatureType = raceSelector.getSelectionModel().getSelectedItem();
        raceSelector.setOnAction((e ->
            selectRace(raceSelector.getSelectionModel().getSelectedItem())));
        return raceSelector;
    }

    private void selectRace(String heroRace) {
        this.creatureType = heroRace;
    }

    private ChoiceBox<Integer> makeLevelSelector() {
        ChoiceBox<Integer> levelSelector = new ChoiceBox<>();
        levelSelector.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9,
                10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);
        levelSelector.setValue(1);
        level = levelSelector.getSelectionModel().getSelectedItem();
        levelSelector.setOnAction(e ->
            selectLevel(levelSelector.getSelectionModel().getSelectedItem()));
        return levelSelector;
    }

    private void selectLevel(int level) {
        this.level = level;
    }

    // TODO implement listeners for user submission fields
    // TODO implement HBox for cancel/submit buttons
    // TODO implement Hero creation (HeroFactory class)

}
