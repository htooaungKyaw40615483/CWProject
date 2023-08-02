package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AllCountriesInRegionIntegrationTest {

    private static AllCountriesInRegion ACIR;
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
            // Create an instance of AllCountriesInRegion to be tested
            ACIR = new AllCountriesInRegion();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testReturnCountriesForNorthAmerica() {
        // Fetch the countries for the region "North America"
        String regionName = "North America";
        ArrayList<Country> countries = AllCountriesInRegion.returnCountries(regionName, con);

        // Check if countries arraylist is not null
        assertNotNull(countries);

        // Check each row of the countries ArrayList using assertions
        for (Country country : countries) {
            // Use the getters to retrieve the values and compare with the expected values
            if (country.getCountryName().equals("United States")) {
                assertEquals("North America", country.getRegionName());
                assertEquals(278357000, country.getPopulation());
                assertEquals("Washington", country.getCapitalName());
            }

        }
    }

    @Test
    void testReturnCountriesForNonexistentRegion() {
        // Fetch the countries for a non-existing region
        String regionName = "Nonexistent Region";
        ArrayList<Country> countries = AllCountriesInRegion.returnCountries(regionName, con);

        // Check if countries arraylist is null, as there are no countries for the non-existing region
        assertNull(countries);
    }

    @Test
    void testReturnCountriesForRegionWithEmptyName() {
        // Fetch the countries for a region with an empty name
        String regionName = "";
        ArrayList<Country> countries = AllCountriesInRegion.returnCountries(regionName, con);

        // Check if countries arraylist is null, as the region name is empty
        assertNull(countries);
    }

    @Test
    void testReturnCountriesForRegionWithNullName() {
        // Fetch the countries for a region with a null name
        String regionName = null;
        ArrayList<Country> countries = AllCountriesInRegion.returnCountries(regionName, con);

        // Check if countries arraylist is null, as the region name is null
        assertNull(countries);
    }

    @Test
    void testReturnCountriesForRegionWithNoCountries() {
        // Fetch the countries for a region with no countries
        String regionName = "Antarctica";
        ArrayList<Country> countries = AllCountriesInRegion.returnCountries(regionName, con);

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

