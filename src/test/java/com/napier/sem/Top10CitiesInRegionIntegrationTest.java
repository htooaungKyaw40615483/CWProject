package com.napier.sem;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class Top10CitiesInRegionIntegrationTest {

    private static Top10CitiesInRegion t10cir;
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
            // Create an instance of Top10CitiesInRegion to be tested
            t10cir = new Top10CitiesInRegion();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testReturnCitiesInRegion() {
        // Fetch the top 10 populated cities in a specific region
        String regionName = "Central Africa"; // Replace with an existing region name from the world database
        ArrayList<City> cities = Top10CitiesInRegion.returnCity(regionName, con);

        // Check if cities arraylist is not null
        assertNotNull(cities);

        // Check if the number of cities returned is 10 or less
        assertTrue(cities.size() <= 10);

        // You can add more assertions to check specific properties of the cities if needed
    }

    @Test
    void testReturnCitiesInNonexistentRegion() {
        // Fetch the top 10 populated cities in a non-existing region
        String regionName = "Nonexistent Region";
        ArrayList<City> cities = Top10CitiesInRegion.returnCity(regionName, con);

        // Check if cities arraylist is null, as there are no cities for the non-existing region
        assertNull(cities);
    }

    @Test
    void testReturnCitiesInRegionWithEmptyName() {
        // Fetch the top 10 populated cities in a region with an empty name
        String regionName = "";
        ArrayList<City> cities = Top10CitiesInRegion.returnCity(regionName, con);

        // Check if cities arraylist is null, as the region name is empty
        assertNull(cities);
    }

    @Test
    void testReturnCitiesInRegionWithNullName() {
        // Fetch the top 10 populated cities in a region with a null name
        String regionName = null;
        ArrayList<City> cities = Top10CitiesInRegion.returnCity(regionName, con);

        // Check if cities arraylist is null, as the region name is null
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

