package com.napier.sem;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CItyPopulationIntegrationTest {
    private static CityPopulation CP;
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
            // Create an instance of PopulationPercentInContinent to be tested
            CP = new CityPopulation();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Test
    void testReturnPopulationForNonexistentCity() {
        // Fetch the cities for a non-existing city
        String cityName = "Nonexistent City";
        ArrayList<Population> populations = CityPopulation.returnPopulation(cityName, con);

        // Check if capitals arraylist is null, as there are no capitals for the non-existing continent
        assertFalse(populations.isEmpty());

    }
    @Test
    void testReturnPopulationForCityWithEmptyName() {
        // Fetch the populations for a continent with an empty name
        String cityName = "";
        ArrayList<Population> populations = CityPopulation.returnPopulation(cityName, con);

        // Check if populations arraylist is empty, as the city name is empty
        assertFalse(populations.isEmpty());

    }
    @Test
    void testReturnPopulationForCityWithNullName() {
        // Fetch the population for a city with a null name
        String cityName = null;
        ArrayList<Population> populations = CityPopulation.returnPopulation(cityName, con);

        // Check if populations arraylist is null, as the city name is null
        assertFalse(populations.isEmpty());
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
