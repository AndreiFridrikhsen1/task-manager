module personal.assignment1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens personal.assignment1 to javafx.fxml;
    exports personal.assignment1;
}