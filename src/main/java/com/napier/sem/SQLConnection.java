package com.napier.sem;
import java.sql.*;

public class SQLConnection {
    // Creating Connection Variable
    private Connection con = null;
    public Connection getCon(){
        return con; // Getter method to return the database connection
    }
    public void connect(String location, int delay) {
        try {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver"); // Load the MySQL database driver
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1); // Exit the program with an error code if the driver could not be loaded
        }
        int retries = 10;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                // Wait a bit for the database to start
                Thread.sleep(delay);
                // Connect to the database
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false", "root", "example");
                // The connection string specifies the database URL, username, and password for the connection
                System.out.println("Successfully connected");
                break; // If the connection is successful, break out of the retry loop
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage()); // Print the error message if the connection fails
            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }
    public void disconnect()
    {
        if (con != null)
        {
            try
            {
                // Close connection
                con.close(); // Close the database connection
            }
            catch (Exception e)
            {
                System.out.println("Error closing connection to database");
            }
        }
    }
}
