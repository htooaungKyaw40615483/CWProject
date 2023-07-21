package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AllCitiesInCountryIntegrationTest {

    private static AllCitiesInCountry ACIC;
    private static Connection con;

    @BeforeAll
    public static void setUp() {
        // Set up the database connection
        String dbUrl = "jdbc:mysql://localhost:3307/world?useSSL=false&allowPublicKeyRetrieval=true";
        String username = "root";
        String password = "";

        try {
            con = DriverManager.getConnection(dbUrl, username, password);
            ACIC = new AllCitiesInCountry(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testReturnCityValidCountry() {
        String countryName = "Germany";
        ArrayList<City> cities = ACIC.returnCity(countryName, con);
        assertNotNull(cities, "Cities list should not be null.");
        assertFalse(cities.isEmpty(), "Cities list should not be empty.");
        ACIC.printResult(countryName, cities);
    }

    @Test
    public void testReturnCityInvalidCountry() {
        String invalidCountryName = "InvalidCountry";
        ArrayList<City> citiesInvalidCountry = ACIC.returnCity(invalidCountryName, con);
        assertNull(citiesInvalidCountry, "Cities list should be null for an invalid country.");
        ACIC.printResult(invalidCountryName, citiesInvalidCountry);
    }

    @Test
    public void testReturnCityNullCountry() {
        String nullCountryName = null;
        ArrayList<City> citiesNullCountry = ACIC.returnCity(nullCountryName, con);
        assertNull(citiesNullCountry, "Cities list should be null for a null country.");
        ACIC.printResult(nullCountryName, citiesNullCountry);
    }

    @Test
    public void testReturnCityCountryWithNoCities() {
        String countryWithNoCities = "Antarctica";
        ArrayList<City> citiesNoCities = ACIC.returnCity(countryWithNoCities, con);

        // Since no cities are found, returnCity should return null, and the cities list should be empty
        assertNull(citiesNoCities, "Cities list should be null for a country with no cities.");
    }

    @Test
    public void testReturnCityCaseInsensitive() {
        String countryName = "UnItEd KinGdOm"; // Mixed case country name
        ArrayList<City> citiesCaseInsensitive = ACIC.returnCity(countryName, con);
        assertNotNull(citiesCaseInsensitive, "Cities list should not be null.");
        assertFalse(citiesCaseInsensitive.isEmpty(), "Cities list should not be empty for a valid country.");
        ACIC.printResult(countryName, citiesCaseInsensitive);
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
