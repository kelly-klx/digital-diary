//package ui;
//
//import model.Diary;
//import model.DiaryEntry;
//import persistence.JsonReader;
//import persistence.JsonWriter;
//
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.LinkedList;
//import java.util.Scanner;
//
//// Diary application, based on Teller app; link below, TEXT BASED INTERFACE
//// https://github.students.cs.ubc.ca/CPSC210/TellerApp
//public class DiaryApp {
//    private static final String JSON_STORE = "./dateModel/diary.json";
//    private Scanner input;
//    private DiaryEntry entry;
//    private Diary diary;
//    private JsonWriter jsonWriter;
//    private JsonReader jsonReader;
//
//
//    // EFFECTS: runs the diary application
//    public DiaryApp() throws FileNotFoundException {
//        input = new Scanner(System.in);
//        diary = new Diary("kelly's diary");
//        jsonWriter = new JsonWriter(JSON_STORE);
//        jsonReader = new JsonReader(JSON_STORE);
//        runDiary();
//    }
//
//    // MODIFIES: this
//    // EFFECTS: processes user input
//    private void runDiary() {
//        boolean keepGoing = true;
//        String command = null;
//        input = new Scanner(System.in);
//
//        init();
//        while (keepGoing) {
//            diaryDisplay();
//            command = input.next();
//            command = command.toLowerCase();
//
//            if (command.equals("q")) {
//                keepGoing = false;
//            } else {
//                processCommand(command);
//            }
//        }
//        System.out.println("\nsee you next time!");
//    }
//
//    //MODIFIES: this
//    //EFFECTS: initializes a diary, a list of diary, an entry
//    private void init() {
//        LinkedList<DiaryEntry> entries = new LinkedList<DiaryEntry>();
//        diary = new Diary("kelly's diary");
//        entry = new DiaryEntry("kelly's day", "12/06/22", "hi");
//        input = new Scanner(System.in);
//        input.useDelimiter("\n");
//    }
//
//    //MODIFIES: this
//    //EFFECTS: processes user command
//    private void processCommand(String command) {
//        if (command.equals("a")) {
//            displayAddedEntry();
//        } else if (command.equals("v")) {
//            previewEntries();
//        } else if (command.equals("d")) {
//            displayDeletion();
//        } else if (command.equals("s")) {
//            displaySearchedEntry();
//        } else if (command.equals("save")) {
//            saveDiary();
//        } else if (command.equals("l")) {
//            loadDiary();
//        } else {
//            System.out.println("selection is not valid...");
//        }
//    }
//
//    //EFFECTS: displays menu of options to user
//    public void diaryDisplay() {
//        String diaryName = diary.getDiaryTitle();
//        System.out.println("\nwelcome to " + diaryName + " !");
//        System.out.println("\nselect from:");
//        System.out.println("\t'v' to view your diary");
//        System.out.println("\t'a' to add an entry");
//        System.out.println("\t'd' to delete an entry");
//        System.out.println("\t's' to search for an entry");
//        System.out.println("\t'save' to save diary to file");
//        System.out.println("\t'l' to load diary from file");
//        System.out.println("\t'q' to quit");
//    }
//
//    //MODIFIES: this
//    //EFFECTS: displays adding an entry to a list of diary
//    public void displayAddedEntry() {
//        entry = new DiaryEntry("kelly's day", "12/06/22", "hi");
//        System.out.println("please enter your entry title, date, and description");
//        input.nextLine();
//        String title = input.nextLine();
//        String date = input.nextLine();
//        String description = input.nextLine();
//        entry.setEntryTitle(title);
//        entry.setDate(date);
//        entry.setDescription(description);
//        diary.addEntry(entry);
//        System.out.println(title + " \n" + date + " \n" + description);
//    }
//
//    //EFFECTS: prints list of entry dates of all the diary
//    public void previewEntries() {
//        System.out.println(diary.getEntryDates());
//    }
//
//    //EFFECTS: searches diary for an entry based on its date and prints out the entry
//    public void displaySearchedEntry() {
//        System.out.println("please input the date of your desired entry...");
//        input.nextLine();
//        String entryDate = input.nextLine();
//        if (diary.searchEntry(entryDate) == entry) {
//            System.out.println(diary.searchEntry(entryDate).getEntryTitle() + " \n"
//                    + diary.searchEntry(entryDate).getDate() + " \n"
//                    + diary.searchEntry(entryDate).getDescription());
//        } else {
//            System.out.println("this entry does not exist...");
//        }
//    }
//
//    //MODIFIES: this
//    //EFFECTS: deletes an entry from list of diary in printed form
//    public void displayDeletion() {
//        System.out.println("please input the date of the entry you would like to delete");
//        input.nextLine();
//        String deleteThisEntry = input.nextLine();
//        diary.removeEntry(deleteThisEntry);
//        System.out.println("deleted!");
//    }
//
//    //MODIFIES: this
//    // EFFECTS: saves the diary to file
//    public void saveDiary() {
//        try {
//            jsonWriter.open();
//            jsonWriter.write(diary);
//            jsonWriter.close();
//            System.out.println("Saved " + diary.getDiaryTitle() + " to " + JSON_STORE);
//        } catch (FileNotFoundException e) {
//            System.out.println("Unable to write to file: " + JSON_STORE);
//        }
//    }
//
//    // MODIFIES: this
//    // EFFECTS: loads diary from file
//    public void loadDiary() {
//        try {
//            diary = jsonReader.read();
//            System.out.println("Loaded " + diary.getDiaryTitle() + " from " + JSON_STORE);
//        } catch (IOException e) {
//            System.out.println("Unable to read from file: " + JSON_STORE);
//        }
//    }
//}
