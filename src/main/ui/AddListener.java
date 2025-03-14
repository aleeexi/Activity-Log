package ui;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;

// Represents listener for "Add" button with gui, panel, name field, location field,
// cost field, inOrOut field, and category field
public class AddListener implements ActionListener, DocumentListener {
    private ActivityLogGUI gui;
    private JPanel panel;
    private JTextField nameField;
    private JTextField locationField;
    private JTextField costField;
    private JTextField inOrOutField;
    private JTextField categoryField;

    // EFFECTS: creates new instance of AddListener with given gui, current panel,
    // and null text fields
    public AddListener(ActivityLogGUI gui) {
        this.gui = gui;
        this.panel = gui.getPanel();
    }

    // REQUIRES: all text fields are not empty
    // costField.getText() is an integer as a string,
    // inOrOutField.getText() is one of 'i' or 'o',
    // categoryField.getText() is one of 'a' or 'r'
    // MODIFIES: this
    // EFFECTS: creates new activity from content in text fields and adds it to list
    // of activities
    public void actionPerformed(ActionEvent e) {
        String name = nameField.getText();
        String location = locationField.getText();
        String costString = costField.getText();
        int cost = Integer.parseInt(costString);
        String inOrOutString = inOrOutField.getText();
        boolean inOrOut = getInOrOut(inOrOutString);
        String categoryString = categoryField.getText();
        boolean category = getCategory(categoryString);
        gui.getActivities().addNewActivity(name, location, cost, category, inOrOut);
        panel.removeAll();
        gui.displayMenu();
        gui.setVisible(true);
    }

    // REQUIRES: text is one of "i" or "o"
    // EFFECTS: converts String text field input to boolean to obtain inOrOut of
    // activity
    public boolean getInOrOut(String text) {
        if (text.equals("i")) {
            return true;
        } else {
            return false;
        }
    }

    // REQUIRES: text is one of "a" or "r"
    // EFFECTS: onverts String text field input to boolean to obtain category of
    // activity
    public boolean getCategory(String text) {
        if (text.equals("a")) {
            return true;
        } else {
            return false;
        }
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

    public void setNameField(JTextField name) {
        this.nameField = name;
    }

    public void setLocationField(JTextField location) {
        this.locationField = location;
    }

    public void setCostField(JTextField cost) {
        this.costField = cost;
    }

    public void setInOrOutField(JTextField inOrOut) {
        this.inOrOutField = inOrOut;
    }

    public void setCategoryField(JTextField category) {
        this.categoryField = category;
    }
}
