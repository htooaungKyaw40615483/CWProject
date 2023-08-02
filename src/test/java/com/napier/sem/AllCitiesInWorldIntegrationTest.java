package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AllCitiesInWorldIntegrationTest {

    private static AllCitiesInWorld ACIW;
    private static Connection con;

    // This method is executed once before all the test methods in this class.
    @BeforeAll
    public static void setUp() {
        // Set up the database connection
        String dbUrl = "jdbc:mysql://localhost:33060/world?useSSL=false&allowPublicKeyRetrieval=true";
        String username = "root";
        String password = "example";

        try {
            // Establish a connection to the database
            con = DriverManager.getConnection(dbUrl, username, password);
            // Create an instance of AllCitiesInWorld to be tested
            ACIW = new AllCitiesInWorld();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // This test method tests the getAllCities() method of AllCitiesInWorld class.
    @Test
    void testGetAllCities() {
        // Fetch all cities from the database
        ArrayList<City> cities = AllCitiesInWorld.returnCity(con);

        // Check if cities arraylist is not null
        assertNotNull(cities);

        // Check each row of the cities ArrayList using assertions
        for (City city : cities) {
            // Use the getters to retrieve the values and ensure they are not null or empty
            assertNotNull(city.getCityName());
            assertNotNull(city.getCountryName());
            assertNotNull(city.getDistrictName());
            assertTrue(city.getCityPopulation() >= 0); // Ensure population is non-negative
        }
    }

    // This test method tests the printResult() method of AllCitiesInWorld class.
    @Test
    void testPrintResult() {
        // Fetch all cities from the database
        ArrayList<City> cities = AllCitiesInWorld.returnCity(con);

        // Test printing the results
        AllCitiesInWorld.printResult(cities);
    }

    // This method is executed once after all the test methods in this class.
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
