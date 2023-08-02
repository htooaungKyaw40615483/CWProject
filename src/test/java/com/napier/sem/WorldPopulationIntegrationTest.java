package com.napier.sem;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class WorldPopulationIntegrationTest {
    private static WorldPopulation WP;
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
            // Create an instance of WorldPopulation to be tested
            WP = new WorldPopulation();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Test
    void testReturnWorldPopulation() {
        // Fetch all populations in the world
        ArrayList<Population> populations = WorldPopulation.returnPopulation(con);

        // Check if populations arraylist is not null
        assertNotNull(populations);

        // Check each row of the populations ArrayList using assertions
        for (Population population : populations) {
            // Use the getters to retrieve the values and perform basic checks
            assertTrue(population.getTotalPopulation() >= 0);
        }
    }

    @Test
    void testPrintResultForPopulationInWorld() {
        // Fetch all populations in the world
        ArrayList<Population> populations = WorldPopulation.returnPopulation(con);

        // Call printResult method and check if it executes without any errors
        try {
            WorldPopulation.printResult(populations);
        } catch (Exception e) {
            fail("printResult method encountered an exception: " + e.getMessage());
        }
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
