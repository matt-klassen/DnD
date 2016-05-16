package klassen.matt.dndproject.ui;

import com.sun.glass.events.DndEvent;
import javafx.scene.control.TextArea;

/**
 * Real-time Combat Log that displays results of actions between creatures
 */
public class CombatLog extends TextArea {
    // TODO implement CombatLog behaviours

    public static final int WIDTH = 300;
    public static final int HEIGHT = 200;

    private DnDCombat parent;

    public CombatLog(DnDCombat parent) {
        super();
        this.parent = parent;
        this.setBorder(DnDCombat.BORDER);
        this.setEditable(false);
        this.setText("Combat Log");
        initDimensions();
    }

    private void initDimensions() {
        this.setMinWidth(WIDTH);
        this.setMaxWidth(WIDTH);
        this.setMinHeight(HEIGHT);
        this.setMaxHeight(HEIGHT);
    }
}
