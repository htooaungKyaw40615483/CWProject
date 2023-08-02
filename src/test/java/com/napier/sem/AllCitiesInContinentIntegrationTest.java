package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AllCitiesInContinentIntegrationTest {

    private static AllCitiesInContinent ACIC;
    private static Connection con;

    String nullMessage = "The 'cities' ArrayList should be null.";
    @BeforeAll
    public static void setUp() {
        // Set up the database connection
        String dbUrl = "jdbc:mysql://localhost:33060/world?useSSL=false&allowPublicKeyRetrieval=true";
        String username = "root";
        String password = "example";

        try {
            // Establish a connection to the database
            con = DriverManager.getConnection(dbUrl, username, password);
            // Create an instance of AllCitiesInContinent to be tested
            ACIC = new AllCitiesInContinent(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testReturnCityForAsia() {
        // Fetch the cities for the continent "Asia"
        String continentName = "Asia";
        ArrayList<City> cities = AllCitiesInContinent.returnCity(continentName, con);

        // Check if cities arraylist is not null
        assertNotNull(cities, "The cities ArrayList is null.");

        // Check each row of the cities ArrayList using assertions
        for (City city : cities) {
            // Use the getters to retrieve the values and compare with the expected values
            if (city.getCityName().equals("Shanghai")) {
                assertEquals("China", city.getCountryName(), "Expected country name is 'China'");
                assertEquals("Shanghai", city.getDistrictName(), "Expected district name is 'Shanghai'");
                assertEquals(9696300, city.getCityPopulation(), "Expected city population is 9696300");
            }

        }
    }

    @Test
    void testReturnCityForEurope() {
        // Fetch the cities for the continent "Europe"
        String continentName = "Europe";
        ArrayList<City> cities = AllCitiesInContinent.returnCity(continentName, con);

        // Check if cities arraylist is not null
        assertNotNull(cities, "The city arraylist is null.");

        // Check each row of the cities ArrayList using assertions
        for (City city : cities) {
            // Use the getters to retrieve the values and compare with the expected values
            if (city.getCityName().equals("London")) {
                assertEquals("United Kingdom", city.getCountryName(), "Expected country name is 'United Kingdom'");
                assertEquals("England", city.getDistrictName(), "Expected district name is 'England'");
                assertEquals(7285000, city.getCityPopulation(), "Expected city population is 7285000");
            }

        }
    }

    @Test
    void testReturnCityForNonexistentContinent() {
        // Fetch the cities for a non-existing continent
        String continentName = "Nonexistent Continent";
        ArrayList<City> cities = AllCitiesInContinent.returnCity(continentName, con);

        // Check if cities arraylist is null, as there are no cities for the non-existing continent
        assertNull(cities, nullMessage);
    }

    @Test
    void testReturnCityForContinentWithEmptyName() {
        // Fetch the cities for a continent with an empty name
        String continentName = "";
        ArrayList<City> cities = AllCitiesInContinent.returnCity(continentName, con);

        // Check if cities arraylist is null, as the continent name is empty
        assertNull(cities, nullMessage);
    }

    @Test
    void testReturnCityForContinentWithNullName() {
        // Fetch the cities for a continent with a null name
        String continentName = null;
        ArrayList<City> cities = AllCitiesInContinent.returnCity(continentName, con);

        // Check if cities arraylist is null, as the continent name is null
        assertNull(cities, nullMessage);
    }

    @Test
    void testReturnCityForContinentWithNoCities() {
        // Fetch the cities for a continent with no cities (e.g., "Antarctica")
        String continentName = "Antarctica";
        ArrayList<City> cities = AllCitiesInContinent.returnCity(continentName, con);

        // Check if cities arraylist is null, as there are no cities for Antarctica
        assertNull(cities, nullMessage);
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
