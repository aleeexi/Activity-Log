package ui;

import java.awt.event.*;

// Represents listener for "Back" button with gui
public class BackListener implements ActionListener {
    private ActivityLogGUI gui;

    // EFFECTS: creates new instance of BackListener with given gui
    public BackListener(ActivityLogGUI gui) {
        this.gui = gui;
    }

    public void actionPerformed(ActionEvent e) {
        gui.getContentPane().removeAll();
        gui.displayMenu();
        gui.setVisible(true);
    }

}
