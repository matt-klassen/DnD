package klassen.matt.dndproject.ui;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import klassen.matt.dndproject.model.actions.AbstractAction;
import klassen.matt.dndproject.model.actions.Action;
import klassen.matt.dndproject.model.actions.Item;
import klassen.matt.dndproject.model.creature.AbstractCreature;
import klassen.matt.dndproject.ui.exception.TooManyCreaturesException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * A JavaFX VBox that contains a group of creatures
 */
public class GroupBox extends VBox {

    public static final int HEIGHT = 210;
    public static final int WIDTH = 220;
    public static final int C_SIZE_LIMIT = 4;

    private DnDCombat parent;
    private String boxName;
    private Map<String, AbstractCreature> creatures;
    private ListView<String> listView;
    private AbstractCreature selectedCreature;
    private ActionBox pairedBox;
    private VBox innerBox;
    private HBox buttonArea;
    private Label label;

    public GroupBox(String boxName, ActionBox pairBox, DnDCombat parent) {
        super();
        this.parent = parent;
        this.boxName = boxName;
        this.creatures = new HashMap<>();
        pair(pairBox);
        initDimensions();
        initChildren();
    }

    public void addCreature(AbstractCreature creature) throws TooManyCreaturesException {
        if (creatures.size() < 4) {
            if (!creatures.containsValue(creature)) {
                creatures.put(creature.getName(), creature);
                listView.getItems().add(creature.getName());
            }
        } else {
            throw new TooManyCreaturesException();
        }
    }

    public void removeCreature(AbstractCreature creature) {
        if (creature != null) { // otherwise simply do nothing
            creatures.remove(creature.getName());
            listView.getItems().remove(creature.getName());
            clearSelection();
        }
    }

    public AbstractCreature getSelectedCreature() {
        return selectedCreature;
    }

    public int getCreaturesSize() {
        return creatures.size();
    }

    public void setSelectedCreature(AbstractCreature creature) {
        selectedCreature = creature;
    }

    private void initChildren() {
        initInnerBox();
        initButtonArea();
        initLabel();
        this.getChildren().addAll(label, innerBox, buttonArea);
    }

    private void initInnerBox() {
        innerBox = new VBox();
        listView = new ListView<>();
        innerBox.getChildren().add(listView);
        initListener();
    }

    private void initButtonArea() {
        buttonArea = new HBox();
        initButtonAreaDimensions();
        buttonArea.getChildren().addAll(
                makeRemoveButton(),
                makeNewButton());
    }

    private Button makeRemoveButton() {
        Button removeButton = new Button("Delete");
        removeButton.setPadding(DnDCombat.SMALL_INSET);
        removeButton.setOnAction(e -> removeCreature(getSelectedCreature()));
        return removeButton;
    }

    private Button makeNewButton() {
        Button newButton = new Button("New...");
        newButton.setPadding(DnDCombat.SMALL_INSET);
        newButton.setOnAction( e -> newButtonClick());
        return newButton;
    }

    private void newButtonClick() {
        if (isHeroGroupBox()) {
            parent.heroPopup();
        } else {
            parent.monsterPopup();
        }
    }

    private boolean isHeroGroupBox() {
        return boxName.equals("Heroes");
    }

    private void initButtonAreaDimensions() {
        buttonArea.setSpacing(10);
        buttonArea.setPadding(DnDCombat.LARGE_INSET);
    }

    private void initLabel() {
        label = new Label(boxName);
        label.setPadding(DnDCombat.SMALL_INSET);
    }

    private void initDimensions() {
        this.setSpacing(10);
        this.setMaxHeight(HEIGHT);
        this.setMinHeight(HEIGHT);
        this.setMinWidth(WIDTH);
        this.setMaxWidth(WIDTH);
        this.setPadding(DnDCombat.SMALL_INSET);
        this.setBorder(DnDCombat.BORDER);
    }

    private void initListener() {
        listView.getSelectionModel().selectedItemProperty().addListener( (v, oldValue, newValue) ->
                listenerResponse(newValue));
    }

    private void listenerResponse(String newValue) {
        if (newValue != null) {
            AbstractCreature creatureToSelect = creatures.get(newValue);
            setSelectedCreature(creatureToSelect);
            generateSelectedCreatureActions();
        }
    }

    private void generateSelectedCreatureActions() {
        Set<AbstractAction> creatureActions = new HashSet<>();
        creatureActions.addAll(selectedCreature.getActions());
        creatureActions.addAll(selectedCreature.getItems());
        
        pairedBox.clear();
        for (AbstractAction action : creatureActions) {
            pairedBox.addAction(action);
        }
    }

    private void clearSelection() {
        listView.getSelectionModel().clearSelection();
        selectedCreature = null;
        pairedBox.clear();
    }

    private void pair(ActionBox box) {
        pairedBox = box;
        box.pair(this);
    }
}
