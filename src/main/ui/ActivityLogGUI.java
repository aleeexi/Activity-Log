package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

import model.Activities;
import model.Event;
import model.EventLog;
import persistence.JsonReader;
import persistence.JsonWriter;

// An activity log where users can add activities, mark activities as completed, and view all activities.
public class ActivityLogGUI extends JFrame {
    private static final String JSON_STORE = "./data/activityLog.json";

    private Activities activities;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private JPanel panel;

    // EFFECTS: creates instance of ActivityLog gui application
    public ActivityLogGUI() {
        super("Activity Log");
        initializeGUI();
        this.activities = new Activities();
        this.jsonWriter = new JsonWriter(JSON_STORE);
        this.jsonReader = new JsonReader(JSON_STORE);
        displayMenu();
    }

    // MODIFIES: this
    // EFFECTS: sets up gui window
    // addWindowListener and windowClosing implementation adapted from:
    // https://stackoverflow.com/questions/60516720/java-how-to-print-message-when-a-jframe-is-closed
    public void initializeGUI() {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                printLog(EventLog.getInstance());
                System.exit(0);
            }
        });
        setSize(500, 500);
        setVisible(true);
    }

    // EFFECTS: prints event log to console
    public void printLog(EventLog el) {
        for (Event next : el) {
            System.out.println(next.toString());
        }
    }

    // MODIFIES: this
    // EFFECTS: displays menu buttons
    public void displayMenu() {
        this.panel = createNewPane();
        panel.add(createAddActivity(), BorderLayout.CENTER);
        panel.add(createViewActivities(), BorderLayout.CENTER);
        panel.add(createMarkCompleted(), BorderLayout.CENTER);
        panel.add(createSave(), BorderLayout.CENTER);
        panel.add(createLoad(), BorderLayout.CENTER);
        add(panel, BorderLayout.CENTER);
    }

    // EFFECTS: creates new panel
    public JPanel createNewPane() {
        JPanel pane = new JPanel();
        pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));
        return pane;
    }

    // EFFECTS: sets up the "Add new activity" button with a listener
    public JButton createAddActivity() {
        JButton addActivity = new JButton("Add new activity");
        MenuListener listener = new MenuListener(this);
        addActivity.setActionCommand("Add new activity");
        addActivity.addActionListener(listener);
        return addActivity;
    }

    // EFFECTS: sets up the "View all activities" button with a listener
    public JButton createViewActivities() {
        JButton viewActivities = new JButton("View all activities");
        MenuListener listener = new MenuListener(this);
        viewActivities.setActionCommand("View all activities");
        viewActivities.addActionListener(listener);
        return viewActivities;
    }

    // EFFECTS: sets up the "Mark activity as completed" button with a listener
    public JButton createMarkCompleted() {
        JButton markCompleted = new JButton("Mark activity as completed");
        MenuListener listener = new MenuListener(this);
        markCompleted.setActionCommand("Mark activity as completed");
        markCompleted.addActionListener(listener);
        return markCompleted;
    }

    // EFFECTS: sets up the "Save current activity log" button with a listener
    public JButton createSave() {
        JButton save = new JButton("Save current activity log");
        MenuListener listener = new MenuListener(this);
        save.setActionCommand("Save current activity log");
        save.addActionListener(listener);
        return save;
    }

    // EFFECTS: sets up the "Load previous activity log" button with a listener
    public JButton createLoad() {
        JButton load = new JButton("Load previous activity log");
        MenuListener listener = new MenuListener(this);
        load.setActionCommand("Load previous activity log");
        load.addActionListener(listener);
        return load;
    }

    // EFFECTS: saves the activity log to file
    // throws FileNotFoundException if file doesn't exist
    // implementation from JsonSerializationDemo
    public void saveActivityLog() throws FileNotFoundException {
        jsonWriter.open();
        jsonWriter.write(activities);
        jsonWriter.close();
    }

    // MODIFIES: this
    // EFFECTS: loads activity log from file
    // throws IOException if previous activity log cannot be loaded
    // implementation from JsonSerializationDemo
    public void loadActivityLog() throws IOException {
        this.activities = jsonReader.read();
    }

    public JPanel getPanel() {
        return this.panel;
    }

    public Activities getActivities() {
        return this.activities;
    }
}
