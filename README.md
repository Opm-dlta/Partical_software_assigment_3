Group member 
Name:Luong Anh Duc,ID:26012107
Task: Logic, and file structure 


# Cinema Ticket Management System


A Java Swing desktop application for browsing cinema movies, selling tickets, and managing the movie catalogue. Movie and staff roles use shared model and manager classes so the same movie data is shown throughout the application.

## Features

- Load movie records from the root-level `movies.txt` file.
- Log in as a ticket seller or manager.
- Browse movies and search by category and title.
- View full movie details and sell tickets. A successful sale reduces the available ticket count.
- Managers can add, update, and delete movies.
- Managers can export the current movie list to a user-selected file in the input format.
- Unit tests use JUnit 5.

## Project structure

```text
assigment_3/
├── movies.txt                 # Initial movie data; keep in the project root
├── pom.xml                    # Maven configuration and JUnit 5 dependency
├── README.md
├── src/
│   ├── Main.java              # Application entry point
│   ├── background_image/
│   │   └── login-background.jpg
│   ├── manager/
│   │   ├── MovieManager.java  # Loads, searches, changes, and exports movies
│   │   └── StaffManager.java  # Creates staff accounts and checks logins
│   ├── movie/
│   │   ├── Movie.java         # Abstract movie base class
│   │   ├── ActionMovie.java
│   │   ├── ComedyMovie.java
│   │   ├── RomanceMovie.java
│   │   └── SciFiMovie.java
│   ├── staff/
│   │   ├── Staff.java         # Abstract staff base class
│   │   ├── TicketSeller.java
│   │   ├── Manager.java
│   │   └── MovieBrowser.java  # Shared browse actions for both staff roles
│   └── ui/
│       ├── LoginPanel.java
│       ├── MainGUI.java       # Selects tabs based on staff role
│       ├── BrowsePanel.java   # Search, details, and ticket sales
│       ├── ManagePanel.java   # Manager movie actions and export
│       └── MovieEditorDialog.java
├── test/
│   ├── manager/
│   │   ├── MovieManagerTest.java
│   │   └── StaffManagerTest.java
│   ├── movie/
│   │   └── MovieCreationTest.java
│   └── staff/
│       └── TicketSellerTest.java
└── target/                    # Maven build output (generated)
```

The project uses a custom Maven layout: Java source is in `src/` and tests are in `test/` (rather than Maven's default `src/main/java` and `src/test/java`). The `pom.xml` configures these locations and includes the login image as a resource.

## How the application works

1. `Main` creates one `MovieManager` and one `StaffManager`, then opens the login screen.
2. `MovieManager` reads the movie records from `movies.txt` and creates the matching movie subclass.
3. `StaffManager` checks the entered username and password and returns a `TicketSeller` or `Manager` object.
4. `MainGUI` displays the features for that role. Both roles get Browse; managers also get Manage.
5. The panels call the staff objects, which use the shared `MovieManager` to access and change movie data.
6. Changes are held in memory during the session. Manager Export writes the current records to a file selected by the user; it does not need to overwrite the original `movies.txt`.

## Requirements

- JDK 26, as configured by `maven.compiler.release` in `pom.xml`.
- Apache Maven.

## Build and run

Run these commands from the project root, where `movies.txt` is located:

```bash
mvn clean package
java -cp target/classes Main
```

In IntelliJ IDEA, open the project, allow Maven to import `pom.xml`, then run `Main`. Keep the working directory set to the project root so the application can find `movies.txt`.

Run the JUnit 5 test suite with:

```bash
mvn test
```

## How to use

### Ticket seller

1. On the login screen, choose Seller login.
2. Enter one of the seller accounts below.
3. In Browse, choose a category, optionally enter part of a title, and click **Search**. Click **Show All** to reset the list.
4. Select a movie and click **View Details**, or double-click its row.
5. Select a movie and click **Sell Ticket**. The available count decreases by one when tickets remain.

### Manager

1. On the login screen, choose Manager login.
2. Enter one of the manager accounts below.
3. Use Browse for the same movie search, details, and ticket sale actions as a seller.
4. Open Manage to add, update, or delete movies. Select a row before updating or deleting. The movie ID cannot be changed during an update.
5. Click **Export Movies**, choose a destination, and confirm if you are replacing an existing file.

### Provided login accounts

| Role | Username | Password |
|---|---|---|
| Ticket seller | `s1` | `s1` |
| Ticket seller | `s2` | `s2` |
| Ticket seller | `s3` | `s3` |
| Manager | `m1` | `m1` |
| Manager | `m2` | `m2` |

The selected login role must match the account's role.

## Movie data format

Each line in `movies.txt` uses this comma-separated format:

```text
Category, MovieID, Title, Director, Duration, Price, ShowTime, ExtraAttribute, AvailableTickets
```

Supported categories are Action, Comedy, Romance, and Science Fiction. The category-specific extra field stores the additional movie attribute. The supplied movie list starts with 50 available tickets per movie.

## Team members and contributions

Update this section with both team members' names, student IDs, and actual task contributions before submission.

| Team member | Student ID | Main contributions |
|---|---|---|
| `[Name]` | `[ID]` | `[Describe completed tasks]` |
| `[Name]` | `[ID]` | `[Describe completed tasks]` |

## Repository

Private GitHub repository: [Partical_software_assigment_3](https://github.com/Opm-dlta/Partical_software_assigment_3).

## Assignment submission checklist

The assignment also requests an instruction manual PDF with GUI screenshots, a metrics report under `reports/metrics/`, and a GitHub Actions workflow. Add those deliverables to the project before submission if they are not already maintained elsewhere.
