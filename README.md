# ToDo List Android App

## Overview

This ToDo List app is designed for working adults and students to manage their tasks effectively. It allows users to create a list of tasks, break down projects into smaller tasks, and monitor progress with ease.

## Problem Statement

With the increasing reliance on digital products for work and education, managing tasks efficiently becomes crucial. This app aims to streamline task management, reduce stress, and enhance productivity for users.

## Project Introduction

The ToDo List app helps users:
- Create new tasks
- Add collaborators from their contacts
- Share tasks with others
- Mark tasks as solved when completed
- View the number of tasks

## Project Requirements

### Add Task

- Allow users to create a new task by pressing the "add" button.
- Show the correct page for users to fill in task details, including title and due date.
- Allow users to add collaborators from their contacts.
- Allow users to share tasks.
- Allow users to check the checkbox as solved if the task is finished.
- Show the tick icon after the user has checked the checkbox.

### Show Tasks Amount

- Show the correct amount of tasks if the user presses the "Show Task" button.
- Hide the amount of tasks if the user presses the "Hide Task" button.

### Database

- Create a database and a table for the application.
- Record all tasks added by the user and update the database.
- Retrieve tasks when the user reopens the application.


## Implementation

### Tools, Techniques, Software & Hardware

#### Software

- IDE for Android Development: Android Studio Version 3.1.1
- API: 28
- Emulator: Android Studio Emulator (Nexus 5X API 28 / Android 9, API 28)


### Application Flow

#### HomePage

- This is the homepage of the application.
- The first page when the application is opened.
- Displays tasks, title, add button, and show tasks button.

  <img width="257" alt="Todolist_Homepage" src="https://github.com/karen-nke/android-studio-todolist/assets/103889430/b164705e-2632-4118-9d40-73e7b9dfa755">

#### After clicking the "Show Tasks"

- Displays the number of tasks under the title of the application.
  
  <img width="256" alt="Todolist_Showtask" src="https://github.com/karen-nke/android-studio-todolist/assets/103889430/188f6492-cccb-4dfa-89db-384eacca17cb">


#### After clicking one of the tasks

- Shows details of the task, including title, details, category, due date, collaborator.
  
  <img width="256" alt="Todolist_TaskDetail" src="https://github.com/karen-nke/android-studio-todolist/assets/103889430/997ce627-63f3-48da-b7b4-90bf9c87db9e">

#### After clicking the "Add" button

- Shows details of the task, including title, details, category, due date, collaborator.
- Displays a checkbox indicating whether the task is completed or not.
  
  <img width="255" alt="Todolist_AddTask" src="https://github.com/karen-nke/android-studio-todolist/assets/103889430/efc6fd4d-f495-4b98-ab5b-c578fb38a084">

  - User can key in the details of the task.
    
    <img width="257" alt="Todolist_KeyIn" src="https://github.com/karen-nke/android-studio-todolist/assets/103889430/2758ae94-ac47-4a29-994c-4ce471c887c9">
    
  - Users can choose the due date of the task by clicking the date below the category.
  - A Date dialog will appear prompting the user to choose a date.
    
    <img width="256" alt="Todolist_AddDate" src="https://github.com/karen-nke/android-studio-todolist/assets/103889430/ccbc7183-eb53-4229-bf2f-398d876fcdc7">

  - The date will reflect back to the task detail page.
  - User can also choose a collaborator by pressing the "Choose Collaborator" Button.
  - User is able to choose a contact from their contact list.
    
    <img width="257" alt="Todolist_AddCollaboratorContact" src="https://github.com/karen-nke/android-studio-todolist/assets/103889430/fa934279-c79d-474a-811b-6c9f462b6287">


## How to Run

1. Open the project in Android Studio.
2. Ensure you have Android Studio version 3.1.1 and Android API 28 installed.
3. Build and run the app on an Android emulator or physical device.

## License

This project is licensed under the [MIT License](LICENSE).


