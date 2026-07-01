# HelperDB - Description

## 1. General information
*   **Class Name:** `HelperDB`
*   **Type:** Normal Class (specifically an `SQLiteOpenHelper`)
*   **Purpose:** This class is responsible for creating and managing the local SQLite database. It defines the tables, columns, and handles database versioning.
*   **Interaction:** It is used by `AddToDB` (to save data) and `ViewFromDB` (to read data).

## 2. Variables (class fields)
| Name | Type | Purpose | Where is it used |
| :--- | :--- | :--- | :--- |
| `DBNAME` | `String` | The actual filename of the database file on the phone (`places.db`). | `HelperDB` constructor |
| `PLACE_TABLE` | `String` | The name of the table (`Places`). | `onCreate`, `AddToDB`, `ViewFromDB` |
| `PLACE_COMMENT`| `String` | The name of the text column (`About_place`). | `onCreate`, `AddToDB`, `ViewFromDB` |
| `PLACE_PICTURE`| `String` | The name of the image column (`Place_picture`). | `onCreate`, `AddToDB`, `ViewFromDB` |

*   **Variables as Constants:** These are `static final`, meaning they are shared and cannot be changed. This prevents typos in SQL queries.

## 3. Classroom Methods
### Method name: `HelperDB` (Constructor)
*   **Type:** `public`
*   **Logic:** Calls the parent constructor with the database name and version (1).
*   **When called:** When you create a new instance (`new HelperDB(context)`).

### Method name: `onCreate`
*   **Type:** `public`
*   **Return value:** `void`
*   **Parameters:**
    | Name | Type | Description |
    | :--- | :--- | :--- |
    | `db` | `SQLiteDatabase` | The database object where the table will be created. |
*   **Logic:** 
    1.  Constructs a `CREATE TABLE` SQL command as a string.
    2.  Specifies that `PLACE_COMMENT` is `TEXT` and `PLACE_PICTURE` is `BLOB`.
    3.  Executes the command using `db.execSQL`.
*   **When called:** Automatically by Android the very first time the app is run and needs the database.
*   **Important:** `BLOB` stands for **Binary Large Object**. It is used to store raw bytes, like images or music files.

### Method name: `onUpgrade`
*   **Type:** `public`
*   **Logic:** Currently empty. It is intended to handle changes in the database structure in future app updates.

## 4. Lifecycle
*   **Not an Activity:** This class follows the application's lifecycle, specifically triggered when the database is accessed.

## 5. Interface Interaction (UI)
*   **None:** This class works entirely "under the hood" (in the background).

## 6. Interaction with other components
*   **SQLite:** It interacts directly with the Android SQLite engine.

## 7. General logic of the class
1.  Provides the names of tables and columns to other classes.
2.  Ensures that the table exists when the app needs to save or read data.

## 8. Simplified explanation
**Explanation in simple words:**
Think of `HelperDB` as the **architect and builder** of a warehouse.
1.  The architect decides the building's name (`DBNAME`).
2.  The architect draws a plan for a room called "Places" (`PLACE_TABLE`).
3.  The room has two shelves: one for notebooks (`TEXT` comments) and one for photo albums (`BLOB` images).
When the app starts for the first time, `HelperDB` builds this warehouse so that other classes can store and find things in it.
