# Info - Description

## 1. General information
*   **Class Name:** `Info`
*   **Type:** Normal Class (Model / POJO)
*   **Purpose:** This class is a "container" for data. It holds a single record consisting of a comment and an image (as bytes).
*   **Interaction:** 
    *   `ViewFromDB` creates list of `Info` objects to manage data read from the database.

## 2. Variables (class fields)
| Name | Type | Purpose | Where is it used |
| :--- | :--- | :--- | :--- |
| `infoComment` | `String` | Stores the text description of the place. | Constructor, Getters/Setters |
| `infoImage` | `byte[]` | Stores the actual image as a sequence of numbers (bytes). | Constructor, Getters/Setters |

*   **byte[]:** An array of small numbers. Since a digital image is just a collection of numbers representing colors, we store it this way in memory.

## 3. Classroom Methods
### Method name: `Info` (Constructor)
*   **Type:** `public`
*   **Parameters:**
    | Name | Type | Description |
    | :--- | :--- | :--- |
    | `infoComment` | `String` | The text to store. |
    | `infoImage` | `byte[]` | The image bytes to store. |
*   **Logic:** Assigns the incoming parameters to the class fields.
*   **When called:** When we want to create a new record object in memory.

### Method name: `getInfoComment` / `setInfoComment`
*   **Purpose:** Standard "Getters" and "Setters". They allow other classes to safely read or change the comment.

### Method name: `getInfoImage` / `setInfoImage`
*   **Purpose:** Standard "Getters" and "Setters" for the image data.

## 4. Lifecycle
*   **Normal Object:** Lives as long as the application needs it in memory (usually while `ViewFromDB` is open).

## 5. Interface Interaction (UI)
*   **None directly:** It provides data to the UI components (like `AlertDialog`).

## 6. Interaction with other components
*   **ViewFromDB:** Used within `ArrayList<Info>`.

## 7. General logic of the class
1.  Acts as a "package". Instead of carrying a string and a byte array separately, we put them into one `Info` object.

## 8. Simplified explanation
**Explanation in simple words:**
Think of `Info` as an **envelope**. 
Inside the envelope, there is a **letter** (the comment) and a **photo** (the image bytes). 
When the librarian (`ViewFromDB`) fetches data from the warehouse (`HelperDB`), they put the letter and photo into this envelope so it's easy to carry around the app.
