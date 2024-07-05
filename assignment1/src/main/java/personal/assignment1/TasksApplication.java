package personal.assignment1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class TasksApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TasksApplication.class.getResource("task-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        stage.setTitle("Task Manager");
        stage.setScene(scene);
        stage.show();
        stage.getIcons().add(new Image(getClass().getResource("task.png").toString()));
    }

    public static void main(String[] args) {
        launch();
    }
}