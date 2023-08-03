package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AllCountriesInWorldIntegrationTest {

    private static AllCountriesInWorld ACIW;
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
            // Create an instance of AllCountriesInWorld to be tested
            ACIW = new AllCountriesInWorld();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testReturnCountriesInWorld() {
        // Fetch all countries in the world
        ArrayList<Country> countries = AllCountriesInWorld.returnCountries(con);

        // Check if countries arraylist is not null
        assertNotNull(countries);

        // Check each row of the countries ArrayList using assertions
        for (Country country : countries) {
            // Use the getters to retrieve the values and perform basic checks
            assertNotNull(country.getCountryNo());
            assertNotNull(country.getCountryName());
            assertNotNull(country.getContinentName());
            assertNotNull(country.getRegionName());
            assertTrue(country.getPopulation() >= 0);
            assertNotNull(country.getCapitalName());
        }
    }

    @Test
    void testPrintResultForCountriesInWorld() {
        // Fetch all countries in the world
        ArrayList<Country> countries = AllCountriesInWorld.returnCountries(con);

        // Call printResult method and check if it executes without any errors
        try {
            AllCountriesInWorld.printResult(countries);
        } catch (Exception e) {
            fail("printResult method encountered an exception: " + e.getMessage());
        }
    }

    @Test
    void testReturnCountriesInRegionWithNoData() {
        // Fetch countries in a region with no data
        String regionName = "Nonexistent Region";
        ArrayList<Country> countries = AllCountriesInRegion.returnCountries(regionName, con);

        // Check if countries arraylist is null, as there are no countries in the nonexistent region
        assertNull(countries);
    }

    @Test
    void testReturnCountriesInRegionWithEmptyName() {
        // Fetch countries in a region with an empty name
        String regionName = "";
        ArrayList<Country> countries = AllCountriesInRegion.returnCountries(regionName, con);

        // Check if countries arraylist is null, as the region name is empty
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
