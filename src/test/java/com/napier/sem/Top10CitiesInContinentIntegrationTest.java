package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class Top10CitiesInContinentIntegrationTest {

    private static Top10CitiesInContinent top10Cities;
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
            // Create an instance of Top10CitiesInContinent to be tested
            top10Cities = new Top10CitiesInContinent();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testReturnTop10CitiesInContinent() {
        // Fetch top 10 cities in a specific continent (e.g., "Asia")
        String continentName = "Asia";
        ArrayList<City> cities = Top10CitiesInContinent.returnCity(continentName, con);

        // Check if cities arraylist is not null
        assertNotNull(cities);

        // Check if there are exactly 10 cities in the list
        assertEquals(10, cities.size());

        // Check each row of the cities ArrayList using assertions
        for (City city : cities) {
            // Use the getters to retrieve the values and perform basic checks
            assertNotNull(city.getCityName());
            assertNotNull(city.getCountryName());
            assertNotNull(city.getDistrictName());
            assertTrue(city.getCityPopulation() >= 0);
        }
    }

    @Test
    void testPrintResultForTop10CitiesInContinent() {
        // Fetch top 10 cities in a specific continent (e.g., "Europe")
        String continentName = "Europe";
        ArrayList<City> cities = Top10CitiesInContinent.returnCity(continentName, con);

        // Call printResult method and check if it executes without any errors
        try {
            Top10CitiesInContinent.printResult(continentName, cities);
        } catch (Exception e) {
            fail("printResult method encountered an exception: " + e.getMessage());
        }
    }

    @Test
    void testReturnTop10CitiesInNonexistentContinent() {
        // Fetch top 10 cities in a nonexistent continent
        String continentName = "Nonexistent Continent";
        ArrayList<City> cities = Top10CitiesInContinent.returnCity(continentName, con);

        // Check if cities arraylist is null, as there are no cities in the nonexistent continent
        assertNull(cities);
    }

    @Test
    void testReturnTop10CitiesInContinentWithEmptyName() {
        // Fetch top 10 cities in a continent with an empty name
        String continentName = "";
        ArrayList<City> cities = Top10CitiesInContinent.returnCity(continentName, con);

        // Check if cities arraylist is null, as the continent name is empty
        assertNull(cities);
    }

    @Test
    void testReturnTop10CitiesInContinentWithNullName() {
        // Fetch top 10 cities in a continent with a null name
        String continentName = null;
        ArrayList<City> cities = Top10CitiesInContinent.returnCity(continentName, con);

        // Check if cities arraylist is null, as the continent name is null
        assertNull(cities);
    }

    @Test
    void testPrintResultForTop10CitiesInContinentWithEmptyName() {
        // Fetch top 10 cities in a continent with an empty name
        String continentName = "";
        ArrayList<City> cities = Top10CitiesInContinent.returnCity(continentName, con);

        // Call printResult method and check if it executes without any errors
        try {
            Top10CitiesInContinent.printResult(continentName, cities);
        } catch (Exception e) {
            fail("printResult method encountered an exception: " + e.getMessage());
        }
    }

    @Test
    void testPrintResultForTop10CitiesInContinentWithNullName() {
        // Fetch top 10 cities in a continent with a null name
        String continentName = null;
        ArrayList<City> cities = Top10CitiesInContinent.returnCity(continentName, con);

        // Call printResult method and check if it executes without any errors
        try {
            Top10CitiesInContinent.printResult(continentName, cities);
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

