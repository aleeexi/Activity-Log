package ui;

import model.Activities;
import model.Activity;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// An activity log where users can add activities, mark them as done,
// view all activities, and view completed activities.
public class ActivityLog {
    private static final String JSON_STORE = "./data/activityLog.json";

    private Activities activities;
    private Scanner scanner;
    private boolean isProgramRunning;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: creates instance of ActivityLog console ui application
    public ActivityLog() {
        this.activities = new Activities();
        this.scanner = new Scanner(System.in);
        this.isProgramRunning = true;
        this.jsonWriter = new JsonWriter(JSON_STORE);
        this.jsonReader = new JsonReader(JSON_STORE);
        while (this.isProgramRunning) {
            mainMenu();
        }
        System.out.println("Thank you for using the Activity Log!");
    }

    // EFFECTS: displays list of menu navigation commands and processes keyboard
    // inputs
    public void mainMenu() {
        displayMenu();
        String input = this.scanner.nextLine();
        menuDirect(input);
    }

    // EFFECTS: displays list of menu navigation commands
    public void displayMenu() {
        System.out.println("\nACTIVITY LOG MAIN MENU");
        System.out.println("Type:");
        System.out.println("'a' to add a new activity");
        System.out.println("'v' to view all activities");
        System.out.println("'r' to get an activity recommendation");
        System.out.println("'c' to mark an existing activity as completed");
        System.out.println("'d' to view all completed activities");
        System.out.println("'s' to save current activity log");
        System.out.println("'l' to load a previous activity log");
    }

    // EFFECTS: runs commands based on input
    // keyboard input processing code from Lab 3.2 Flashcard Reviewer
    @SuppressWarnings("methodlength")
    public void menuDirect(String input) {
        switch (input) {
            case "a":
                addNewActivity();
                mainMenu();
            case "v":
                viewActivities();
                mainMenu();
            case "r":
                inputPreferences();
                mainMenu();
            case "c":
                markActivityAsDone();
                mainMenu();
            case "d":
                viewCompletedActivities();
                mainMenu();
            case "s":
                saveActivityLog();
                mainMenu();
            case "l":
                loadActivityLog();
                mainMenu();
            default:
                System.out.println("Invalid input.");
                mainMenu();
        }
    }

    // MODIFIES: this, Activities
    // EFFECTS: adds new activity to list of activities
    // keyboard input code from Lab 3.2 Flashcard Reviewer
    public void addNewActivity() {
        System.out.println("What do you want to call this activity?");
        String name = this.scanner.nextLine();

        System.out.println("Where is this activity located?");
        String location = this.scanner.nextLine();

        System.out.println("How much does this activity cost to carry out? Type a numeric value >= 0.");
        int cost = this.scanner.nextInt();
        this.scanner.nextLine();

        boolean category = getCategory();

        boolean inOrOut = getInOrOut();

        activities.addNewActivity(name, location, cost, category, inOrOut);
        System.out.println("Activity added to log!");
    }

    // EFFECTS: converts String keyboard input to boolean to obtain inOrOut of
    // activity
    public boolean getInOrOut() {
        boolean inOrOut = false;
        boolean shouldContinueInOrOut = true;
        while (shouldContinueInOrOut) {
            System.out.println("Is this activity indoors or outdoors?");
            System.out.println("Type 'i' for indoors or 'o' for outdoors.");
            String inOrOutInput = this.scanner.nextLine();
            switch (inOrOutInput) {
                case "i":
                    inOrOut = true;
                    shouldContinueInOrOut = false;
                    break;
                case "o":
                    inOrOut = false;
                    shouldContinueInOrOut = false;
                    break;
                default:
                    System.out.println("Invalid input.");
            }
        }
        return inOrOut;
    }

    // EFFECTS: converts String keyboard input to boolean to obtain category of
    // activity
    public boolean getCategory() {
        boolean category = false;
        boolean shouldContinueCategory = true;
        while (shouldContinueCategory) {
            System.out.println("Is this activity active or relaxing?");
            System.out.println("Type 'a' for active or 'r' for relaxing.");
            String categoryInput = this.scanner.nextLine();
            switch (categoryInput) {
                case "a":
                    category = true;
                    shouldContinueCategory = false;
                    break;
                case "r":
                    category = false;
                    shouldContinueCategory = false;
                    break;
                default:
                    System.out.println("Invalid input.");
            }
        }
        return category;
    }

    // EFFECTS: displays all activities and their fields all at once in order added
    public void viewActivities() {
        List<Activity> list = activities.getActivities();
        if (list.isEmpty()) {
            System.out.println("You haven't added any activities!");
            return;
        }
        viewList(list);
        System.out.println("No more activities in log.");
    }

