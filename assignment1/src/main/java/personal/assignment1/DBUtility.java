package personal.assignment1;
import java.sql.*;
import java.util.*;

public class DBUtility {

    public static HashMap populate() { // populates hashmap with all the db rows
        HashMap<String, Integer> map = new LinkedHashMap<String, Integer>();// stores time and tasks as key value pairs
        List<String> keys = new ArrayList<>();
        List<Integer> values = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://database-1.cj8wke2sulvr.us-east-2.rds.amazonaws.com:3306/comp1011", "admin", "mymysqldb");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from tasks order by id asc");

            while (resultSet.next()) {

                map.put(resultSet.getString("task_name"), resultSet.getInt("time_spent"));

                System.out.println(resultSet.getString("task_name"));
                System.out.println(resultSet.getInt("time_spent"));



            }


        } catch (Exception e) {
            System.out.println(e);
        }
        return map;
    }
    public static void addTask(String task, int minutes){
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://database-1.cj8wke2sulvr.us-east-2.rds.amazonaws.com:3306/comp1011", "admin", "mymysqldb");
            PreparedStatement stmnt = connection.prepareStatement("INSERT INTO tasks (task_name, time_spent) VALUES (?, ?)");
            stmnt.setString(1, task);
            stmnt.setInt(2, minutes );
            stmnt.execute();
        }catch (Exception e){
            System.out.println(e);
        }
    }
}



    /*public static void main(String[] args){
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://172.31.22.43:3306/Andrei200558923", "Andrei200558923", "UftJIlr-al");
            PreparedStatement stmnt = connection.prepareStatement("INSERT INTO tasks (task_name, time_spent) VALUES (?, ?)");
            stmnt.setString(1, "task 1");
            stmnt.setInt(2, 60 );
            stmnt.execute();

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from tasks");


            while (resultSet.next()){

                System.out.println(resultSet.getInt("id"));
                System.out.println(resultSet.getString("task_name"));
                System.out.println(resultSet.getInt("time_spent"));

            }

        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}*/
