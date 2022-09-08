package model;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;

class DiaryTest {

    DiaryEntry e1;
    DiaryEntry e2;
    DiaryEntry e3;
    Diary diary;
    LinkedList<String> listOfEntryDates;
    LinkedList<DiaryEntry> listOfEntries;

    @BeforeEach
    void runBefore() {
        e1 = new DiaryEntry("the happiest day of my life", "12/21/21",
                "today i finished all my exams so i am happy!");
        e2 = new DiaryEntry("first day of school", "09/07/08", "it was not fun");
        e3 = new DiaryEntry("sick today", "09/21/17", "i got the flu...");
        LinkedList<DiaryEntry> entries = new LinkedList<DiaryEntry>();
        diary = new Diary("kelly's diary");
        listOfEntryDates = new LinkedList<String>(Arrays.asList("09/21/17", "12/21/21"));
        listOfEntries = new LinkedList<DiaryEntry>(Arrays.asList(e3, e1));

    }

    @Test
    void testDiary() {
        assertTrue(diary.isEmpty());
        assertEquals("kelly's diary", diary.getDiaryTitle());
        diary.setDiaryTitle("kel's diary");
        assertEquals("kel's diary", diary.getDiaryTitle());
    }

    @Test
    void testAddingAndRemovingAnEntry() {
        diary.addEntry(e1);
        assertFalse(diary.isEmpty());
        diary.addEntry(e2);
        diary.addEntry(e3);
        assertEquals(e2, diary.searchEntry("09/07/08"));
        diary.removeEntry("09/07/08");
        assertEquals(null, diary.searchEntry("09/07/08"));
    }

    @Test
    void testChangingInformation() {
        assertEquals("the happiest day of my life", e1.getEntryTitle());
        e1.setEntryTitle("a happy day");
        assertEquals("a happy day", e1.getEntryTitle());
        assertEquals("12/21/21", e1.getDate());
        e1.setDate("04/02/18");
        assertEquals("04/02/18", e1.getDate());
        assertEquals("today i finished all my exams so i am happy!", e1.getDescription());
        e1.setDescription("i am happy");
        assertEquals("i am happy", e1.getDescription());
    }

    @Test
    void testGetEntryDates() {
        diary.addEntry(e1);
        diary.addEntry(e3);
        assertEquals(listOfEntries, diary.getEntries());
        assertEquals(2, diary.numEntries());
        assertTrue(listOfEntryDates.equals(diary.getEntryDates()));
    }
}
