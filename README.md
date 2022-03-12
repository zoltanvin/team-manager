<h1 align="center">
  <br>
  <a href="https://github.com/zolvin/team-manager"><img src="https://github.com/zolvin/team-manager/blob/main/Misc/logo.png" alt="Image rotator" width="200"></a>
  <br>
  Team Manager
  <br>
</h1>

<h3 align="center">Manage football players, teams and matches easily</a></h3>

<p align="center">
  <a href="#key-features-">Key Features</a> •
  <a href="#clone-and-use-">Clone and Use</a> •
  <a href="#sqlite-%EF%B8%8F">SQLite</a> •
  <a href="#c-program-">C# Program</a> •
  <a href="#javafx-program-">JavaFX Program</a>
</p>

# Key Features 🔑
 
* C#
  * Create teams and players
  * List teams, players
  * Change teams, players, player's details
* JavaFX
  * Listing team players
  * Send a text message to team players
  * View a given player's messages
  * Creating matches
  * Recording results
  * View the results of the match
  * Match Properties (Time, Judge, Place, Home or Away, Opponent, Result: Players, Scorer(s), Travel Information)
  * Travel Properties (Departure, Arrival, Travel Type)
  * Support for queries (Team statistics, Scorer(s), Results of Home or Away Games)

# Clone And Use 📋

- C#
	- Start in Visual Studio, Open a project or solution
	- Select `TeamManagerCSharp.sln`
	- Then simply click `Start` and it will run 

- JavaFX
  - To install `Java` follow the instructions from the [official](https://java.com/en/download/) website
  - To start executable jar in explorer
	  - Run  `TeamManagerJavaFX/target/TeamManagerJavaFX-1.0-SNAPSHOT.jar`
  - Starting an executable jar in IntelliJ
	  - Open
  	- Select `TeamManagerJavaFX`
  	- On the right side of the `Lifecycle` tab, a new jar is created by selecting and running the package
  	- After the run, select the target folder in the project directory on the left side and select the newly created `TeamManagerJavaFX-1.0-SNAPSHOT-jar-with-dependencies.jar` file
  	- Right click, then Run (Ctrl + Shift + F10)
  - Starting in IntelliJ:
	  - Open
	  - Select `TeamManagerJavaFX`
	  - On the right side of the maven tab plugins, then click on `javafx:run`
- Important: the folder structure of the project must be preserved. The db, TeamManagerCSharp and TeamManagerJavaFX must remain within a folder, otherwise they will not access the database!

# SQLite 🛠️

- Both applications use the same database file as `db/team.db`
- Database schemas and detailed descriptions can be found in `Database documentation.pdf`
- Open in Windows (x64)
  - Open a command line prompt and navigate to the db folder
  - To check tables, you need the following commands:
	  ```sql
	  >> sqlite3 team.db
	  >> .fullschema
	  ```
  - The values can be retrieved:
      ```sql
    >> SELECT * FROM [table name];
    ```
- For other operating systems please follow the instructions from the [official](https://www.sqlite.org/download.html) website

# C# Program ⚽

![screenshot](https://github.com/zolvin/team-manager/blob/main/Misc/demo1.gif)

- I created the application using `WinForms` in Visual Studio. Within the application you can add, modify and list players and teams.
- Listing: List Player/Team within the Player/Team menu
- Add: Add Player/Team within the Player/Team menu, then listing
- Modify: After listing, click on the item you want to modify and then list
- During the preparation, I applied validation everywhere and commented on the most important parts

# JavaFX Program 🥅

![screenshot](https://github.com/zolvin/team-manager/blob/main/Misc/demo2.gif)

- I created the application according to the principles of MVC
- I used version 11 Java during the development
- During the preparation, I applied validation everywhere and commented on the most important parts
- Teams can be managed individually within the application
- After start, we select the team we want to manage. The players within the team are then visible and the following features are available:
	- Messages: Sent (Sender's name), Inbox (Recipient's name), View message and Send message (Menu bar)
	- Results: Total, Home and Away results. By clicking on a given match, you can see who played and scored a goal from the managed team, as well as the details of the trip, if the match took place away
	- Add match (For multiple active players, the save may take a few seconds): You can add a match to the managed team. The data for the home team should be on the left and the away team should be on the right. You can swap them by clicking on the double arrow
	- Top row data: Home Team, Home Goal(s), Replace Button, Away team, Away Goal
	- At the bottom center, we can enter the away team
	- The players table is "Active?" column can be used to mark the players who played in the match. If they have played and scored a goal, they can be added to the scorers with the Goal button
	- After adding the goal scorers in the table, you can specify the number of goals scored by the given player by double-clicking on the 'Goal number' cell and can be saved with enter (it can be modified in the same way if typed). You can remove it from the scorers with the X key. When saving, if there is incorrect data, we get the corresponding error message

</br>
:star: Star me on GitHub — it helps!
