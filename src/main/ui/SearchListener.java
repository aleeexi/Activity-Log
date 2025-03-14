package ui;

import java.util.List;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

import model.Activity;

// Represents listener for "Mark" button with gui, panel, and name field
public class SearchListener implements ActionListener, DocumentListener {
    private ActivityLogGUI gui;
    private JPanel panel;
    private JTextField nameField;

    // EFFECTS: creates new instance of SearchListener with given gui, current
    // panel, and given nameField
    public SearchListener(ActivityLogGUI gui, JTextField nameField) {
        this.gui = gui;
        this.panel = gui.getPanel();
        this.nameField = nameField;
    }

    // MODIFIES: this
    // EFFECTS: marks activity with a name matching text in nameField as completed
    // amd displays message
    // if list of activities is empty, displays message
    // if no activities in log match text in nameField, displays message
    public void actionPerformed(ActionEvent e) {
        this.panel = gui.createNewPane();
        gui.getContentPane().removeAll();
        panel.removeAll();
        JButton back = createBackButton();
        String name = nameField.getText();
        List<Activity> activities = gui.getActivities().getActivities();
        boolean found = false;
        for (Activity next : activities) {
            if (name.equals(next.getName())) {
                found = true;
                next.markAsDone();
                JLabel wasFound = new JLabel("Activity '" + name + "' has been marked as completed");
                panel.add(wasFound);
                break;
            }
        }
        if (found == false) {
            JLabel notFound = new JLabel("No activities in the log have that name.");
            panel.add(notFound);
        }
        panel.add(back);
        gui.add(panel, BorderLayout.CENTER);
        gui.setVisible(true);
    }

    // EFFECTS: creates "Back" button with a listener
    public JButton createBackButton() {
        JButton back = new JButton("Back");
        back.setActionCommand("Back");
        BackListener listener = new BackListener(gui);
        back.addActionListener(listener);
        return back;
    }

    // EFFECTS: required by DocumentListener, does nothing when any text field is
    // typed in
    public void insertUpdate(DocumentEvent e) {
    }

    // EFFECTS: required by DocumentListener, does nothing when text from any text
    // field is removed
    public void removeUpdate(DocumentEvent e) {
    }

    // EFFECTS: required by DocumentListener, does nothing when any text field is
    // updated
    public void changedUpdate(DocumentEvent e) {
    }

}
