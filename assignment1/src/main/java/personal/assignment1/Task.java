package personal.assignment1;
import java.time.LocalTime;
import java.time.Duration;
public class Task {
    private String taskName;
    private int minutes;
    private LocalTime timeStart;
    private LocalTime timeEnd;

    private Boolean completed;
    public Task(String taskName){
        timeStart = LocalTime.now();
        this.taskName = taskName;
        validate(this.taskName);
    };
    public void validate(String taskName){
        if (taskName.trim().isEmpty()){
            throw new IllegalArgumentException("dont leave the name empty");
        }

    }
    public Task(){}

    public void setName (String name){
        this.taskName = name;
    }
    public String getName(){
        return taskName;
    }

    public LocalTime getTimeStart(){

            return timeStart;
    }
    //  the difference between timestart and timeend in minutes
    public long difference(){
        timeEnd = LocalTime.now();
        Duration diff = Duration.between(timeStart, timeEnd);
        return diff.toMinutes();
    }


}
