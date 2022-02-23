package lev.johnydotsville;

import lev.johnydotsville.Entities.Actor;
import lev.johnydotsville.Helpers.DbHelper;

import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class App
{
    public static void main( String[] args )
    {
        Connection connection = null;
        String connectionString = "jdbc:mysql://localhost:3306/sakila";
        String username = "root";
        String pass = "j123";
        try
        {
            connection = DriverManager.getConnection(connectionString, username, pass);
            System.out.println( "Connection established!" );

            printActors(connection, 7);
            int id = insertActor(connection, "Jackie", "Chan");
            updateActor(connection, id, "Jacky", "Chan");
            deleteActor(connection, id);
        }
        catch (SQLException se) {
            se.printStackTrace();
        }

        try {
            connection.close();
        }
        catch (SQLException se) {
            se.printStackTrace();
        }
    }

    static void printActors(Connection connection, int countActorsToPrint) {
        List<Actor> actors = new ArrayList<Actor>();

        try {
            PreparedStatement prep = connection.prepareStatement("select * from actor limit ?");
            prep.setInt(1, countActorsToPrint);
            ResultSet result = prep.executeQuery();

            while (result.next()) {
                Actor actor = new Actor();
                actor.setId(result.getInt("actor_id"));
                actor.setFirstName(result.getString("first_name"));
                actor.setLastName(result.getString("last_name"));

                actors.add(actor);
            }
        }
        catch (SQLException se) {
            System.out.println("Error occurred during gathering actors info");
            se.printStackTrace();
        }

        for (Actor actor : actors) {
            System.out.println(actor);
        }
    }

    static int insertActor(Connection connection, String firstName, String lastName) {
        int maxActorId = DbHelper.getMaxIdFromTable(connection, "actor_id", "actor");
        int newActorId = maxActorId + 1;

        try {
            PreparedStatement prep = connection.prepareStatement("insert into actor(actor_id, first_name, last_name) values (?, ?, ?)");
            prep.setInt(1, newActorId);
            prep.setString(2, firstName);
            prep.setString(3, lastName);

            int countInserted = prep.executeUpdate();
            if (countInserted > 0)
                System.out.println("Actor inserted successfully. Id: " + countInserted);
        }
        catch (SQLException se) {
            System.out.println("Failed to insert actor");
            se.printStackTrace();
        }
        return newActorId;
    }

    static void updateActor(Connection connection, int id, String newFirstName, String newLastName) {
        try {
            PreparedStatement prep = connection.prepareStatement("update actor set first_name=?, last_name=? where actor_id=?");
            prep.setString(1, newFirstName);
            prep.setString(2, newLastName);
            prep.setInt(3, id);
            prep.executeUpdate();

            System.out.println("Actor updated successfully");
        }
        catch (SQLException se) {
            System.out.println("Failed to update actor");
            se.printStackTrace();
        }
    }

    static void deleteActor(Connection connection, int id) {
        try {
            PreparedStatement prep = connection.prepareStatement("delete from actor where actor_id=?");
            prep.setInt(1, id);
            prep.executeUpdate();
            System.out.println("Actor deleted successfully");
        }
        catch (SQLException se) {
            System.out.println("Failed to delete actor");
            se.printStackTrace();
        }
    }
}
