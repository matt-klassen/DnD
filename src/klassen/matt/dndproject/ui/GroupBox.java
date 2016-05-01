package klassen.matt.dndproject.ui;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import klassen.matt.dndproject.model.actions.Action;
import klassen.matt.dndproject.model.creature.AbstractCreature;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Matt on 2016-04-29.
 */
public class GroupBox extends VBox {
    // TODO add Javadoc
    public static final int HEIGHT = 200;
    public static final int WIDTH = 220;

    private Map<String, AbstractCreature> creatures;
    private ListView<String> listView;
    private AbstractCreature selectedCreature;
    private ActionBox pairedBox;

    public GroupBox(String boxName) {
        super();
        creatures = new HashMap<>();
        this.setSpacing(10);
        this.setMaxHeight(HEIGHT);
        this.setMinHeight(HEIGHT);
        this.setMinWidth(WIDTH);
        this.setMaxWidth(WIDTH);

        VBox innerBox = new VBox();
        this.setPadding(DnDCombat.SMALL_INSET);
        this.setBorder(DnDCombat.BORDER);

        // ListView area
        listView = new ListView<>();
        listView.setMaxHeight(100);
        initListener();

        // Buttons
        Button removeButton = new Button("Delete");
        removeButton.setPadding(DnDCombat.SMALL_INSET);
        removeButton.setOnAction(e -> removeCreature(getSelectedCreature()));

        Button newButton = new Button("New...");
        newButton.setPadding(DnDCombat.SMALL_INSET);

        Label label = new Label(boxName);
        label.setPadding(DnDCombat.SMALL_INSET);
        HBox buttonArea = new HBox();
        buttonArea.setSpacing(10);
        buttonArea.setPadding(DnDCombat.LARGE_INSET);
        buttonArea.getChildren().addAll(removeButton, newButton);
        innerBox.getChildren().add(listView);
        this.getChildren().addAll(label, innerBox, buttonArea);
    }

    private void initListener() {
        listView.getSelectionModel().selectedItemProperty().addListener( (v, oldValue, newValue) -> {
            if (newValue != null) {
                setSelectedCreature(creatures.get(newValue));
                Set<Action> creatureActions = selectedCreature.getActions();
                pairedBox.clear();
                for (Action act : creatureActions) {
                    pairedBox.addAction(act);
                }
            }
        } );
    }

    public void addCreature(AbstractCreature creature) {
        if (!creatures.containsValue(creature)) {
            creatures.put(creature.getName(), creature);
            listView.getItems().add(creature.getName());
        }
    }

    public void removeCreature(AbstractCreature creature) {
        creatures.remove(creature.getName());
        listView.getItems().remove(creature.getName());
        listView.getSelectionModel().clearSelection();
        selectedCreature = null;
        pairedBox.clear();
    }

    public AbstractCreature getSelectedCreature() {
        return selectedCreature;
    }

    public void setSelectedCreature(AbstractCreature creature) {
        selectedCreature = creature;
    }

    public void pair(ActionBox box) {
        pairedBox = box;
        box.pair(this);
    }


}
