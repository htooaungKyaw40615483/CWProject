package com.napier.sem;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CapitalCitiesInRegionIntegrationTest {
    private static CapitalCitiesInRegion CCIR;
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
            // Create an instance of CapitalCitiesInRegion to be tested
            CCIR = new CapitalCitiesInRegion();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Test
    void testReturnCapitalForCaribbean() {
        // Fetch the capitals for the continent "Asia"
        String regionName = "Caribbean";
        ArrayList<Capital> capitals = CapitalCitiesInRegion.returnCapital(regionName, con);

        // Check if capitals arraylist is not null
        assertNotNull(capitals);

        // Check each row of the capitals ArrayList using assertions
        for (Capital capital : capitals) {
            // Use the getters to retrieve the values and compare with the expected values
            if (capital.getCountry().equals("Bahamas")) {
                assertEquals(172000, capital.getCapitalPopulation());
            }
        }
    }
    @Test
    void testReturnCapitalForNonexistentRegion() {
        // Fetch the cities for a non-existing continent
        String regionName = "Nonexistent Region";
        ArrayList<Capital> capitals = CapitalCitiesInRegion.returnCapital(regionName, con);

        // Check if capitals arraylist is null, as there are no capitals for the non-existing continent
        assertNull(capitals);
    }

    @Test
    void testReturnCapitalForRegionWithEmptyName() {
        // Fetch the capitals for a region with an empty name
        String regionName = "";
        ArrayList<Capital> capitals = CapitalCitiesInRegion.returnCapital(regionName, con);

        // Check if capitals arraylist is null, as the continent name is empty
        assertNull(capitals);
    }
    @Test
    void testReturnCapitalForRegionWithNullName() {
        // Fetch the capitals for a region with a null name
        String regionName = "";
        ArrayList<Capital> capitals = CapitalCitiesInRegion.returnCapital(regionName, con);

        // Check if capitals arraylist is null, as the region name is null
        assertNull(capitals);
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
