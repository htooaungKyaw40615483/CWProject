package com.napier.sem;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AllCitiesInRegionIntegrationTest {

    private static AllCitiesInRegion ACIR;
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
            // Create an instance of AllCitiesInRegion to be tested
            ACIR = new AllCitiesInRegion(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testReturnCityForNonexistentRegion() {
        // Fetch the cities for a non-existing region
        String regionName = "Nonexistent Region";
        ArrayList<City> cities = AllCitiesInRegion.returnCity(regionName, con);

        // Check if cities arraylist is empty, as there are no cities for the non-existing region
        assertTrue(cities.isEmpty());
    }

    @Test
    void testReturnCityForRegionWithEmptyName() {
        // Fetch the cities for a region with an empty name
        String regionName = "";
        ArrayList<City> cities = AllCitiesInRegion.returnCity(regionName, con);

        // Check if cities arraylist is empty, as the region name is empty
        assertTrue(cities.isEmpty());
    }

    @Test
    void testReturnCityForRegionWithNullName() {
        // Fetch the cities for a region with a null name
        String regionName = null;
        ArrayList<City> cities = AllCitiesInRegion.returnCity(regionName, con);

        // Check if cities arraylist is empty, as the region name is null
        assertTrue(cities.isEmpty());
    }

    @Test
    void testReturnCityForRegionWithNoCities() {
        // Fetch the cities for a region with no cities (e.g., "Antarctica")
        String regionName = "Antarctica";
        ArrayList<City> cities = AllCitiesInRegion.returnCity(regionName, con);

        // Check if cities arraylist is empty, as there are no cities for the region
        assertTrue(cities.isEmpty());
    }

    @Test
    void testPrintResultForRegion() {
        // Fetch the cities for a predefined region (e.g., "Eastern Asia")
        String regionName = "Eastern Asia";
        ArrayList<City> cities = AllCitiesInRegion.returnCity(regionName, con);

        // Check if cities arraylist is not null
        assertNotNull(cities);

        // Call the printResult method to check if it prints the cities correctly
        AllCitiesInRegion.printResult(regionName, cities);
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
