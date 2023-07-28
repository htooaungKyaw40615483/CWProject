package com.napier.sem;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CapitalCitiesInWorldIntegrationTest {
    private static CapitalCitiesInWorld CCIW;
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
            // Create an instance of AllCitiesInWorld to be tested
            CCIW = new CapitalCitiesInWorld();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetAllCapitals() {
        // Fetch all capitals from the database
        ArrayList<Capital> capitals = CapitalCitiesInWorld.returnCapital(con);

        // Check if capitals arraylist is not null
        assertNotNull(capitals);

        // Check each row of the capitals ArrayList using assertions
        for (Capital capital : capitals) {
            // Use the getters to retrieve the values and ensure they are not null or empty
            assertNotNull(capital.getCapitalName());
            assertNotNull(capital.getCountry());
            assertTrue(capital.getCapitalPopulation() >= 0); // Ensure population is non-negative
        }
    }
    @Test
    void testPrintResult() {
        // Fetch all cities from the database
        ArrayList<Capital> capitals = CapitalCitiesInWorld.returnCapital(con);

        // Test printing the results
        CapitalCitiesInWorld.printResult(capitals);
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
