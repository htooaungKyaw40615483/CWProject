package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AllCountriesInContinentIntegrationTest {

    private static AllCountriesInContinent ACIC;
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
            // Create an instance of AllCountriesInContinent to be tested
            ACIC = new AllCountriesInContinent();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testReturnCountriesForAsia() {
        // Fetch the countries for the continent "Asia"
        String continentName = "Asia";
        ArrayList<Country> countries = AllCountriesInContinent.returnCountries(continentName, con);

        // Check if countries arraylist is not null
        assertNotNull(countries);

        // Check each row of the countries ArrayList using assertions
        for (Country country : countries) {
            // Use the getters to retrieve the values and compare with the expected values
            if (country.getCountryName().equals("China")) {
                assertEquals("Asia", country.getContinentName());
                assertEquals("Eastern Asia", country.getRegionName());
                assertEquals(1277558000, country.getPopulation());
                assertEquals("Peking", country.getCapitalName());
            }
            // You can add more assertions for other countries in Asia if needed
        }
    }

    @Test
    void testReturnCountriesForEurope() {
        // Fetch the countries for the continent "Europe"
        String continentName = "Europe";
        ArrayList<Country> countries = AllCountriesInContinent.returnCountries(continentName, con);

        // Check if countries arraylist is not null
        assertNotNull(countries);

        // Check each row of the countries ArrayList using assertions
        for (Country country : countries) {
            // Use the getters to retrieve the values and compare with the expected values
            if (country.getCountryName().equals("Germany")) {
                assertEquals("Europe", country.getContinentName());
                assertEquals("Western Europe", country.getRegionName());
                assertEquals(82164700, country.getPopulation());
                assertEquals("Berlin", country.getCapitalName());
            }
            // You can add more assertions for other countries in Europe if needed
        }
    }

    @Test
    void testReturnCountriesForNonexistentContinent() {
        // Fetch the countries for a non-existing continent
        String continentName = "Nonexistent Continent";
        ArrayList<Country> countries = AllCountriesInContinent.returnCountries(continentName, con);

        // Check if countries arraylist is null, as there are no countries for the non-existing continent
        assertNull(countries);
    }

    @Test
    void testReturnCountriesForContinentWithEmptyName() {
        // Fetch the countries for a continent with an empty name
        String continentName = "";
        ArrayList<Country> countries = AllCountriesInContinent.returnCountries(continentName, con);

        // Check if countries arraylist is null, as the continent name is empty
        assertNull(countries);
    }

    @Test
    void testReturnCountriesForContinentWithNullName() {
        // Fetch the countries for a continent with a null name
        String continentName = null;
        ArrayList<Country> countries = AllCountriesInContinent.returnCountries(continentName, con);

        // Check if countries arraylist is null, as the continent name is null
        assertNull(countries);
    }

    @Test
    void testReturnCountriesForContinentWithNoCountries() {
        // Fetch the countries for a continent with no countries (e.g., "Antarctica")
        String continentName = "Antarctica";
        ArrayList<Country> countries = AllCountriesInContinent.returnCountries(continentName, con);

        // Check if countries arraylist is null, as there are no countries in Antarctica
        assertNull(countries);
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

