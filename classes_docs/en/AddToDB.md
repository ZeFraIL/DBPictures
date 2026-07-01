# AddToDB - Description

## 1. General information
*   **Class Name:** `AddToDB`
*   **Type:** Activity (Screen)
*   **Purpose:** This screen allows the user to take a photo using the device camera, add a text comment, and save both to a local database.
*   **Interaction:** 
    *   Requests permissions from the Android system.
    *   Starts the system Camera app.
    *   Saves data into `HelperDB` (SQLite).

## 2. Variables (class fields)
| Name | Type | Purpose | Where is it used |
| :--- | :--- | :--- | :--- |
| `ivImage` | `ImageView` | To display the photo taken by the camera. | `onCreate()`, `onActivityResult()` |
| `imageFilename` | `String` | Temporary name for the image file. | `ivImage.onClick`, `createImageFile()` |
| `imageUri` | `Uri` | The "address" of the image file in the system. | `callCameraApp()`, `onActivityResult()` |
| `helperDB` | `HelperDB` | Connection to the SQLite database. | `bSave.onClick` |
| `db` | `SQLiteDatabase` | Object to perform SQL operations (Insert). | `bSave.onClick` |
| `etComment` | `EditText` | Text field for the user to type a description. | `onCreate()`, `bSave.onClick` |
| `bSave` | `Button` | Button to save data to the database. | `onCreate()` |

## 3. Classroom Methods
### Method name: `callCameraApp`
*   **Type:** `private`
*   **Return value:** `void`
*   **Logic:**
    1.  Creates an Intent for `ACTION_IMAGE_CAPTURE` (opens the camera).
    2.  Creates a temporary file for the photo using `createImageFile()`.
    3.  Uses `FileProvider` to get a secure URI (address) for the file.
    4.  Passes this URI to the camera app so it knows where to save the photo.
    5.  Starts the camera using `startActivityForResult`.
*   **When called:** When the user clicks on the `ImageView`.

### Method name: `onActivityResult`
*   **Type:** `protected`
*   **Return value:** `void`
*   **Parameters:**
    | Name | Type | Description |
    | :--- | :--- | :--- |
    | `requestCode` | `int` | ID of the request (3 for camera). |
    | `resultCode` | `int` | Whether the user took the photo or cancelled. |
    | `data` | `Intent` | Extra data (not used here because we use `imageUri`). |
*   **Logic:** If the user successfully took a photo, it sets the `ivImage` to display that photo using the `imageUri`.

### Method name: `bSave.onClick` (Inside `onCreate`)
*   **Logic:**
    1.  Gets text from `etComment`.
    2.  Gets the bitmap (image) from `ivImage`.
    3.  Converts the image to a `byte[]` (binary data) using `ByteArrayOutputStream`.
    4.  Opens the database in writable mode.
    5.  Puts the comment and the image bytes into a `ContentValues` object.
    6.  Inserts the row into the database.
    7.  Closes the database and finishes the activity.

## 4. Lifecycle (Activity)
*   **`onCreate()`:** Requests permissions (Camera, Storage) and sets up UI listeners.
*   **`onActivityResult()`:** Handles the return from the camera app to show the picture.

## 5. Interface Interaction (UI)
*   **`ImageView`:** Acts as a button to open the camera and a preview of the photo.
*   **`EditText`:** Captures user text.
*   **`Button`:** Triggers the saving process.

## 6. Interaction with other components
*   **Camera App:** External system application.
*   **FileProvider:** Used for secure file sharing between apps.
*   **HelperDB:** Custom class to manage the database.

## 7. General logic of the class
1.  User grants permissions.
2.  User taps the image icon -> Camera opens.
3.  User takes photo -> Return to app, photo is displayed.
4.  User types comment and hits Save.
5.  App converts image to bytes and saves to SQLite.

## 8. Simplified explanation
**Explanation in simple words:**
Imagine you are a **journalist**. `AddToDB` is your **notebook and camera**. 
1.  You take a picture (Camera).
2.  You write a caption (EditText).
3.  You glue the photo into your notebook and write the text next to it (SQLite Database).
The `FileProvider` is like a **security guard** that safely carries the photo from the camera to your notebook.
