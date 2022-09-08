package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

// Represents a diary entry with a title, date and whatever the user wants to write.
public class DiaryEntry implements Writable {

    private String entryTitle;
    private String date;
    private String description;

    //EFFECTS: creates a diary entry with a title, date, and a description
    public DiaryEntry(String entryTitle, String date, String description) {
        this.entryTitle = entryTitle;
        this.date = date;
        this.description = description;
    }

    //GETTERS AND SETTERS
    public String getEntryTitle() {
        return entryTitle;
    }

    public void setEntryTitle(String entryTitle) {
        this.entryTitle = entryTitle;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("entryTitle", entryTitle);
        json.put("date", date);
        json.put("description", description);
        return json;
    }
}

