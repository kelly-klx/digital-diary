package persistence;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.Diary;
import model.DiaryEntry;
import org.json.*;

// Represents a reader that reads a diary from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads diary from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Diary read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseDiary(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses diary from JSON object and returns it
    private Diary parseDiary(JSONObject jsonObject) {
        String diaryTitle = jsonObject.getString("diaryTitle");
        Diary d = new Diary(diaryTitle);
        addEntries(d, jsonObject);
        return d;
    }

    // MODIFIES: diary
    // EFFECTS: parses entries from JSON object and adds them to diary
    private void addEntries(Diary d, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("entries");
        for (Object json : jsonArray) {
            JSONObject nextEntry = (JSONObject) json;
            addEntry(d, nextEntry);
        }
    }

    // MODIFIES: diary
    // EFFECTS: parses entry from JSON object and adds it to the diary
    private void addEntry(Diary d, JSONObject jsonObject) {
        String entryTitle = jsonObject.getString("entryTitle");
        String date = jsonObject.getString("date");
        String description = jsonObject.getString("description");
        DiaryEntry entry = new DiaryEntry(entryTitle, date, description);
        d.addEntry(entry);
    }
}
