package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.LinkedList;

//Represents a diary with a title and an empty list of diary entries
public class Diary implements Writable {

    private LinkedList<DiaryEntry> entries;
    private LinkedList<String> entryDates;
    private String diaryTitle;

    public Diary(String diaryTitle) {
        this.diaryTitle = diaryTitle;
        entries = new LinkedList<DiaryEntry>();
    }

    public LinkedList<DiaryEntry> getEntries() {
        return entries;
    }

    //MODIFIES: this
    //EFFECTS: adds a diary entry to the beginning of the list and logs the event
    public void addEntry(DiaryEntry entry) {
        entries.addFirst(entry);
        EventLog.getInstance().logEvent(new Event("a new entry named "
                + entry.getEntryTitle() + " was added to your diary"));
    }

    //EFFECTS: searches diary for an entry based on its date
    //          if entry is found, return entry, otherwise return null
    public DiaryEntry searchEntry(String date) {
        for (int i = 0; i < entries.size(); i++) {
            if (date.equals(entries.get(i).getDate())) {
                return entries.get(i);
            }
        }
        return null;
    }

    //MODIFIES: this
    //EFFECTS: removes a diary entry through the date in MM/DD/YY format
    public void removeEntry(String date) {
        DiaryEntry entryToBeRemoved = searchEntry(date);
        entries.remove(entryToBeRemoved);
    }

    // EFFECTS: returns true if queue is empty, false otherwise
    public boolean isEmpty() {
        return entries.size() == 0;
    }

    //MODIFIES: this
    //EFFECTS: gets entry dates from all the entries and puts them into a list of strings
    public LinkedList<String> getEntryDates() {
        entryDates = new LinkedList<String>();
        for (DiaryEntry entry : entries) {
            entryDates.add(entry.getDate());
        }
        return entryDates;
    }

    //EFFECTS: returns the number of entries in the diary
    public int numEntries() {
        return entries.size();
    }

    public String getDiaryTitle() {
        return diaryTitle;
    }

    public void setDiaryTitle(String diaryTitle) {
        this.diaryTitle = diaryTitle;
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("diaryTitle", diaryTitle);
        json.put("entries", entriesToJson());
        return json;
    }

    // EFFECTS: returns entries in the diary as a JSON array
    private JSONArray entriesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (DiaryEntry e : entries) {
            jsonArray.put(e.toJson());
        }

        return jsonArray;
    }

}



