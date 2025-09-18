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

### File Structure

This section explains the purpose of the key directories and files in the project.
