package com.napier.sem;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class Top10CapitalCitiesInContinentIntegrationTest {
    private static Top10CapitalCitiesInContinent t10ccic;
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
            // Create an instance of Top10CapitalCitiesInContinent to be tested
            t10ccic = new Top10CapitalCitiesInContinent();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Test
    void testReturnCapitalCitiesInContinent() {
        // Fetch the top 10 populated capital in a specific continent (e.g., Europe)
        ArrayList<Capital> capitals = Top10CapitalCitiesInContinent.returnCapital("Europe", con);

        // Check if countries arraylist is not null
        assertNotNull(capitals);

        // Check if the number of countries returned is 10 or less
        assertTrue(capitals.size() <= 10);

        // You can add more assertions to check specific properties of the countries if needed
    }
    @Test
    void testReturnCapitalsInEmptyContinent() {
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

        // Fetch the top 10 populated capitals cities in an empty continent
        ArrayList<Capital> capitals = Top10CapitalCitiesInContinent.returnCapital("EmptyContinent", emptyCon);

        // Check if capitals arraylist is null, as there are no capitals in the empty database
        assertNull(capitals);
    }
    @Test
    void testReturnCapitalsWithNullConnection() {
        // Fetch the top 10 populated countries with a null connection
        ArrayList<Capital> capitals = Top10CapitalCitiesInContinent.returnCapital("Europe", null);

        // Check if countries arraylist is null, as the connection is null
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
