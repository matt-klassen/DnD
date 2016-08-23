package klassen.matt.dndproject.ui;

import com.sun.glass.events.DndEvent;
import javafx.scene.control.TextArea;

/**
 * Real-time Combat Log that displays results of actions between creatures
 */
public class CombatLog extends TextArea {

    public static final int WIDTH = 300;
    public static final int HEIGHT = 200;

    private static CombatLog instance;

    private CombatLog() {
        super();
        this.setBorder(DnDCombat.BORDER);
        this.setEditable(false);
        this.setText("Combat Log\n\n");
        initDimensions();
    }

    public static CombatLog getInstance() {
        if (instance == null) {
            instance = new CombatLog();
        }
        return instance;
    }

    public void message(String msg) {
        this.appendText(msg + "\n");
    }

    public void reset() {
        this.clear();
        this.setText("Combat Log\n\n");
    }

    private void initDimensions() {
        this.setMinWidth(WIDTH);
        this.setMaxWidth(WIDTH);
        this.setMinHeight(HEIGHT);
        this.setMaxHeight(HEIGHT);
    }
}
