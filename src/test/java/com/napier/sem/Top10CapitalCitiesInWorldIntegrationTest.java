package com.napier.sem;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;

public class Top10CapitalCitiesInWorldIntegrationTest {
    private static Top10CapitalCitiesInWorld t10ciw;
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
            // Create an instance of Top10CapitalCitiesInWorld to be tested
            t10ciw = new Top10CapitalCitiesInWorld();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testReturnCapitalsInWorld() {
        // Fetch the top 10 populated capitals in the world
        ArrayList<Capital> capitals = Top10CapitalCitiesInWorld.returnCapital(con);

        // Check if capitals arraylist is not null
        assertNotNull(capitals);

        // Check if the number of capitals returned is 10 or less
        assertTrue(capitals.size() <= 10);

    }

    @Test
    void testReturnCapitalsInEmptyWorld() {
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

        // Fetch the top 10 populated capitals in an empty world
        ArrayList<Capital> capitals = Top10CapitalCitiesInWorld.returnCapital(emptyCon);

        // Check if capitals arraylist is null, as there are no capitals in the empty database
        assertNull(capitals);
    }

    @Test
    void testReturnCapitalsWithNullConnection() {
        // Fetch the top 10 populated capitals with a null connection
        ArrayList<Capital> capitals = Top10CapitalCitiesInWorld.returnCapital(null);

        // Check if capitals arraylist is null, as the connection is null
        assertNull(capitals);
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
