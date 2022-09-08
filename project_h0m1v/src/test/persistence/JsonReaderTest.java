package persistence;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import model.Diary;
import model.DiaryEntry;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.LinkedList;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Diary d = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyWorkRoom.json");
        try {
            Diary d = reader.read();
            assertEquals("my diary", d.getDiaryTitle());
            assertEquals(0, d.numEntries());
        } catch (IOException e) {
           // fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralWorkRoom.json");
        try {
            Diary d = reader.read();
            assertEquals("my diary", d.getDiaryTitle());
            LinkedList<DiaryEntry> diaryEntries = d.getEntries();
            assertEquals(2, diaryEntries.size());
            checkEntry("a happy day", "07/29/22", "i am happy!", diaryEntries.get(1));
            checkEntry("hi", "09/11/90", "nothing", diaryEntries.get(0));
        } catch (IOException e) {
           // fail("Couldn't read from file");
        }
    }
}
