package com.napier.sem;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AllCitiesInDistrictIntegrationTest {

    private static AllCitiesInDistrict ACID;
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
            // Create an instance of AllCitiesInDistrict to be tested
            ACID = new AllCitiesInDistrict(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testReturnCityForDistrict() {
        // Fetch the cities for a predefined district (e.g., "New York")
        String districtName = "New York";
        ArrayList<City> cities = AllCitiesInDistrict.returnCity(districtName, con);

        // Check if cities arraylist is not null
        assertNotNull(cities);

        // Check each row of the cities ArrayList using assertions
        for (City city : cities) {
            // Use the getters to retrieve the values and compare with the expected values
            if (city.getCityName().equals("New York")) {
                assertEquals("United States", city.getCountryName());
                assertEquals("New York", city.getDistrictName());
                assertEquals(8008278, city.getCityPopulation());
            }
            // You can add more assertions for other cities if needed
        }
    }

    @Test
    void testReturnCityForDistrictWithEmptyName() {
        // Fetch the cities for a district with an empty name
        String districtName = "";
        ArrayList<City> cities = AllCitiesInDistrict.returnCity(districtName, con);

        // Check if cities arraylist is empty, as the district name is empty
        assertNull(cities);
    }

    @Test
    void testReturnCityForDistrictWithNullName() {
        // Fetch the cities for a district with a null name
        String districtName = null;
        ArrayList<City> cities = AllCitiesInDistrict.returnCity(districtName, con);

        // Check if cities arraylist is empty, as the district name is null
        assertTrue(cities.isEmpty());
    }

    @Test
    void testReturnCityForDistrictWithNoCities() {
        // Fetch the cities for a district with no cities (e.g., "Antarctica")
        String districtName = "Antarctica";
        ArrayList<City> cities = AllCitiesInDistrict.returnCity(districtName, con);

        // Check if cities arraylist is empty, as there are no cities for the district
        assertTrue(cities.isEmpty());
    }

    @Test
    void testPrintResultForDistrict() {
        // Fetch the cities for a predefined district (e.g., "New York")
        String districtName = "New York";
        ArrayList<City> cities = AllCitiesInDistrict.returnCity(districtName, con);

        // Check if cities arraylist is not null
        assertNotNull(cities);

        // Call the printResult method to check if it prints the cities correctly
        AllCitiesInDistrict.printResult(districtName, cities);
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

