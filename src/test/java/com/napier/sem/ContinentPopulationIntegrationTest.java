package com.napier.sem;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ContinentPopulationIntegrationTest {
    private static ContinentPopulation CP;
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
            // Create an instance of ContinentPopulation to be tested
            CP = new ContinentPopulation();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Test
    void testReturnPopulationForNonexistentContinent() {
        // Fetch the cities for a non-existing continent
        String continentName = "Nonexistent Continent";
        ArrayList<Population> populations = ContinentPopulation.returnPopulation(continentName, con);

        // Check if capitals arraylist is null, as there are no capitals for the non-existing continent
        assertTrue(populations.isEmpty());

    }
    @Test
    void testReturnPopulationForContinentWithEmptyName() {
        // Fetch the populations for a continent with an empty name
        String continentName = "";
        ArrayList<Population> populations = ContinentPopulation.returnPopulation(continentName, con);

        // Check if populations arraylist is empty, as the city name is empty
        assertTrue(populations.isEmpty());

    }
    @Test
    void testReturnPopulationForContinentWithNullName() {
        // Fetch the population for a continent with a null name
        String continentName = null;
        ArrayList<Population> populations = ContinentPopulation.returnPopulation(continentName, con);

        // Check if populations arraylist is null, as the city name is null
        assertTrue(populations.isEmpty());
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
