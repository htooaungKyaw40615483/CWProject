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
        assertNotNull(cities);

        // Check each row of the cities ArrayList using assertions
        for (City city : cities) {
            // Use the getters to retrieve the values and compare with the expected values
            if (city.getCityName().equals("New York")) {
                assertEquals("United States", city.getCountryName());
                assertEquals("New York", city.getDistrictName());
                assertEquals(8008278, city.getCityPopulation());
            }
            // You can add more assertions for other cities if needed
        }
    }

    // Add test methods for other countries or scenarios here

    @Test
    void testReturnCityForChina() {
        // Fetch the cities for another predefined country (e.g., "China")
        String countryName = "China";
        ArrayList<City> cities = AllCitiesInCountry.returnCity(countryName, con);

        // Check if cities arraylist is not null
        assertNotNull(cities);

        // Check each row of the cities ArrayList using assertions
        for (City city : cities) {
            // Use the getters to retrieve the values and compare with the expected values
            if (city.getCityName().equals("Shanghai")) {
                assertEquals("China", city.getCountryName());
                assertEquals("Shanghai", city.getDistrictName());
                assertEquals(9696300, city.getCityPopulation());
            }
            // You can add more assertions for other cities if needed
        }
    }

    @Test
    void testReturnCityForNonexistentCountry() {
        // Fetch the cities for a non-existing country
        String countryName = "Nonexistent Country";
        ArrayList<City> cities = AllCitiesInCountry.returnCity(countryName, con);

        // Check if cities arraylist is null, as there are no cities for the non-existing country
        assertNull(cities);
    }

    @Test
    void testReturnCityForCountryWithEmptyName() {
        // Fetch the cities for a country with an empty name
        String countryName = "";
        ArrayList<City> cities = AllCitiesInCountry.returnCity(countryName, con);

        // Check if cities arraylist is null, as the country name is empty
        assertNull(cities);
    }

    @Test
    void testReturnCityForCountryWithNullName() {
        // Fetch the cities for a country with a null name
        String countryName = null;
        ArrayList<City> cities = AllCitiesInCountry.returnCity(countryName, con);

        // Check if cities arraylist is null, as the country name is null
        assertNull(cities);
    }

    @Test
    void testReturnCityForCountryWithNoCities() {
        // Fetch the cities for a country with no cities (e.g., "Antarctica")
        String countryName = "Antarctica";
        ArrayList<City> cities = AllCitiesInCountry.returnCity(countryName, con);

        // Check if cities arraylist is null, as there are no cities for Antarctica
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
