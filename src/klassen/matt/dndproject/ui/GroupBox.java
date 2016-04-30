package klassen.matt.dndproject.ui;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

/**
 * Created by Matt on 2016-04-29.
 */
public class GroupBox extends VBox {

    private BorderStroke[] borders = { new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
            new CornerRadii(1), BorderStroke.THIN) };
    private Border border = new Border(borders);

    public GroupBox(String boxName) {
        super();
        this.setMaxHeight(125);
        this.setSpacing(20);

        VBox innerBox = new VBox();
        innerBox.setMaxHeight(100);
        innerBox.setMaxWidth(175);
        this.setPadding(DnDCombat.LARGE_INSET);
        this.setBorder(border);

        CheckBox hero1 = new CheckBox("<name of creature>");
        hero1.setPadding(DnDCombat.SMALL_INSET);
        CheckBox hero2 = new CheckBox("<name of creature>");
        hero2.setPadding(DnDCombat.SMALL_INSET);
        CheckBox hero3 = new CheckBox("<name of creature>");
        hero3.setPadding(DnDCombat.SMALL_INSET);
        CheckBox hero4 = new CheckBox("<name of creature>");
        hero4.setPadding(DnDCombat.SMALL_INSET);

        Button removeButton = new Button("Delete");
        removeButton.setPadding(DnDCombat.SMALL_INSET);
        Button newButton = new Button("New...");
        newButton.setPadding(DnDCombat.SMALL_INSET);
        Label label = new Label(boxName);
        label.setPadding(DnDCombat.LARGE_INSET);
        HBox buttonArea = new HBox();
        buttonArea.setSpacing(10);
        buttonArea.setPadding(DnDCombat.LARGE_INSET);
        buttonArea.getChildren().addAll(removeButton, newButton, label);

        innerBox.getChildren().addAll(hero1, hero2, hero3, hero4);
        this.getChildren().addAll(innerBox, buttonArea);
    }

}
