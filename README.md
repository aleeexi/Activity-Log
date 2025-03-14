# Activity Planner

### What will the application do?
This application will log activities. Activities consist of a location, a cost, a category, and an indication of whether they are indoors or outdoors. Activites can also be marked as "done", which will add them to a list of "completed" activities that can be viewed. Additionally, it will be able to recommend an activity from the log based on inputted conditions (e.g. available money, weather, energy).

### Who will use it?
The everyday person can use this application to keep track of any activities that they want to do or to determine what activity to do that day.

### Why is this project of interest to you?
When I am with friends, we always find ourselves struggling to decide what activity to do. Having an application like this would allow me to keep track of possible activities and even pick an activity to do that day.

## User Stories
- As a user, I want to be able to add an activity to my activity log and specify the name, location, cost, category, and indoor/outdoor status.
- As a user, I want to be able to view the list of activities in my activity log.
- As a user, I want to be able to mark an activity as done in my activity log.
- As a user, I want to be able to see the completed activities in my activity log.
- As a user, I want to be able to input how much money I have, the current weather, and how much energy I have into the application and receive an activity recommendation from my activity log.
- As a user, I want to be able to choose to save my activity log to file.
- As a user, I want to be able to choose to load an activity log from file.


## Navigation Instructions
- You can generate the first required action related to the user story "adding multiple Xs to a Y" by clicking the "Add new activity" button.
- You can generate the second required action related to the user story "adding multiple Xs to a Y" by clicking the "Mark activity as completed" button.
- You can locate my visual component by clicking the "Save current activity log" button and seeing the image that appears below the confirmation message and "Back" button.
- You can save the state of my application by clicking the "Save current activity log" button.
- You can reload the state of my application by clicking the "Load previous activity log" button.


## Future Improvements
After looking at my UML Design Diagram, I realized that, if I had more time to work on this project, I would refactor all of my Listener classes and  ActivityLogGUI class to get rid of the numerous associations between them. This would allow me to make changes in the Listener classes and ActivityLogGUI class more efficiently without having to change a lot of things. Refactoring this would also reduce duplication in my code. I would probably find a way to make the ActivityLogGUI instance static using the Simpleton pattern by separating the functions of ActivityLogGUI into two different classes. One class would strictly be for drawing the GUI, while the other would perform the functions of the Activity Log that do not involve the GUI or the console. I could also create an abstract Listener type that each Listener class extends, so that the ActivityLogGUI class would only be associated with the abstract Listener class.