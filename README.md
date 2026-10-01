# Partical_software_assigment-2-
assismnget 2 movie tciket website  
---

# 📘 **Cinema Ticket Management System – README Overview**

## 🎯 **Project Description**
The **Cinema Ticket Management System** is a Java-based desktop application built using **Object-Oriented Programming (OOP)** principles and **Java Swing**.  
It simulates how cinema staff manage movies, showtimes, and ticket sales.

The system supports two staff roles:

- **Ticket Sellers** – can log in, browse movies, search movies, and sell tickets.  
- **Managers** – can do everything sellers can, plus add, update, and delete movies.

The program loads movie data from a provided `movies.txt` file and allows exporting updated movie information to a new file.

---

## 🧱 **System Architecture**
The system is divided into **five major layers**, each responsible for a different part of the application.

---

## 1️⃣ **Data Layer (Core Classes)**  
This layer defines the main objects used in the system.

### **Movie Hierarchy**
- `Movie` (abstract base class)
- `ActionMovie`
- `ComedyMovie`
- `RomanceMovie`
- `SciFiMovie`

Each movie stores:
- ID  
- Title  
- Director  
- Duration  
- Price  
- Show time  
- Available tickets  
- Extra attribute (varies by category)

### **Staff Hierarchy**
- `Staff` (abstract base class)
- `TicketSeller`
- `Manager`

Each staff member has:
- Username  
- Password  
- Role  

---

## 2️⃣ **Business Logic Layer**
This layer handles all operations on movies and staff.

### **MovieManager**
Responsible for:
- Loading movies from `movies.txt`
- Searching movies by category/title
- Selling tickets
- Adding new movies
- Updating movie details
- Deleting movies
- Exporting updated movie data

### **StaffManager**
Responsible for:
- Storing staff accounts  
- Validating login credentials  
- Returning the correct staff role (Seller or Manager)

---

## 3️⃣ **User Roles Layer**
Defines what each type of staff can do.

### **Ticket Seller**
- Login  
- View all movies  
- Search movies  
- Sell tickets  

### **Manager**
- All seller functions  
- Add movies  
- Update movies  
- Delete movies  

Role-based access is enforced in the GUI.

---

## 4️⃣ **GUI Layer (Java Swing)**
The graphical interface is built using Swing components.

### **MainGUI**
- Hosts all panels  
- Receives `MovieManager` and `StaffManager`  
- Controls tab visibility based on staff role  

### **LoginPanel**
- Username + password fields  
- Login button  
- Validates staff using `StaffManager`  
- Redirects based on role  

### **BrowsePanel**
- Displays all movies in a table  
- Search bar  
- Book ticket button  

### **ManagePanel** *(Manager only)*
- Add movie  
- Update movie  
- Delete movie  
- Clear fields  

---

## 5️⃣ **Utility Layer**
Additional features required by the assignment.

### **File Import**
- Reads `movies.txt`  
- Creates correct movie subclass  
- Initializes ticket count  

### **File Export**
- Saves current movie list to a new file  
- Same format as input file  

### **JUnit Testing**
Tests include:
- Movie creation  
- Login validation  
- Search function  
- Add/update/delete movie  
- Ticket booking logic  

### **Code Metrics**
Generated using a metrics tool and stored in:
```
reports/metrics/
```

---

# 🔗 **How Components Work Together**
- `Main.java` starts the program  
- `MovieManager` loads movies → GUI displays them  
- `StaffManager` validates login → GUI enables correct features  
- `TicketSeller` interacts with movies to sell tickets  
- `Manager` interacts with movies to add/update/delete  
- GUI panels call managers to perform operations  
- Export function saves updated movie data  
- JUnit tests verify system correctness  

---

# 📂 **Project Structure**
```
src/
 ├── main/java/
 │    ├── Movie classes
 │    ├── Staff classes
 │    ├── MovieManager
 │    ├── StaffManager
 │    ├── GUI panels
 │    └── Main.java
 ├── test/java/
 │    └── JUnit tests
reports/
 └── metrics/
movies.txt
InstructionManual.pdf
README.md
```

---

# 🧑‍🤝‍🧑 **Group Members**
(Add your names + student IDs here)

---

# 🛠️ **How to Run the Program**
1. Open the project in IntelliJ  
2. Ensure `movies.txt` is in the project root  
3. Run `Main.java`  
4. Log in using one of the provided accounts:  

### **Ticket Sellers**
- s1 / s1  
- s2 / s2  
- s3 / s3  

### **Managers**
- m1 / m1  
- m2 / m2  

---

# 🔗 **GitHub Repository**
(Add your repo link here)

---