    // REQUIRES: list.size() > 0
    // EFFECTS: prints all activities in list in order added and their fields all at
    // once
    public void viewList(List<Activity> list) {
        int count = 0;
        for (Activity next : list) {
            count++;
            String name = next.getName();
            String location = next.getLocation();
            int cost = next.getCost();
            String category = next.getFullCategory();
            String inOrOut = next.getFullInOrOut();
            System.out.println("Activity #" + count);
            System.out.println(name + " (" + category + ") " + inOrOut + " at " + location + " for $" + cost + ".\n");
        }
    }

    // EFFECTS: processes keyboard input to find matching activity
    public void inputPreferences() {
        System.out.println("How much spending money do you have?");
        int money = this.scanner.nextInt();
        this.scanner.nextLine();

        boolean weather = getWeather();

        boolean energy = getEnergy();

        findActivity(money, weather, energy);
    }

    // EFFECTS: converts String keyboard input to boolean to obtain energy
    private boolean getEnergy() {
        boolean energy = false;
        boolean shouldContinueEnergy = true;
        while (shouldContinueEnergy) {
            System.out.println("How much energy do you have today?");
            System.out.println("Type 'h' for high energy or 'l' for low energy.");
            String energyInput = this.scanner.nextLine();
            switch (energyInput) {
                case "h":
                    energy = true;
                    shouldContinueEnergy = false;
                    break;
                case "l":
                    energy = false;
                    shouldContinueEnergy = false;
                    break;
                default:
                    System.out.println("Invalid input.");
            }
        }
        return energy;
    }

    // EFFECTS: converts String keyboard input to boolean to obtain weather
    private boolean getWeather() {
        boolean weather = false;
        boolean shouldContinueWeather = true;
        while (shouldContinueWeather) {
            System.out.println("What's the weather like today?");
            System.out.println("Type 'o' for stormy or 'u' for sunny.");
            String weatherInput = this.scanner.nextLine();
            switch (weatherInput) {
                case "o":
                    weather = true;
                    shouldContinueWeather = false;
                    break;
                case "u":
                    weather = false;
                    shouldContinueWeather = false;
                    break;
                default:
                    System.out.println("Invalid input.");
            }
        }
        return weather;
    }

    // REQUIRES: money >= 0
    // EFFECTS: searches list of activities for activities that meet the following
    // criteria:
    // energy.equals(this.category)
    // weather.equals(this.inOrOut)
    // money >= this.cost
    // then if any activities matched the criteria, print the list of matching
    // activities
    // otherwise, print an error message
    public void findActivity(int money, boolean weather, boolean energy) {
        boolean found = false;
        List<Activity> foundActivities = new ArrayList<>();
        for (Activity next : activities.getActivities()) {
            if (weather == next.getInOrOut() && energy == next.getCategory() && money >= next.getCost()) {
                foundActivities.add(next);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No activities from log to recommend. Go take a day-long nap!");
        } else {
            displayFound(foundActivities);
        }
    }

    // REQUIRES: !foundActivities.isEmpty()
    // EFFECTS: lists all activities in foundActivities at once in order found
    public void displayFound(List<Activity> foundActivities) {
        viewList(foundActivities);
        System.out.println("No more activities to recommend.");
    }

    // MODIFIES: Activities, Activity
    // EFFECTS: marks the first activity with a name that matches the user input as
    // done
    public void markActivityAsDone() {
        List<Activity> list = activities.getActivities();
        if (list.isEmpty()) {
            System.out.println("You haven't added any activities!");
            return;
        }

        System.out.println("What is the name of the activity that you want to mark as done?");
        String name = this.scanner.nextLine();
        boolean found = false;

        for (Activity next : list) {
            if (name.equals(next.getName())) {
                found = true;
                next.markAsDone();
                activities.addCompletedActivity(next);
                System.out.println("Activity '" + name + "' has been marked as completed");
                break;
            }
        }

        if (!found) {
            System.out.println("No activities in the log have that name.");

        }
    }

    // EFFECTS: prints list of all activities that have been marked as completed
    public void viewCompletedActivities() {
        List<Activity> completed = activities.getCompletedActivities();
        if (completed.isEmpty()) {
            System.out.println("You haven't marked any activities as done!");
            return;
        }

        viewList(completed);
        System.out.println("No more completed activities in log.");
    }

    // EFFECTS: saves the activity log to file
    // implementation from JsonSerializationDemo
    public void saveActivityLog() {
        try {
            jsonWriter.open();
            jsonWriter.write(activities);
            jsonWriter.close();
            System.out.println("Activity Log saved to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads activity log from file
    // implementation from JsonSerializationDemo
    public void loadActivityLog() {
        try {
            this.activities = jsonReader.read();
            System.out.println("Activity log loaded from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}