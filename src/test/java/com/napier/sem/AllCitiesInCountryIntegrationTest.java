package com.napier.sem;

import com.napier.sem.AllCitiesInCountry;
import com.napier.sem.City;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll; // Import for @AfterAll annotation
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class AllCitiesInCountryIntegrationTest {

    static AllCitiesInCountry ACIC;
    static Connection con;

    @BeforeAll
    static void init() {
        // Initialize the connection to the real database
        String url = "jdbc:mysql://db:3306/world?useSSL=false";
        String user = "root";
        String password = "example";

        try {
            con = DriverManager.getConnection(url, user, password);
            ACIC = new AllCitiesInCountry(con);
        } catch (SQLException e) {
            System.out.println("Failed to connect to the database");
            e.printStackTrace();
        }
    }

    // Test retrieving cities from the database for a specific country
    @Test
    void testGetCitiesForCountry() {
        String countryName = "China";
        ArrayList<City> cities = ACIC.returnCity(countryName, con);

        assertNotNull(cities);
        assertEquals(4, cities.size()); // Assuming China has 4 cities in the test database, adjust the value accordingly
    }

    // Test handling of null country name
    @Test
    void testNullCountryName() {
        ArrayList<City> cities = ACIC.returnCity(null, con);

        assertNotNull(cities);
        assertEquals(0, cities.size()); // Since country name is null, there should be no cities returned
    }

    // Test handling of null connection object
    @Test
    void testNullConnection() {
        String countryName = "Germany"; // Assume there is a country named Germany in the test database
        ArrayList<City> cities = ACIC.returnCity(countryName, null);

        assertNotNull(cities);
        assertEquals(0, cities.size()); // Since connection is null, there should be no cities returned
    }

    // Add more tests based on other scenarios and edge cases
    // ...

    // Remember to close the connection after all the tests are completed
    @AfterAll
    static void tearDown() {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Failed to close the database connection");
            e.printStackTrace();
        }
    }
}
