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

### Application

- Must run on Android Emulator or Physical Android Phone without crashing.
- Should display all elements without errors.

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

#### After clicking the "Show Tasks"

- Displays the number of tasks under the title of the application.

#### After clicking one of the tasks

- Shows details of the task, including title, details, category, due date, collaborator.

#### After clicking the "Add" button

- Shows details of the task, including title, details, category, due date, collaborator.
- Displays a checkbox indicating whether the task is completed or not.

  - User can key in the details of the task.
  - Users can choose the due date of the task by clicking the date below the category.
  - A Date dialog will appear prompting the user to choose a date.
  - The date will reflect back to the task detail page.
  - User can also choose a collaborator by pressing the "Choose Collaborator" Button.
  - User is able to choose a contact from their contact list.

## How to Run

1. Open the project in Android Studio.
2. Ensure you have Android Studio version 3.1.1 and Android API 28 installed.
3. Build and run the app on an Android emulator or physical device.

## License

This project is licensed under the [MIT License](LICENSE).


