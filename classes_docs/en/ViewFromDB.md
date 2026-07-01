# ViewFromDB - Description

## 1. General information
*   **Class Name:** `ViewFromDB`
*   **Type:** Activity (Screen)
*   **Purpose:** This screen displays a list of all saved locations. When a user clicks on an item in the list, it shows the saved photo and comment in a pop-up window.
*   **Interaction:** 
    *   Reads data from `HelperDB`.
    *   Uses the `Info` class to store data temporarily in memory.
    *   Populates a `ListView` with the titles (comments).

## 2. Variables (class fields)
| Name | Type | Purpose | Where is it used |
| :--- | :--- | :--- | :--- |
| `helperDB` | `HelperDB` | To access the database. | `buildList()` |
| `db` | `SQLiteDatabase` | Object to read data from tables. | `buildList()` |
| `lv` | `ListView` | The UI element that shows the list. | `onCreate()`, `buildList()` |
| `all` | `ArrayList<String>` | List of comments to show in the ListView. | `buildList()` |
| `allinfo` | `ArrayList<Info>` | List of full objects (text + image bytes). | `buildList()`, `onItemClick` |
| `adapter` | `ArrayAdapter` | The bridge between the data and the ListView. | `buildList()` |
| `context` | `Context` | Application environment. | `onCreate()`, `onItemClick` |

## 3. Classroom Methods
### Method name: `buildList`
*   **Type:** `private`
*   **Return value:** `void`
*   **Logic:**
    1.  Initializes the lists `all` and `allinfo`.
    2.  Opens the database in readable mode.
    3.  Queries the `Places` table to get all rows.
    4.  Uses a `Cursor` (a pointer) to go through each row.
    5.  For each row:
        *   Gets the comment (column 0).
        *   Gets the image bytes (column 1).
        *   Creates an `Info` object and adds it to `allinfo`.
        *   Adds the comment to the `all` list.
    6.  Closes the database.
    7.  Creates an `ArrayAdapter` and connects it to the `ListView`.
*   **When called:** Automatically in `onCreate()` to show data immediately.

### Method name: `onItemClick` (Inside `onCreate`)
*   **Logic:**
    1.  Identifies which item was clicked.
    2.  Gets the corresponding `Info` object from `allinfo`.
    3.  Converts the `byte[]` from the database back into a `Bitmap` (image) using `BitmapFactory`.
    4.  Creates an `AlertDialog` (pop-up).
    5.  Sets the title of the pop-up to the comment.
    6.  Creates an `ImageView` and puts the bitmap in it.
    7.  Shows the pop-up to the user.
*   **When called:** When the user taps a line in the list.

## 4. Lifecycle (Activity)
*   **`onCreate()`:** Initializes the UI, sets up the list click listener, and calls `buildList()`.

## 5. Interface Interaction (UI)
*   **`ListView`:** Displays the rows of data.
*   **`AlertDialog`:** Shows the image details without leaving the screen.

## 6. Interaction with other components
*   **HelperDB:** To read the SQLite data.
*   **Info Class:** Used as a container for each record.

## 7. General logic of the class
1.  Screen opens.
2.  App reads everything from SQLite.
3.  App shows only the comments in a list.
4.  User clicks a comment.
5.  App finds the image hidden behind that comment and shows it in a pop-up.

## 8. Simplified explanation
**Explanation in simple words:**
Imagine a **library catalog**. 
1.  `ViewFromDB` is the catalog drawer.
2.  Each card in the drawer has a title (the comment). 
3.  When you pull out a card (click a list item), the librarian brings you the **actual photo** (the Bitmap) from the storage room (the Database) and shows it to you.
The `Cursor` is like your **finger** pointing at one line in the book at a time while you read it.
