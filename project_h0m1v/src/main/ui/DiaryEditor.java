package ui;

import model.Diary;
import model.DiaryEntry;
import model.Event;
import model.EventLog;
import persistence.JsonReader;
import persistence.JsonWriter;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

//Diary application, with a GUI
//Based on (some of which were given by https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html):
// - AlarmSystem (https://github.students.cs.ubc.ca/CPSC210/AlarmSystem.git)
// - DrawingEditor (https://github.students.cs.ubc.ca/CPSC210/SimpleDrawingPlayer-Starter.git)
// - ButtonDemo (https://docs.oracle.com/javase/tutorial/displayCode.html?code=
// https://docs.oracle.com/javase/tutorial/uiswing/examples/components
// /ButtonDemoProject/src/components/ButtonDemo.java
// - PopupMenuDemo (https://docs.oracle.com/javase/tutorial/displayCode.html?code=
// https://docs.oracle.com/javase/tutorial/uiswing/examples/components
// /PopupMenuDemoProject/src/components/PopupMenuDemo.java)
// - ListDemo (https://docs.oracle.com/javase/tutorial/displayCode.html?code=
// https://docs.oracle.com/javase/tutorial/uiswing/examples/components
// /ListDemoProject/src/components/ListDemo.java)
public class DiaryEditor extends JFrame {
    private Diary diary;
    private String entryTitle;
    private String entryDate;
    private String entryDescription;
    private LinkedList<DiaryEntry> entries;
    public static final int WIDTH = 500;
    public static final int HEIGHT = 700;
    private JLabel titleLabel;
    private JLabel dateLabel;
    private JLabel descriptionLabel;
    private JTextField titleText;
    private JTextField dateText;
    private JTextField descriptionText;
    private JPanel diaryPanel;
    private JPopupMenu popupMenu;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/diary.json";

    //EFFECTS: runs the diary application
    public DiaryEditor() {
        super("My Diary");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        init();
        addButtons();
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS:  sets the basic layout for the diary with text fields to make an entry
    private void init() {
        titleLabel = new JLabel("entry title \n");
        dateLabel = new JLabel("date \n");
        descriptionLabel = new JLabel("description \n");
        titleText = new JTextField(40);
        dateText = new JTextField(40);
        descriptionText = new JTextField(40);
        diaryPanel = new JPanel();
        diary = new Diary("My Diary");
        diary.setDiaryTitle("My Diary");
        entries = new LinkedList<DiaryEntry>();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        diaryPanel.add(titleLabel);
        diaryPanel.add(titleText);
        diaryPanel.add(dateLabel);
        diaryPanel.add(dateText);
        diaryPanel.add(descriptionLabel);
        diaryPanel.add(descriptionText);
        add(diaryPanel);

    }

    //MODIFIES: this
    //EFFECTS: helper to add buttons to perform user actions
    private void addButtons() {
        ImageIcon img = createImageIcon("images/middle.gif");
        JButton b1 = new JButton(img);
        diaryPanel.add(new JButton(new AddEntryAction()));
        diaryPanel.add(new JButton(new DisplayEntryAction()));
        diaryPanel.add(new JButton(new SaveEntryAction()));
        diaryPanel.add(new JButton(new LoadEntryAction()));
        diaryPanel.add(b1);

    }

    // Represents action taken when user adds an entry to the diary
    private class AddEntryAction extends AbstractAction {
        AddEntryAction() {
            super("add entry");
        }

        //MODIFIES: this
        //EFFECTS: adds an entry to the diary on the GUI
        @Override
        public void actionPerformed(ActionEvent event) {
            entryTitle = titleText.getText();
            entryDate = dateText.getText();
            entryDescription = descriptionText.getText();
            DiaryEntry entry = new DiaryEntry(entryTitle, entryDate, entryDescription);
            entry.setEntryTitle(entryTitle);
            entry.setDate(entryDate);
            entry.setDescription(entryDescription);
            diary.addEntry(entry);
            //entries.add(entry);
            clearTextBar();
        }
    }

    //MODIFIES: this
    //EFFECTS: clears the text bar after an entry is added
    private void clearTextBar() {
        titleText.setText("");
        dateText.setText("");
        descriptionText.setText("");
    }

    // Represents action taken when user wants to view the added entry dates of the diary
    private class DisplayEntryAction extends AbstractAction {
        DisplayEntryAction() {
            super("display entries");
        }

        //MODIFIES: this
        //EFFECTS: displays added entry dates in a popup menu
        @Override
        public void actionPerformed(ActionEvent event) {
            entries = diary.getEntries();
            popupMenu = new JPopupMenu("Message");
            for (DiaryEntry entry : entries) {
                popupMenu.add(new JMenuItem(entry.getDate()));
            }
            popupMenu.show(diaryPanel, 200, 200);

        }
    }

    // Represents action taken when user wants to save their diary
    private class SaveEntryAction extends AbstractAction {
        SaveEntryAction() {
            super("save");
        }

        //EFFECTS; saves the user's entries to the diary through the 'save' button
        @Override
        public void actionPerformed(ActionEvent event) {
            saveDiary();
        }
    }

    // Represents action taken when user wants to load their diary
    private class LoadEntryAction extends AbstractAction {
        LoadEntryAction() {
            super("load");
        }

        //EFFECTS: loads the user's entries through the 'load' button
        @Override
        public void actionPerformed(ActionEvent event) {
            loadDiary();
        }
    }

    //MODIFIES: this
    // EFFECTS: saves the diary to file
    public void saveDiary() {
        try {
            jsonWriter.open();
            jsonWriter.write(diary);
            jsonWriter.close();

            System.out.println("Saved " + diary.getDiaryTitle() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads diary from file
    public void loadDiary() {
        try {
            diary = jsonReader.read();
            System.out.println("Loaded " + diary.getDiaryTitle() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    //MODIFIES: this
    //EFFECTS: turns an image from its file string into an icon
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = DiaryEditor.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    public void printLog(EventLog el) {
        for (Event next : EventLog.getInstance()) {
            System.out.println(next);
        }
    }

}
