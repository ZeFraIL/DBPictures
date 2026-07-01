# MainActivity - Description

## 1. General information
*   **Class Name:** `MainActivity`
*   **Type:** Activity (Screen)
*   **Purpose:** This is the "Main Menu" of the application. It provides the user with two main options: adding a new record with a photo or viewing existing records.
*   **Interaction:** It acts as a launcher. It starts `AddToDB` when the user wants to take a photo and `ViewFromDB` when the user wants to see the saved data.

## 2. Variables (class fields)
| Name | Type | Purpose | Where is it used |
| :--- | :--- | :--- | :--- |
| `bAdd` | `Button` | Button to go to the adding screen. | `onCreate()` |
| `bView` | `Button` | Button to go to the viewing screen. | `onCreate()` |
| `context` | `Context` | Holds information about the application environment. | `onCreate()`, used for starting Intents. |

*   **Context:** Think of it as the "passport" of the app. Android needs it to know which part of the app is requesting an action.

## 3. Classroom Methods
### Method name: `onCreate`
*   **Type:** `protected`
*   **Return value:** `void` (returns nothing)
*   **Parameters:**
    | Name | Type | Description |
    | :--- | :--- | :--- |
    | `savedInstanceState` | `Bundle` | Stores the state of the screen if it was closed and restarted. |
*   **Logic:**
    1.  Calls the parent method `super.onCreate` to perform standard initialization.
    2.  Sets the layout from `activity_main.xml`.
    3.  Initializes the `context` variable.
    4.  Finds the buttons in the layout using `findViewById`.
    5.  Sets "Listeners" (code that waits for a click) on the buttons.
*   **When called:** Automatically by the Android system when the app starts.
*   **Important:** This is the entry point. If you don't call `setContentView`, the screen will be empty.

## 4. Lifecycle (Activity)
*   **`onCreate()`:** Called when the screen is first created. Here, the interface is linked to the code, and the main logic is initialized.

## 5. Interface Interaction (UI)
*   **Elements:** `Button` (bAdd, bView).
*   **Linkage:** Using `findViewById(R.id...)`.
*   **Events:** `setOnClickListener` handles user taps.

## 6. Interaction with other components
*   **`Intent`:** Used to navigate from `MainActivity` to `AddToDB` or `ViewFromDB`. An Intent is essentially a "message" to the Android system saying "I want to open this screen."

## 7. General logic of the class
1.  The app starts.
2.  `MainActivity` loads two buttons.
3.  The user clicks a button.
4.  The app sends an `Intent` to open the requested screen.

## 8. Simplified explanation
**Explanation in simple words:**
Think of `MainActivity` as the **reception desk** in a hotel. When you walk in, the receptionist doesn't give you a room immediately; they ask what you want to do. You have two buttons (options): "Go to the Photo Studio" (Add) or "Go to the Gallery" (View). `MainActivity` simply points you in the right direction.
