module com.furthurprogramming.assignment2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.sql.rowset;
    requires java.desktop;
    requires org.jetbrains.annotations;


    exports com.furthurprogramming.assignment2;

    opens com.furthurprogramming.assignment2.controller to javafx.fxml;
    exports com.furthurprogramming.assignment2.controller;

    opens com.furthurprogramming.assignment2.views to javafx.fxml;

    opens com.furthurprogramming.assignment2.controller.element to javafx.fxml;
    exports com.furthurprogramming.assignment2.controller.element;
}