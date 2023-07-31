package com.napier.sem;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegionPopulationIntegrationTest {
    private static RegionPopulation RP;
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
            // Create an instance of RegionPopulation to be tested
            RP = new RegionPopulation();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Test
    void testReturnPopulationForNonexistentRegion() {
        // Fetch the populations for a non-existing Region
        String regionName = "Nonexistent Region";
        ArrayList<Population> populations = RegionPopulation.returnPopulation(regionName, con);

        // Check if capitals arraylist is null, as there are no populations for the non-existing region
        assertTrue(populations.isEmpty());

    }
    @Test
    void testReturnPopulationForRegionWithEmptyName() {
        // Fetch the populations for a region with an empty name
        String regionName = "";
        ArrayList<Population> populations = RegionPopulation.returnPopulation(regionName, con);

        // Check if populations arraylist is empty, as the region name is empty
        assertTrue(populations.isEmpty());

    }
    @Test
    void testReturnPopulationForRegionWithNullName() {
        // Fetch the population for a region with a null name
        String regionName = null;
        ArrayList<Population> populations = RegionPopulation.returnPopulation(regionName, con);

        // Check if populations arraylist is null, as the district name is null
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
