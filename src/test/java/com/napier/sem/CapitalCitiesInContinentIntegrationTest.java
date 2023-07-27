package com.napier.sem;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CapitalCitiesInContinentIntegrationTest {
    private static CapitalCitiesInContinent CCIC;
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
            // Create an instance of CapitalCitiesInContinent to be tested
            CCIC = new CapitalCitiesInContinent();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Test
    void testReturnCapitalForAsia() {
        // Fetch the capitals for the continent "Asia"
        String continentName = "Asia";
        ArrayList<Capital> capitals = CapitalCitiesInContinent.returnCapital(continentName, con);

        // Check if capitals arraylist is not null
        assertNotNull(capitals);

        // Check each row of the capitals ArrayList using assertions
        for (Capital capital : capitals) {
            // Use the getters to retrieve the values and compare with the expected values
            if (capital.getCountry().equals("Mexico")) {
                assertEquals(8591309, capital.getCapitalPopulation());
            }
        }
    }
    @Test
    void testReturnCapitalForNonexistentContinent() {
        // Fetch the cities for a non-existing continent
        String continentName = "Nonexistent Continent";
        ArrayList<Capital> capitals = CapitalCitiesInContinent.returnCapital(continentName, con);

        // Check if capitals arraylist is null, as there are no capitals for the non-existing continent
        assertNull(capitals);
    }

    @Test
    void testReturnCapitalForContinentWithEmptyName() {
        // Fetch the capitals for a continent with an empty name
        String continentName = "";
        ArrayList<Capital> capitals = CapitalCitiesInContinent.returnCapital(continentName, con);

        // Check if capitals arraylist is null, as the continent name is empty
        assertNull(capitals);
    }
    @Test
    void testReturnCapitalForContinentWithNullName() {
        // Fetch the cities for a continent with a null name
        String continentName = "";
        ArrayList<Capital> capitals = CapitalCitiesInContinent.returnCapital(continentName, con);

        // Check if capitals arraylist is null, as the continent name is null
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
