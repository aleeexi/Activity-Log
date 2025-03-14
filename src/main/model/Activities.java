package model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

// Represents a list of activities and a separate list of completed activities
public class Activities implements Writable {
    private List<Activity> activities;
    private List<Activity> completedActivities;

    // EFFECTS: creates empty list of activities and empty list of completed
    // activities
    public Activities() {
        this.activities = new ArrayList<>();
        this.completedActivities = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds new activity to list of activities
    public void addNewActivity(String name, String location, int cost, boolean category, boolean inOrOut) {
        Activity activity = new Activity(name, location, cost, category, inOrOut);
        activities.add(activity);
        EventLog.getInstance().logEvent(new Event("New activity added to log."));
    }

    // MODIFIES: this
    // EFFECTS: adds given activity to list of completed activities
    public void addCompletedActivity(Activity activity) {
        completedActivities.add(activity);
    }

    // MODIFIES: this
    // EFFECTS: adds given activity to list of activities
    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public List<Activity> getCompletedActivities() {
        return completedActivities;
    }

    // implementation from JsonSerializationDemo
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("all", activitiesToJson());
        json.put("completed", completedToJson());
        return json;
    }

    // EFFECTS: returns list of activities in this activity log as a JSON array
    // implementation from JsonSerializationDemo
    private JSONArray activitiesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Activity next : activities) {
            jsonArray.put(next.toJson());
        }

        return jsonArray;
    }

    // EFFECTS: returns list of completed activities in this activity log as a JSON
    // array
    // implementation from JsonSerializationDemo
    private JSONArray completedToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Activity next : completedActivities) {
            jsonArray.put(next.toJson());
        }

        return jsonArray;
    }
}