package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AllCitiesInCountryIntegrationTest {

    private static AllCitiesInCountry ACIC;
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
            // Create an instance of AllCitiesInCountry to be tested
            ACIC = new AllCitiesInCountry(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Test
    void testReturnCityForUSA() {
        // Fetch the cities for a predefined country (e.g., "USA")
        String countryName = "United States";
        ArrayList<City> cities = AllCitiesInCountry.returnCity(countryName, con);

        // Check if cities arraylist is not null
        assertNotNull(cities, "The cities ArrayList is null.");

        // Check each row of the cities ArrayList using assertions
        for (City city : cities) {
            // Use the getters to retrieve the values and compare with the expected values
            if (city.getCityName().equals("New York")) {
                assertEquals("United States", city.getCountryName(), "Country name should be 'United States'.");
                assertEquals("New York", city.getDistrictName(), "District name should be 'New York'.");
                assertEquals(8008278, city.getCityPopulation(), "City population should be 8008278.");
            }

        }
    }


    @Test
    void testReturnCityForChina() {
        // Fetch the cities for another predefined country (e.g., "China")
        String countryName = "China";
        ArrayList<City> cities = AllCitiesInCountry.returnCity(countryName, con);

        // Check if cities arraylist is not null
        assertNotNull(cities, "The cities ArrayList is null.");

        // Check each row of the cities ArrayList using assertions
        for (City city : cities) {
            // Use the getters to retrieve the values and compare with the expected values
            if (city.getCityName().equals("Shanghai")) {
                assertEquals("China", city.getCountryName(), "Country name should be 'China'.");
                assertEquals("Shanghai", city.getDistrictName(), "District name should be 'Shanghai'.");
                assertEquals(9696300, city.getCityPopulation(), "City population should be 9696300.");

            }

        }
    }

    @Test
    void testReturnCityForNonexistentCountry() {
        // Fetch the cities for a non-existing country
        String countryName = "Nonexistent Country";
        ArrayList<City> cities = AllCitiesInCountry.returnCity(countryName, con);

        // Check if cities arraylist is null, as there are no cities for the non-existing country
        assertNull(cities, nullMessage);
    }

    @Test
    void testReturnCityForCountryWithEmptyName() {
        // Fetch the cities for a country with an empty name
        String countryName = "";
        ArrayList<City> cities = AllCitiesInCountry.returnCity(countryName, con);

        // Check if cities arraylist is null, as the country name is empty
        assertNull(cities, nullMessage);
    }

    @Test
    void testReturnCityForCountryWithNullName() {
        // Fetch the cities for a country with a null name
        String countryName = null;
        ArrayList<City> cities = AllCitiesInCountry.returnCity(countryName, con);

        // Check if cities arraylist is null, as the country name is null
        assertNull(cities, nullMessage);
    }

    @Test
    void testReturnCityForCountryWithNoCities() {
        // Fetch the cities for a country with no cities (e.g., "Antarctica")
        String countryName = "Antarctica";
        ArrayList<City> cities = AllCitiesInCountry.returnCity(countryName, con);

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
