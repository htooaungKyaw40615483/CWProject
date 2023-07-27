package com.napier.sem;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class Top10CitiesInCountryIntegrationTest {

    private static Top10CitiesInCountry TCC;
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
            // Create an instance of Top10CitiesInCountry to be tested
            TCC = new Top10CitiesInCountry();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testReturnCitiesInCountry() {
        // Fetch the top 10 populated cities for the country "China"
        String countryName = "China";
        ArrayList<City> cities = Top10CitiesInCountry.returnCitiesInCountry(countryName, con);

        // Check if cities ArrayList is not null
        assertNotNull(cities);

        // Check if cities ArrayList has exactly 10 cities
        assertEquals(10, cities.size());

        // Check each city in the ArrayList to have valid attributes
        for (City city : cities) {
            assertNotNull(city.getCityName());
            assertNotNull(city.getCountryName());
            assertNotNull(city.getDistrictName());
            assertNotEquals(0, city.getCityPopulation());
        }
    }

    @Test
    void testReturnCitiesInNonexistentCountry() {
        // Fetch the top 10 populated cities for a non-existing country
        String countryName = "Nonexistent Country";
        ArrayList<City> cities = Top10CitiesInCountry.returnCitiesInCountry(countryName, con);

        // Check if cities ArrayList is empty since there are no cities for the non-existing country
        assertTrue(cities.isEmpty());
    }

    @Test
    void testReturnCitiesInCountryWithEmptyName() {
        // Fetch the top 10 populated cities for a country with an empty name
        String countryName = "";
        ArrayList<City> cities = Top10CitiesInCountry.returnCitiesInCountry(countryName, con);

        // Check if cities ArrayList is empty since the country name is empty
        assertTrue(cities.isEmpty());
    }

//    @Test
//    void testReturnCitiesInCountryWithNullName() {
//        // Fetch the top 10 populated cities for a country with a null name
//        String countryName = null;
//        ArrayList<City> cities = Top10CitiesInCountry.returnCitiesInCountry(countryName, con);
//
//        // Check if cities ArrayList is empty since the country name is null
//        assertTrue(cities.isEmpty());
//    }

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
