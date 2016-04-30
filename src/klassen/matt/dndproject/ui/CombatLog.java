package klassen.matt.dndproject.ui;

import javafx.scene.control.TextArea;

/**
 * Created by Matt on 2016-04-29.
 */
public class CombatLog extends TextArea {

    public static final int WIDTH = 300;
    public static final int HEIGHT = 200;

    public CombatLog() {
        super();

        this.setEditable(false);
        this.setText("Combat Log");
        this.setMinWidth(WIDTH);
        this.setMaxWidth(WIDTH);
        this.setMinHeight(HEIGHT);
        this.setMaxHeight(HEIGHT);
    }

}
