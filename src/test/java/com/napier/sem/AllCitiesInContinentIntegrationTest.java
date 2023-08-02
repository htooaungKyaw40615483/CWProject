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
        assertNotNull(cities);

        // Check each row of the cities ArrayList using assertions
        for (City city : cities) {
            // Use the getters to retrieve the values and compare with the expected values
            if (city.getCityName().equals("Shanghai")) {
                assertEquals("China", city.getCountryName());
                assertEquals("Shanghai", city.getDistrictName());
                assertEquals(9696300, city.getCityPopulation());
            }

        }
    }

    @Test
    void testReturnCityForEurope() {
        // Fetch the cities for the continent "Europe"
        String continentName = "Europe";
        ArrayList<City> cities = AllCitiesInContinent.returnCity(continentName, con);

        // Check if cities arraylist is not null
        assertNotNull(cities);

        // Check each row of the cities ArrayList using assertions
        for (City city : cities) {
            // Use the getters to retrieve the values and compare with the expected values
            if (city.getCityName().equals("London")) {
                assertEquals("United Kingdom", city.getCountryName());
                assertEquals("England", city.getDistrictName());
                assertEquals(7285000, city.getCityPopulation());
            }

        }
    }

    @Test
    void testReturnCityForNonexistentContinent() {
        // Fetch the cities for a non-existing continent
        String continentName = "Nonexistent Continent";
        ArrayList<City> cities = AllCitiesInContinent.returnCity(continentName, con);

        // Check if cities arraylist is null, as there are no cities for the non-existing continent
        assertNull(cities);
    }

    @Test
    void testReturnCityForContinentWithEmptyName() {
        // Fetch the cities for a continent with an empty name
        String continentName = "";
        ArrayList<City> cities = AllCitiesInContinent.returnCity(continentName, con);

        // Check if cities arraylist is null, as the continent name is empty
        assertNull(cities);
    }

    @Test
    void testReturnCityForContinentWithNullName() {
        // Fetch the cities for a continent with a null name
        String continentName = null;
        ArrayList<City> cities = AllCitiesInContinent.returnCity(continentName, con);

        // Check if cities arraylist is null, as the continent name is null
        assertNull(cities);
    }

    @Test
    void testReturnCityForContinentWithNoCities() {
        // Fetch the cities for a continent with no cities (e.g., "Antarctica")
        String continentName = "Antarctica";
        ArrayList<City> cities = AllCitiesInContinent.returnCity(continentName, con);

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
