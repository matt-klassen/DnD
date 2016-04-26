package klassen.matt.dndproject.ui;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * The main UI window
 */
public class DnDCombat extends JFrame {

    public DnDCombat() {
        super("D&D Combat Sim v.0.1");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        pack();
        centerOnScreen();
        setVisible(true);
    }

    private void centerOnScreen() {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screen.width - getWidth()) / 2, (screen.height - getHeight()) / 2);
    }

}

