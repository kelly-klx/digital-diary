package persistence;

import model.DiaryEntry;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkEntry(String entryTitle, String date, String description, DiaryEntry entry) {
        assertEquals(entryTitle, entry.getEntryTitle());
        assertEquals(date, entry.getDate());
        assertEquals(description, entry.getDescription());
    }
}
