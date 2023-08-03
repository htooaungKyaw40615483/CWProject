package com.napier.sem;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PopulationPercentInCountryIntegrationTest {
    private static PopulationPercentInCountry PPIC;
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
            // Create an instance of PopulationPercentInCountry to be tested
            PPIC = new PopulationPercentInCountry();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Test
    void testReturnPopulation() {
        // Fetch all Population in the country
        ArrayList<Population> populations = PopulationPercentInCountry.returnPopulation(con);

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
    void testPrintResultForPopulationPercentInCountry() {
        // Fetch all Population Percent in the country
        ArrayList<Population> populations = PopulationPercentInCountry.returnPopulation(con);

        // Call printResult method and check if it executes without any errors
        try {
            PopulationPercentInCountry.printResult(populations);
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
