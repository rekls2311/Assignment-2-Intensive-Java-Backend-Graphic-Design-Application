# Assignment2
 
# Smart Canvas: A Graphic Design Application

This is an individual programming assignment for **COSC2391 Further Programming**. This application, named Smart Canvas, is a graphic design tool that allows users to create visual content like posters and cards. It features a graphical user interface for easy manipulation of various elements.

---

### Features

* **User Authentication**: Secure user login and account creation.
* **Canvas Management**: Users can create a new canvas with custom dimensions (width and height).
* **Drawing Tools**: Add and manipulate different shapes and elements on the canvas, including text, rectangles, circles, and images from a local machine.
* **Element Properties**: Adjust properties like color, size, and position for selected elements through dedicated property panels.
* **Database Integration**: User data (accounts) and user information are stored in a local SQLite database.

---

### How to Run the Application

This project uses **Maven** for dependency management and building. Follow these steps to set up and run the application.

1.  **Prerequisites**:
    * **Java Development Kit (JDK) 18** or later.
    * **Apache Maven** installed.
    * A Git client to clone the repository.

2.  **Clone the Repository**:
    ```bash
    git clone [https://github.com/your-username/your-repository-name.git](https://github.com/your-username/your-repository-name.git)
    cd your-repository-name
    ```

3.  **Compile and Run**:
    * Open a terminal or command prompt in the project's root directory.
    * Use the Maven command to run the application. The `javafx-maven-plugin` is configured to handle the execution.
    ```bash
    mvn clean javafx:run
    ```
    * This command will automatically download all dependencies and launch the application's GUI.

---
---

### User Guide with Screenshots

This section walks through the application's flow from start to finish.

#### 1. Login GUI

After running the application, the first thing you'll see is the Login screen. From here, you can log in with an existing account or create a new one.

![Login Screen](https://github.com/rekls2311/Assignment-2-Intensive-Java-Backend-Graphic-Design-Application/blob/e691d7f4ad660be724981782adde08ea01baa4b4/assests/login%20screen.png)


#### 2. Sign Up for a New Account

To create a new account, click the **"Sign Up"** button on the Login screen. You'll be prompted to enter a username, password, first name, and last name. If a username already exists, the application will prevent you from creating a duplicate account. You can also personalize your account by adding a profile picture.

![Sign Up Screen](https://github.com/rekls2311/Assignment-2-Intensive-Java-Backend-Graphic-Design-Application/blob/d8164e8f08c04c5ca69aecf366131ea7aab1a629/assests/signup%20screen.png)

#### 3. Main Application Canvas

Once logged in, you can start creating! The main GUI displays your profile picture and name at the top, a clear indicator of the "smart" user experience.

![Main Canvas Screen](https://github.com/rekls2311/Assignment-2-Intensive-Java-Backend-Graphic-Design-Application/blob/d8164e8f08c04c5ca69aecf366131ea7aab1a629/assests/main%20canvas%20screen.png)

*You can then play around with it. By creating a new canvas up to your size, add in text, circles, rectangles, and your images.*

#### 4. Manipulating Elements

The application allows you to add various elements to your canvas, each with its own customizable properties.

* **Create a new canvas**: Click on **File** then **New Canvas** to specify your desired width and height.
* **Add elements**: Use the buttons on the left sidebar to add **Text**, **Circles**, **Rectangles**, or **Images**.
* **Edit properties**: Select an element on the canvas to open its property panel on the right, where you can change its color, font, size, and more.
* **Delete an element**: You can delete individual elements from the canvas as needed.

![Adding Elements](https://github.com/rekls2311/Assignment-2-Intensive-Java-Backend-Graphic-Design-Application/blob/d52ebb74754734de1ea670b9480041942ead9bf1/assests/adding%20elements.png)

#### 5. Saving Your Work

The application also provides a way to save your work. Press **File** then **Save As** to save your completed design.

Enjoy!

---
### File Structure

This section explains the purpose of the key directories and files in the project.

#### Key Files Explained

* `pom.xml`: The **Maven Project Object Model** file. It manages the project's dependencies (JavaFX, SQLite JDBC) and defines how the project is built and run.
* `src/main/java/`: Contains all the application's Java source code.
    * `Main.java`: The primary entry point for the application. It sets up the initial stage and scene and handles the launching of the application.
    * `controller/`: This package follows the **Model-View-Controller (MVC)** design pattern. It contains the controller classes that handle user interaction and logic for each view (FXML file).
        * `LoginController.java`: Manages the logic for the user login window.
        * `SignUpController.java`: Manages the logic for creating a new user account.
        * `CreateCanvasController.java`: Handles the logic for creating a new canvas with custom dimensions.
        * `MainController.java`: The main controller that manages the drawing canvas and all the graphic design tools.
        * `element/`: Contains specialized controllers for managing the properties of different canvas elements.
            * `CanvasImagePropertyController.java`: Handles the properties of image elements.
            * `CanvasShapePropertyController.java`: Manages properties for shapes like rectangles and circles.
            * `CanvasTextPropertyController.java`: Manages the properties of text elements (font, color, size).
    * `model/`: Contains classes that define the data models and interact with the database.
        * `Account.java`: The data model for a user's login account, storing the username and password.
        * `User.java`: The data model for a user's profile, including first name, last name, and profile picture.
        * `AccountDAO.java`: The **Data Access Object** class responsible for all database operations related to user accounts, such as checking for existence and creating new accounts.
    * `service/`: Holds the core logic for the graphic design functionality, such as the `Canvas` class and the different `CanvasElement` types (rectangle, text, circle).
    * `util/`: Includes utility classes for common tasks like database connection (`DBUtil`) and loading FXML files (`JavaFXUtil`).
* `src/main/resources/`: Contains non-Java files required by the application, such as FXML layout files and database files.
* `COSC2391-Assignment-2-S1-2022.pdf`: The official assignment specification document.

---

### Technologies Used

* **Java 18**: The primary programming language.
* **JavaFX**: A software platform for creating and delivering desktop applications with a modern graphical user interface.
* **Maven**: A build automation tool used to manage project dependencies and build process.
* **SQLite**: A lightweight, file-based relational database used for storing user account data.
