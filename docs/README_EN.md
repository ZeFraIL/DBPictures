# 📱 Android App Documentation (Level 10/10)

---

## 🧾 General Information
**Project Name:**
DBPictures
**Author(s):**
Zeev Fraiman
**Date:**
July 2026
**Language:**
Java
**Development Environment:**
Android Studio
**Android Version (minSdk / targetSdk):**
minSdk 26 / targetSdk 30

---

## 🎯 Project Goal
*   **What task does the app solve?**
    It allows users to capture photos of places and save them with custom text comments directly into a local SQLite database for offline storage and retrieval.
*   **Why is this task important?**
    It provides a simple way to create a personal visual diary or catalog of locations without needing cloud storage or an internet connection.
*   **Target Audience:**
    Travelers, researchers, or anyone who wants to quickly log locations with photos and notes.

---

## 📌 App Requirements
### Functional Requirements
*   Ability to launch the camera app.
*   Ability to save a photo and a text comment to the database.
*   Ability to view a list of all saved records.
*   Ability to view detailed information (image + comment) in a dialog.
### Non-functional Requirements
*   **Performance:** Fast response time for database operations and image loading.
*   **Usability:** Simple and intuitive two-button main interface.
*   **Reliability:** Robust handling of camera intent results and database persistence.

---

## 🧠 General Architecture
*   **Selected Approach:**
    MVC (Model-View-Controller) - standard for traditional Android Java development.
*   **Why was it chosen?**
    Given the project's simplicity, MVC provides a straightforward structure where Activities handle both UI logic and control flow, while the `HelperDB` and `Info` classes represent the Data layer.
*   **Main System Components:**
    *   `MainActivity`: Entry point/navigation.
    *   `AddToDB`: Controller for taking photos and saving data.
    *   `ViewFromDB`: Controller for retrieving and listing data.
    *   `HelperDB`: Database management (SQLite).
    *   `Info`: Data model class.

---

## 🧩 UML Diagram
`[MainActivity]` –> `[AddToDB]` / `[ViewFromDB]`
`[AddToDB]` –> `[HelperDB]`
`[ViewFromDB]` –> `[HelperDB]` & `[Info]`

**Explanation:**
- The packages are organized by responsibility (UI, Data).
- This separation helps in scaling by allowing for more complex data models or additional activities to be added without modifying the core navigation.

---

## 🧩 Detailed Class Description
### 📌 Class: MainActivity
*   **Role:** Main entry point.
*   **Responsibility:** Provides navigation to "Add" and "View" modules.
*   **Main Methods:**
    *   `onCreate()`: Initializes buttons and sets up click listeners for navigation.
*   **Interaction with other classes:** Starts `AddToDB` and `ViewFromDB` via Intents.

### 📌 Class: AddToDB
*   **Role:** Data entry activity.
*   **Responsibility:** Manages camera interaction, user input (EditText), and database insertion.
*   **Main Methods:**
    *   `callCameraApp()`: Prepares the file and triggers the camera intent.
    *   `onActivityResult()`: Receives the photo and displays it in the ImageView.
    *   `bSave.onClick()`: Compresses the image to a byte array and saves it to SQLite.

### 📌 Class: ViewFromDB
*   **Role:** Data retrieval activity.
*   **Responsibility:** Fetches records from SQLite and displays them in a ListView.
*   **Main Methods:**
    *   `buildList()`: Queries the database and populates the list adapter.
    *   `onItemClick()`: Displays a full-sized image and its comment in an AlertDialog.

### 📌 Class: Repository / Data Layer (HelperDB)
*   **Role:** Persistence layer.
*   **Responsibility:** Creates and manages the SQLite database schema (`Places` table).

---

## 🔄 App Operation Diagram
1.  User opens the app (`MainActivity`).
2.  User clicks "Add" -> `AddToDB` opens.
3.  User clicks on `ImageView` -> Camera opens.
4.  User takes a photo -> Photo is shown in `ImageView`.
5.  User enters a comment and clicks "Save" -> Data is stored in SQLite.
6.  User goes back and clicks "View" -> `ViewFromDB` displays the list.
7.  User clicks on a list item -> `AlertDialog` shows the photo and text.

---

## 🎨 UI/UX Analysis
*   **Why is the interface made this way?**
    To minimize complexity. Big buttons on the main screen lead to clear, separate tasks.
*   **Principles used:**
    *   **Simplicity:** No unnecessary menus.
    *   **Logical flow:** Step-by-step process for adding data.
    *   **Accessibility:** Large interactive elements.
*   **What can be improved?**
    Adding an Image Gallery view instead of a simple text list.

---

## ⚙️ Threading
*   **Used technologies:**
    *   Main UI Thread (for interactions).
*   **Why this method?**
    For a small local DB, UI thread operations are acceptable, though `AsyncTask` or `Executors` would be better for larger datasets.
*   **Prevention of issues:**
    *   ANRs are avoided by keeping DB operations minimal.
    *   Memory leaks are minimized by using standard activity lifecycle management.

---

## 💾 Data Management
*   **Storage Location:** Local SQLite database (`places.db`).
*   **Why this method?**
    It's reliable, structured, and doesn't require an internet connection.
*   **Ensuring safety/correctness:**
    *   `HelperDB` handles table creation and schema consistency.
    *   Blob storage ensures image data is kept together with its metadata (comment).

---

## 🌐 Networking
*   **Status:** Not used in the current version. All operations are offline.

---

## 🔐 Security (Basic)
*   **Sensitive Data:** Only user photos and comments.
*   **Protection:** Data is stored in the application's internal database directory, which is private to the app.

---

## 🧪 Testing
*   **Current tests:**
    *   Manual functional testing of the camera flow.
    *   Manual testing of DB persistence and retrieval.
*   **Verified:**
    *   Camera image capture and URI handling.
    *   Database insertion and querying logic.

---

## 🐞 Error Handling
*   **Anticipated errors:**
    *   Camera not available.
    *   Empty comments (handled by setting a default "Nothing").
    *   Permissions not granted.
*   **Response:** Toasts and simple checks.

---

## ⚡ Performance
*   **Optimizations:** Image compression (PNG) before DB storage.
*   **Bottlenecks:** Large BLOBs in SQLite can slow down the cursor if the table becomes very large.

---

## 🚀 Expansion Possibilities
*   Adding GPS coordinates to photos.
*   Adding a search feature in the list.
*   Synchronizing with cloud storage (Firebase).

---

## 📊 Project Self-Assessment
| Criterion | Rating (1–10) |
| :--- | :--- |
| Architecture | 7 |
| Code Quality | 8 |
| UI/UX | 7 |
| Reliability | 9 |
| **Total Level** | **8** |

---

## 🏁 Conclusion
*   **Best outcome:** Robust SQLite persistence for media.
*   **Challenges:** Handling FileProvider for camera URI on modern Android versions.
*   **Skills mastered:** SQLite BLOB management, Camera API, FileProvider.

---

## 📎 Appendix
*   **Screenshots:** [Attached in /assets]
*   **Diagrams:** [Class Diagram]
*   **Repository Link:** [Git Link]
