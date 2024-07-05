package personal.assignment1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TasksController {


    @FXML
    private Text copyright;
    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Label welcomeText;

    @FXML
    private ImageView screen;
    @FXML
    private Label taskManagerLaber;
    private Task task;
    @FXML
    private BarChart <Number, String> barChart;
    @FXML
    private ToggleButton toggle;
    @FXML
    private Label display;

    @FXML
    private TextField input;

    @FXML
    private ToggleButton toggleScene2;
    // when sumbit button is pressed create a new task obj with a timestamp
    @FXML
    void submit(ActionEvent event) {
        try{
            task = new Task(input.getText());
            display.setText(task.getName() + " " +  task.getTimeStart());
        }catch(IllegalArgumentException e){
            display.setText(e.getMessage());
        }


    }
    //when task is completed add it to the database with an end time stamp
    @FXML
    void complete(ActionEvent event) {
        //returns time in seconds
       try {
           int time = (int) task.difference();
           String name = task.getName();
           DBUtility.addTask(name, time);
           display.setText("Task was added to the chart");

       }catch (NullPointerException e){
           display.setText("Enter the task name");
       }


    }

    @FXML
    //scene 2
    void toggleScene2(ActionEvent event) {
        if (toggleScene2.isSelected()){
            try {

                FXMLLoader fxmlLoader = new FXMLLoader(TasksApplication.class.getResource("task-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = (Stage) toggleScene2.getScene().getWindow();
                scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

                stage.setTitle("Task Manager");
                stage.setScene(scene);
                stage.show();
            }catch (IOException e){
                System.out.println(e);
            }

        }
    }

    public void initialize(){
        if(barChart != null) {

            // populate bar chart from the hashmap thats populated in  dbutility class
            XYChart.Series<Number, String> data = new XYChart.Series<>();
            HashMap<String, Integer> map = DBUtility.populate();
            for (Map.Entry<String, Integer> m : map.entrySet()) {
                data.getData().add(new XYChart.Data<>( m.getValue(), m.getKey()));
             //   System.out.println(m.getKey() + " " + m.getValue());
            }
            barChart.getData().add(data);

            // resize barChart according to number of data
            barChart.setMinHeight(map.size() * 60);



        }else {
            if(display.getText().length() < 1) {
                screen.setImage(new Image(getClass().getResource("task.png").toString()));
            }
        }
    }

    //change scenes
    //scene1
    @FXML
    protected void onToggle(){
        if (toggle.isSelected()){
            try {

                FXMLLoader fxmlLoader = new FXMLLoader(TasksApplication.class.getResource("Scene2.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = (Stage) toggle.getScene().getWindow();
                scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
                stage.setTitle("Task Manager");
                stage.setScene(scene);
                stage.show();
            }catch (IOException e){
                System.out.println(e);


            }

        }
    }

}