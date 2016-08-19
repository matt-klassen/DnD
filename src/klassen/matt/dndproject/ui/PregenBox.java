package klassen.matt.dndproject.ui;

import javafx.scene.layout.VBox;

/**
 * Created by Matt on 4/30/2016.
 */
public class PregenBox extends VBox {

    public static final int WIDTH = 300;
    public static final int HEIGHT = 260;

    private DnDCombat parent;

    public PregenBox(DnDCombat parent) {
        super();
        this.parent = parent;
        this.setBorder(DnDCombat.BORDER);
        this.setMinHeight(HEIGHT);
        this.setMaxHeight(HEIGHT);
        this.setMinWidth(WIDTH);
        this.setMaxWidth(WIDTH);
    }

    // TODO delete and extend combat log

}
