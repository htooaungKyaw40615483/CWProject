package com.napier.sem;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PopulationPercentInContinentIntegrationTest {
    private static PopulationPercentInContinent PPIC;

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
            // Create an instance of PopulationPercentInContinent to be tested
            PPIC = new PopulationPercentInContinent();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testReturnPopulation() {
        // Fetch all Population in the continent
        ArrayList<Population> populations = PopulationPercentInContinent.returnPopulation(con);

        // Check if populations arraylist is not null
        assertNotNull(populations);

        // Check each row of the populations ArrayList using assertions
        for (Population population : populations) {
            // Use the getters to retrieve the values and perform basic checks
            assertNotNull(population.getName());
            assertNotNull(population.getTotalPopulation() >= 0);
            assertNotNull(population.getYesCityPercent());
            assertNotNull(population.getNoCityPercent());
        }
    }

    @Test
    void testPrintResultForPopulationPercentInContinent() {
        // Fetch all Population Percent in the continent
        ArrayList<Population> populations = PopulationPercentInContinent.returnPopulation(con);

        // Call printResult method and check if it executes without any errors
        try {
            PopulationPercentInContinent.printResult(populations);
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
