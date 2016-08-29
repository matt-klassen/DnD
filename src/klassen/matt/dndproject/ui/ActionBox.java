package klassen.matt.dndproject.ui;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import klassen.matt.dndproject.model.actions.Action;

import java.util.HashMap;
import java.util.Map;

/**
 * A JavaFX VBox that contains action data parsed from selected member
 * of an associated GroupBox
 */
public class ActionBox extends VBox {

    public static final int HEIGHT = 260;
    public static final int WIDTH = 220;

    private DnDCombat parent;
    private Map<String, Action> actions;
    private ListView<String> actionListView;
    private Action selectedAction;
    private GroupBox pairedBox;
    private VBox innerBox;
    private HBox buttonArea;
    private Label label;

    public ActionBox(DnDCombat parent) {
        super();
        this.parent = parent;
        actions = new HashMap<>();
        initDimensions();
        initChildren();
    }

    public void addAction(Action action) {
        if (!actions.containsValue(action)) {
            actions.put(action.getName(), action);
            actionListView.getItems().add(action.getName());
        }
    }

    public void removeAction(Action action) {
        actions.remove(action.getName());
        actionListView.getItems().remove(action.getName());
    }

    public Action getSelectedAction() {
        return selectedAction;
    }

    public void setSelectedAction(Action action) {
        selectedAction = action;
    }

    public void pair(GroupBox box) {
        pairedBox = box;
    }

    public  void clear() {
        actions = new HashMap<>();
        actionListView.getItems().clear();
    }

    private void initChildren() {
        initLabel();
        initInnerBox();
        initButtons();
        this.getChildren().addAll(label, innerBox, buttonArea);
        initListener();
    }

    private void initLabel() {
        label = new Label("Actions");
        label.setPadding(DnDCombat.SMALL_INSET);
    }

    private void initInnerBox() {
        initActionListView();
        innerBox = new VBox();
        innerBox.getChildren().add(actionListView);
    }

    private void useAction() {
        if (getSelectedAction() != null) {
            pairedBox.useAction(getSelectedAction());
        }
    }

    private void initButtons() {
        Button useActionButton = new Button("Use Action");
        useActionButton.setPadding(DnDCombat.SMALL_INSET);
        buttonArea = new HBox();
        buttonArea.getChildren().add(useActionButton);
        buttonArea.setPadding(DnDCombat.LARGE_INSET);
        useActionButton.setOnAction(e -> useAction());
    }

    private void initActionListView() {
        actionListView = new ListView<>();
        actionListView.setMaxHeight(200);
    }

    private void initDimensions() {
        this.setSpacing(10);
        this.setPadding(DnDCombat.SMALL_INSET);
        this.setMaxHeight(HEIGHT);
        this.setMinHeight(HEIGHT);
        this.setMinWidth(WIDTH);
        this.setMaxWidth(WIDTH);
        this.setBorder(DnDCombat.BORDER);
    }

    private void initListener() {
        // Selection listening
        actionListView.getSelectionModel().selectedItemProperty().addListener( (v, oldValue, newValue) -> {
            setSelectedAction(actions.get(newValue));
        } );
    }
}
