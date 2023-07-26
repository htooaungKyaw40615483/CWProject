package com.napier.sem;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class Top10CountriesInWorldIntegrationTest {

    private static Top10CountriesInWorld t10ciw;
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
            // Create an instance of Top10CountriesInWorld to be tested
            t10ciw = new Top10CountriesInWorld();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testReturnCountriesInWorld() {
        // Fetch the top 10 populated countries in the world
        ArrayList<Country> countries = Top10CountriesInWorld.returnCountries(con);

        // Check if countries arraylist is not null
        assertNotNull(countries);

        // Check if the number of countries returned is 10 or less
        assertTrue(countries.size() <= 10);

    }

    @Test
    void testReturnCountriesInEmptyWorld() {
        // Create a new connection to an empty database
        Connection emptyCon = null;
        try {
            String dbUrl = "jdbc:mysql://localhost:33060/empty_world?useSSL=false&allowPublicKeyRetrieval=true";
            String username = "root";
            String password = "example";
            emptyCon = DriverManager.getConnection(dbUrl, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Fetch the top 10 populated countries in an empty world
        ArrayList<Country> countries = Top10CountriesInWorld.returnCountries(emptyCon);

        // Check if countries arraylist is null, as there are no countries in the empty database
        assertNull(countries);
    }

    @Test
    void testReturnCountriesWithNullConnection() {
        // Fetch the top 10 populated countries with a null connection
        ArrayList<Country> countries = Top10CountriesInWorld.returnCountries(null);

        // Check if countries arraylist is null, as the connection is null
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
