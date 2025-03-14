package persistence;

import model.Activities;
import model.Activity;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads activities from JSON data stored in file
// implementation of Json from JsonSerializationDemo
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads activities from file and returns it
    // throws IOException if an error occurs reading data from file
    public Activities read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseActivities(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses activities from JSON object and returns it
    private Activities parseActivities(JSONObject jsonObject) {
        Activities activities = new Activities();
        addActivities(activities, jsonObject);
        addCompleted(activities, jsonObject);
        return activities;
    }

    // MODIFIES: activities
    // EFFECTS: parses list of all activities from JSON object and adds them to
    // activities
    private void addActivities(Activities activities, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("all");
        for (Object json : jsonArray) {
            JSONObject nextActivity = (JSONObject) json;
            addActivity(activities, nextActivity);
        }
    }

    // MODIFIES: activities
    // EFFECTS: parses list of completed activities from JSON object and adds them
    // to activities
    private void addCompleted(Activities activities, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("completed");
        for (Object json : jsonArray) {
            JSONObject nextActivity = (JSONObject) json;
            addCompletedActivity(activities, nextActivity);
        }
    }

    // MODIFIES: activities
    // EFFECTS: parses activity from JSON object and adds it to activities
    private void addActivity(Activities activities, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String location = jsonObject.getString("location");
        int cost = jsonObject.getInt("cost");
        boolean category = jsonObject.getBoolean("category");
        boolean inOrOut = jsonObject.getBoolean("inOrOut");
        boolean completion = jsonObject.getBoolean("completion");
        Activity activity = new Activity(name, location, cost, category, inOrOut);
        if (completion) {
            activity.markAsDone();
        }
        activities.addActivity(activity);
    }

    // MODIFIES: activities
    // EFFECTS: parses activity from JSON object and adds it to activities
    private void addCompletedActivity(Activities completed, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String location = jsonObject.getString("location");
        int cost = jsonObject.getInt("cost");
        boolean category = jsonObject.getBoolean("category");
        boolean inOrOut = jsonObject.getBoolean("inOrOut");
        Activity activity = new Activity(name, location, cost, category, inOrOut);
        activity.markAsDone();
        completed.addCompletedActivity(activity);
    }
}