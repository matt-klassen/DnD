package klassen.matt.dndproject.ui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import klassen.matt.dndproject.model.creature.Monster;
import klassen.matt.dndproject.ui.exception.TooManyCreaturesException;

/**
 * Prompt for new monster creation.
 */
public class NewMonsterPopup {

    private DnDCombat parent;
    private Stage window;
    private GroupBox monsterBox;
    private Monster monster;

    public NewMonsterPopup(DnDCombat parent) {
        this.parent = parent;
    }

    public void display() { // TODO: fix throwing exception with default choices
        window = initWindow();
        monsterBox = parent.getMonsterBox();
        VBox inputList = initInputArea();
        HBox buttonArea = initButtonArea();
        VBox layout = initLayout(inputList, buttonArea);
        Scene scene = new Scene(layout);

        window.setScene(scene);
        window.showAndWait();
    }

    private Stage initWindow() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(220);
        window.setMinHeight(150);
        window.setTitle("Add a monster");
        return window;
    }

    private VBox initLayout(VBox inputList, HBox buttonArea) {
        VBox layout = new VBox();
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(inputList, buttonArea);
        layout.setSpacing(15);
        layout.setPadding(DnDCombat.LARGE_INSET);
        return layout;
    }

    private VBox initInputArea() {
        ChoiceBox<String> monsterList = initMonsterList();
        VBox inputArea = new VBox();
        inputArea.getChildren().addAll(monsterList);
        inputArea.setAlignment(Pos.CENTER);
        return inputArea;
    }

    private ChoiceBox<String> initMonsterList() {
        ChoiceBox<String> monsterList = new ChoiceBox<>();
        monsterList.getItems().addAll("Kobold", "Goblin", "Orc", "Owlbear",
                "Troll", "Red Dragon", "Beholder", "Devil", "Lich");
        monsterList.setValue("Kobold");
        monster = stringToMonster(monsterList.getSelectionModel().getSelectedItem());
        monsterList.setOnAction(e ->
                selectMonster(monsterList.getSelectionModel().getSelectedItem()));
        return monsterList;
    }

    private HBox initButtonArea() {
        Button addButton = initAddButton();
        Button cancelButton = initCancelButton();
        HBox buttonArea = new HBox();
        buttonArea.getChildren().addAll(addButton, cancelButton);
        buttonArea.setAlignment(Pos.CENTER);
        buttonArea.setSpacing(10);
        return buttonArea;
    }

    private Button initAddButton() {
        Button addButton = new Button("Add Selected");
        addButton.setOnAction(e -> addAction());
        return addButton;
    }

    private void addAction() {
        try {
            monsterBox.addCreature(monster);
            window.close();
        } catch (TooManyCreaturesException e) {
            showFullAlert();
        } finally {
            window.close();
        }
    }

    private static void showFullAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Monsters full");
        alert.setHeaderText(null);
        alert.setGraphic(null);
        alert.setContentText("You cannot add any more Monsters to the group");
        alert.showAndWait();
    }

    private Button initCancelButton() {
        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e -> window.close());
        return cancelButton;
    }

    private void selectMonster(String monster) {
        this.monster = stringToMonster(monster);
    }

    private Monster stringToMonster(String monsterString) {
        switch (monsterString) {
            case "Kobold": return Monster.newKobold();
            case "Goblin": return Monster.newGoblin();
            case "Orc": return Monster.newOrc();
            case "Owlbear": return Monster.newOwlbear();
            case "Troll": return Monster.newTroll();
            case "Red Dragon": return Monster.newRedDragon();
            case "Beholder": return Monster.newBeholder();
            case "Devil": return Monster.newDevil();
            case "Lich": return Monster.newLich();
            default: return null;
        }
    }

}
