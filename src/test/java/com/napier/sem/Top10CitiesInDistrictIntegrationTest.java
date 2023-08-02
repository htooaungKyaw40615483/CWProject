package com.napier.sem;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class Top10CitiesInDistrictIntegrationTest {

    private static Top10CitiesInDistrict t10cid;
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
            // Create an instance of Top10CitiesInDistrict to be tested
            t10cid = new Top10CitiesInDistrict();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testReturnCitiesInDistrict() {
        // Fetch the top 10 populated cities in a specific district
        String districtName = "California"; // Replace with an existing district name from the world database
        ArrayList<City> cities = Top10CitiesInDistrict.returnCitiesInDistrict(districtName, con);

        // Check if cities arraylist is not null
        assertNotNull(cities);

        // Check if the number of cities returned is 10 or less
        assertTrue(cities.size() <= 10);

    }

    @Test
    void testReturnCitiesInNonexistentDistrict() {
        // Fetch the top 10 populated cities in a non-existing district
        String districtName = "Nonexistent District";
        ArrayList<City> cities = Top10CitiesInDistrict.returnCitiesInDistrict(districtName, con);

        // Check if cities arraylist is null, as there are no cities for the non-existing district
        assertNull(cities);
    }


    @Test
    void testReturnCitiesInDistrictWithNullName() {
        // Fetch the top 10 populated cities in a district with a null name
        String districtName = null;
        ArrayList<City> cities = Top10CitiesInDistrict.returnCitiesInDistrict(districtName, con);

        // Check if cities arraylist is null, as the district name is null
        assertNull(cities);
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
