# Diary Project

The *Diary Project* will allow users to write entries in a virtual diary and be able to write them with their own flair. 
Each entry will include a title, a date, and a description. Its targeted audience is for people who enjoy reflecting on 
their day and for those who like to store memories in an online written format. This project is of interest to me 
because sometimes I have thoughts and emotions and memories that I want to jot down in a private space, 
but I have no motivation to write it on paper. 

I was also inspired by the creative look of the *Unsent Project*, which is a website where users 
can send a coloured "unsent" message to someone. My *Diary Project* would be designed so that users can input a date 
and make longer entries in a diary format instead, where they can type their private thoughts and feelings.



### User Stories

**PHASE 1**

- As a **user**, I want to be able to add an entry to my diary.
- As a **user**, I want to be able to view the entry dates of my diary.
- As a **user**, I want to delete entries in my diary.
- As a **user**, I want to search for an entry in my diary.

**PHASE 2** 

- As a **user**, I want to be able to save all my diary entries.
- As a **user**, I want to be able to load my diary entries and be able to make changes to them.

**PHASE 3**

- As a **user**, I want to be able to add an entry with text fields and a button.
- As a **user**, I want to be able to view my entry dates in a popup menu through a button.
- As a **user**, I want to be able to save my diary by clicking on a button.
- As a **user**, I want to be able to load my diary by clicking on a button.

**PHASE 4: TASK 2**

After adding two entries, one named 'a happy day' and one named 'a sad day', the console prints out:

Wed Aug 10 23:03:35 PDT 2022
a new entry named a happy day was added to your diary
Wed Aug 10 23:03:45 PDT 2022
a new entry named a sad day  was added to your diary

Process finished with exit code 0


**PHASE 4: TASK 3**

If I had more time I would do refactoring between the actionPerformed methods for each button event, as
there is overlapping code between them. 
- I would make an abstract class called ButtonPerformed
- I would make an abstract method called actionPerformed 
- I would override actionPerformed for each button event in DiaryEditor

Refactoring aside, I would also add more features in my GUI that I previously added for the console 
(ex: searching for an entry, removing an entry). If I were more experienced in Java, I would also add features 
like choosing a certain colour to decorate each entry in the diary,
as this project was initially inspired by the *Unsent Project*.


# Instructions for Grader

- You can generate the first required event by clicking on the 'add entry' button.
- You can generate the second required event by clicking on the 'display entries' button, only the added entry dates 
should pop up.
- You can locate my visual component by looking at the button with the yellow blob image on it.
- You can save the state of my application by clicking the save button.
- You can reload the state of my application by clicking the load button and checking the loaded entries by clicking 
on the 'display entries button'