package klassen.matt.dndproject.ui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import klassen.matt.dndproject.model.creature.Hero;


/**
 * Created by Matt on 5/1/2016.
 */
public class NewHeroPopup {

    private static Stage window;
    private String name;
    private String creatureType;
    private String heroClass;
    private int level;

    public static Hero display() {
        window = initWindow();

        Label label = new Label("Create a Hero");
        VBox inputList = makeInputList();
        Button submitButton = new Button("Create"); // TODO: add button functionality
        VBox layout = initLayout(label, inputList, submitButton);
        Scene scene = new Scene(layout);

        window.setScene(scene);
        window.showAndWait();

        Hero demoHero = null; // TODO return new Hero or null (user error)
        return demoHero;
    }

    private static Stage initWindow() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(270);
        window.setMinHeight(200);
        window.setTitle("Create a Hero");
        return window;
    }

    private static VBox initLayout(Label label, VBox inputList, Button submitButton) {
        VBox layout = new VBox();
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(label, inputList, submitButton);
        return layout;
    }

    private static VBox makeInputList() {
        Label nameLabel = new Label("Name");
        TextField nameField = new TextField();
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

    private static ChoiceBox<String> makeClassSelector() {
        ChoiceBox<String> classSelector = new ChoiceBox<>();
        classSelector.getItems().addAll("Barbarian", "Bards", "Druid", "Monk",
                "Paladin", "Ranger", "Sorcerer", "Warlock", "Wizard");
        classSelector.setValue("Barbarian");
        return classSelector;
    }

    private static ChoiceBox<String> makeRaceSelector() {
        ChoiceBox<String> raceSelector = new ChoiceBox<>();
        raceSelector.getItems().addAll("Human", "Dwarf", "Elf", "Half-Elf", "Dragonborn",
                "Tiefling", "Halfling", "Gnome", "Half-Orc");
        raceSelector.setValue("Human");
        return raceSelector;
    }

    private static ChoiceBox<Integer> makeLevelSelector() {
        ChoiceBox<Integer> levelSelector = new ChoiceBox<>();
        levelSelector.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9,
                10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);
        levelSelector.setValue(1);
        return levelSelector;
    }

    // TODO implement listeners for user submission fields
    // TODO implement HBox for cancel/submit buttons
    // TODO implement Hero creation (HeroFactory class)

}
