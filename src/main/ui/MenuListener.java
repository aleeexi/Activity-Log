package ui;

import java.util.List;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;

import model.Activity;

// Represents a listener for all buttons in menu with gui and panel
public class MenuListener implements ActionListener {
    private ActivityLogGUI gui;
    private JPanel panel;

    // EFFECTS: creates new instance of MenuListener with given gui and current
    // panel
    public MenuListener(ActivityLogGUI gui) {
        this.gui = gui;
        this.panel = gui.getPanel();
    }

    // MODIFIES: this
    // EFFECTS: displays prompts depending on button pressed
    public void actionPerformed(ActionEvent e) {
        panel.removeAll();
        String command = e.getActionCommand();

        if (command.equals("Add new activity")) {
            displayAddPrompts();
        } else if (command.equals("View all activities")) {
            viewActivities();
        } else if (command.equals("Mark activity as completed")) {
            markCompleted();
        } else if (command.equals("Save current activity log")) {
            save();
        } else if (command.equals("Load previous activity log")) {
            load();
        }
    }

    // MODIFIES: this
    // EFFECTS: displays prompts to add new activity
    public void displayAddPrompts() {
        JButton add = createAdd();
        AddListener listener = new AddListener(gui);
        add.addActionListener(listener);
        createNamePrompt();
        createNameField(listener);
        createLocationPrompt();
        createLocationField(listener);
        createCostPrompt();
        createCostField(listener);
        createInOrOutPrompt();
        createInOrOutField(listener);
        createCategoryPrompt();
        createCategoryField(listener);
        panel.add(add);
        gui.add(panel, BorderLayout.CENTER);
        gui.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: displays all activities and their fields all at once in order added
    // if activities is empty, displays message
    public void viewActivities() {
        this.panel = gui.createNewPane();
        JButton back = createBackButton();

        List<Activity> activities = gui.getActivities().getActivities();
        if (activities.isEmpty()) {
            JLabel empty = new JLabel("You haven't added any activities!");
            panel.add(empty);
        } else {
            displayActivities(activities);
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

    // MODIFIES: this
    // EFFECTS: displays all activities and their fields all at once in order added
    public void displayActivities(List<Activity> activities) {
        int count = 0;
        for (Activity next : activities) {
            count++;
            String name = next.getName();
            String location = next.getLocation();
            int cost = next.getCost();
            String category = next.getFullCategory();
            String inOrOut = next.getFullInOrOut();
            String completion = getFullCompletion(next.getCompletion());
            JLabel title = new JLabel("Activity #" + count);
            JLabel info = new JLabel(
                    name + " (" + category + ") " + inOrOut + " at " + location + " for $" + cost + " (" + completion
                            + ").");
            JLabel lineBreak = new JLabel(" ");
            panel.add(title);
            panel.add(info);
            panel.add(lineBreak);
        }
    }

    // MODIFIES: this
    // EFFECTS: marks the first activity with a name matching text field content as
    // done
    public void markCompleted() {
        this.panel = gui.createNewPane();
        List<Activity> activities = gui.getActivities().getActivities();
        if (activities.isEmpty()) {
            JLabel empty = new JLabel("You haven't added any activities!");
            panel.add(empty);
            JButton back = createBackButton();
            panel.add(back);
        } else {
            JLabel namePrompt = new JLabel("What is the name of the activity that you want to mark as done?");
            panel.add(namePrompt);
            JTextField name = new JTextField(20);
            SearchListener listener = new SearchListener(gui, name);
            name.addActionListener(listener);
            name.getDocument().addDocumentListener(listener);
            panel.add(name);
            JButton mark = new JButton("Mark");
            mark.setActionCommand("Mark");
            mark.addActionListener(listener);
            panel.add(mark);
        }
        gui.add(panel, BorderLayout.CENTER);
        gui.setVisible(true);
    }

    // EFFECTS: saves current activity log to file and displays message
    // if FileNotFoundException thrown, displays message
    public void save() {
        this.panel = gui.createNewPane();
        try {
            gui.saveActivityLog();
            JLabel confirmation = new JLabel("Current activity log saved!");
            panel.add(confirmation);
            JButton back = createBackButton();
            panel.add(back);
            JLabel image = loadImage();
            panel.add(image);
        } catch (FileNotFoundException e) {
            JLabel exception = new JLabel("Unable to write to file");
            panel.add(exception);
        } finally {
            gui.add(panel, BorderLayout.CENTER);
            gui.setVisible(true);
        }
    }

    // EFFECTS: loads previous activity log from file and displays message
    // if IOException thrown, displays message
    public void load() {
        this.panel = gui.createNewPane();
        try {
            gui.loadActivityLog();
            JLabel confirmation = new JLabel("Previous activity log loaded!");
            panel.add(confirmation);
            JButton back = createBackButton();
            panel.add(back);
        } catch (IOException e) {
            JLabel exception = new JLabel("Unable to read from file");
            panel.add(exception);
        } finally {
            gui.add(panel, BorderLayout.CENTER);
            gui.setVisible(true);
        }
    }

    // EFFECTS: sets up save confirmation image
    // implementation to import image from C3LectureLab
    public JLabel loadImage() {
        String sep = System.getProperty("file.separator");
        ImageIcon saved = new ImageIcon(System.getProperty("user.dir") + sep
                + "images" + sep + "saved.jpg");
        JLabel image = new JLabel(saved);
        return image;
    }

    // MODIFIES: this
    // EFFECTS: creates text field for category
    public void createCategoryField(AddListener listener) {
        JTextField category = new JTextField(20);
        listener.setCategoryField(category);
        category.addActionListener(listener);
        category.getDocument().addDocumentListener(listener);
        panel.add(category);
    }

    // MODIFIES: this
    // EFFECTS: creates text field for inOrOut
    public void createInOrOutField(AddListener listener) {
        JTextField inOrOut = new JTextField(20);
        listener.setInOrOutField(inOrOut);
        inOrOut.addActionListener(listener);
        inOrOut.getDocument().addDocumentListener(listener);
        panel.add(inOrOut);
    }

    // MODIFIES: this
    // EFFECTS: creates text field for cost
    public void createCostField(AddListener listener) {
        JTextField cost = new JTextField(20);
        listener.setCostField(cost);
        cost.addActionListener(listener);
        cost.getDocument().addDocumentListener(listener);
        panel.add(cost);
    }

    // MODIFIES: this
    // EFFECTS: creates text field for location
    public void createLocationField(AddListener listener) {
        JTextField location = new JTextField(20);
        listener.setLocationField(location);
        location.addActionListener(listener);
        location.getDocument().addDocumentListener(listener);
        panel.add(location);
    }

    // MODIFIES: this
    // EFFECTS: creates text field for name
    public void createNameField(AddListener listener) {
        JTextField name = new JTextField(20);
        listener.setNameField(name);
        name.addActionListener(listener);
        name.getDocument().addDocumentListener(listener);
        panel.add(name);
    }

    // MODIFIES: this
    // EFFECTS: creates Add button
    public JButton createAdd() {
        JButton add = new JButton("Add");
        add.setActionCommand("Add");
        return add;
    }

    // MODIFIES: this
    // EFFECTS: creates prompt for category
    public void createCategoryPrompt() {
        JLabel categoryPrompt = new JLabel(
                "Is this activity active or relaxing? Type 'a' for active or 'r' for relaxing.");
        panel.add(categoryPrompt);
    }

    // MODIFIES: this
    // EFFECTS: creates prompt for inOrOut
    public void createInOrOutPrompt() {
        JLabel inOrOutPrompt = new JLabel(
                "Is this activity indoors or outdoors? Type 'i' for indoors or 'o' for outdoors.");
        panel.add(inOrOutPrompt);
    }

    // MODIFIES: this
    // EFFECTS: creates prompt for cost
    public void createCostPrompt() {
        JLabel costPrompt = new JLabel(
                "How much does this activity cost to carry out? Type a numeric value >= 0.");
        panel.add(costPrompt);
    }

    // MODIFIES: this
    // EFFECTS: creates prompt for location
    public void createLocationPrompt() {
        JLabel locationPrompt = new JLabel("Where is this activity located?");
        panel.add(locationPrompt);
    }

    // MODIFIES: this
    // EFFECTS: creates prompt for name
    public void createNamePrompt() {
        JLabel namePrompt = new JLabel("What do you want to call this activity?");
        panel.add(namePrompt);
    }

    // EFFECTS: converts given completion status to a string
    public String getFullCompletion(boolean completion) {
        if (completion) {
            return "Completed";
        } else {
            return "Not completed";
        }
    }
}
