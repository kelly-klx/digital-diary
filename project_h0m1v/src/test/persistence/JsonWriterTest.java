package persistence;

import model.Diary;
import static org.junit.jupiter.api.Assertions.assertEquals;

import model.DiaryEntry;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.util.LinkedList;

public class JsonWriterTest extends JsonTest {


    @Test
    void testWriterInvalidFile() {
        try {
            Diary entries = new Diary("my diary");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            Diary d = new Diary("my diary");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyDiary.json");
            writer.open();
            writer.write(d);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyDiary.json");
            d = reader.read();
            assertEquals("my diary", d.getDiaryTitle());
            assertEquals(0, d.numEntries());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            Diary d = new Diary("my diary");
            d.addEntry(new DiaryEntry("a happy day", "07/29/22", "i am happy!"));
            d.addEntry(new DiaryEntry("hi", "09/11/90", "nothing"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralDiary.json");
            writer.open();
            writer.write(d);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralDiary.json");
            d = reader.read();
            assertEquals("my diary", d.getDiaryTitle());
            LinkedList<DiaryEntry> diaryEntries = d.getEntries();
            assertEquals(2, diaryEntries.size());
            checkEntry("a happy day", "07/29/22", "i am happy!", diaryEntries.get(0));
            checkEntry("hi", "09/11/90", "nothing", diaryEntries.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}
