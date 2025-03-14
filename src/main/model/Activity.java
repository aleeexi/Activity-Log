package model;

import org.json.JSONObject;

import persistence.Writable;

// Represents activity with a name, location, cost, category,
// an indication whether it is inside or outside, and
// a completion status.
public class Activity implements Writable {
    String name;
    String location;
    int cost;
    boolean category;
    boolean inOrOut;
    boolean completion;

    // REQUIRES: cost >= 0
    // EFFECTS: constructs an activity with given name, location, cost (in $),
    // category, inside/outside category, and with an incomplete status.
    public Activity(String name, String location, int cost, boolean category, boolean inOrOut) {
        this.name = name;
        this.location = location;
        this.cost = cost;
        this.category = category;
        this.inOrOut = inOrOut;
        this.completion = false;
    }

    // MODIFIES: this
    // EFFECTS: sets completion status of activity to true
    public void markAsDone() {
        this.completion = true;
        EventLog.getInstance().logEvent(new Event("Activity marked as completed."));
    }

    // EFFECTS: returns name of category as a string from its associated character
    public String getFullCategory() {
        if (this.getCategory()) {
            return "Active";
        } else {
            return "Relaxing";
        }
    }

    // EFFECTS: returns whether activity is indoors or outdoors as a string
    public String getFullInOrOut() {
        if (this.inOrOut) {
            return "indoors";
        } else {
            return "outdoors";
        }
    }

    public String getName() {
        return this.name;
    }

    public String getLocation() {
        return this.location;
    }

    public int getCost() {
        return this.cost;
    }

    public boolean getCategory() {
        return this.category;
    }

    public boolean getInOrOut() {
        return this.inOrOut;
    }

    public boolean getCompletion() {
        return this.completion;
    }

    // implementation from JsonSerializationDemo
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("location", location);
        json.put("cost", cost);
        json.put("category", category);
        json.put("inOrOut", inOrOut);
        json.put("completion", completion);
        return json;
    }
}
