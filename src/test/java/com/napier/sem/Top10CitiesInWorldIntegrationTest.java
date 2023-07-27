package com.napier.sem;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class Top10CitiesInWorldIntegrationTest {

    private static Top10CitiesInWorld t10ciw;
    private static Connection con;

    @BeforeAll
    public static void setUp() {
        // Set up the database connection
        String dbUrl = "jdbc:mysql://localhost:33060/world?useSSL=false&allowPublicKeyRetrieval=true";
        String username = "root";
        String password = "example";

        try {
            // Establish a connection to the database
            con = DriverManager.getConnection(dbUrl, username, password);
            // Create an instance of Top10CitiesInWorld to be tested
            t10ciw = new Top10CitiesInWorld();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testReturnCitiesInWorld() {
        // Fetch the top 10 populated cities in the world
        ArrayList<City> cities = Top10CitiesInWorld.returnCity(con);

        // Check if cities arraylist is not null
        assertNotNull(cities);

        // Check if the number of cities returned is 10 or less
        assertTrue(cities.size() <= 10);

        // You can add more assertions to check specific properties of the cities if needed
    }

    @Test
    void testReturnCitiesInEmptyWorld() {
        // Create a new connection to an empty database
        Connection emptyCon = null;
        try {
            String dbUrl = "jdbc:mysql://localhost:33060/empty_world?useSSL=false&allowPublicKeyRetrieval=true";
            String username = "root";
            String password = "example";
            emptyCon = DriverManager.getConnection(dbUrl, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Fetch the top 10 populated cities in an empty world database
        ArrayList<City> cities = Top10CitiesInWorld.returnCity(emptyCon);

        // Check if cities arraylist is null, as there are no cities in the empty database
        assertNull(cities);
    }

    @Test
    void testReturnCitiesInWorldWithNullConnection() {
        // Fetch the top 10 populated cities in the world with a null connection
        ArrayList<City> cities = Top10CitiesInWorld.returnCity(null);

        // Check if cities arraylist is null, as the connection is null
        assertNull(cities);
    }

    @AfterAll
    public static void tearDown() {
        // Close the database connection after all tests
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
