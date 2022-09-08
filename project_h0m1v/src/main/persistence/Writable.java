package persistence;

import org.json.JSONObject;

//This interface, JsonReader and JsonWrite are based on the JsonSerializationDemo given from class
//The link for that repository is here: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}

