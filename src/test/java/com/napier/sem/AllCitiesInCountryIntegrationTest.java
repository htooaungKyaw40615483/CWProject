package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

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
        assertNotNull(cities);
        assertFalse(cities.isEmpty());
        ACIC.printResult(countryName, cities);
    }

    @Test
    public void testReturnCityInvalidCountry() {
        String invalidCountryName = "InvalidCountry";
        ArrayList<City> citiesInvalidCountry = ACIC.returnCity(invalidCountryName, con);
        assertNull(citiesInvalidCountry);
        ACIC.printResult(invalidCountryName, citiesInvalidCountry);
    }

    @Test
    public void testReturnCityNullCountry() {
        String nullCountryName = null;
        ArrayList<City> citiesNullCountry = ACIC.returnCity(nullCountryName, con);
        assertNull(citiesNullCountry);
        ACIC.printResult(nullCountryName, citiesNullCountry);
    }

}
