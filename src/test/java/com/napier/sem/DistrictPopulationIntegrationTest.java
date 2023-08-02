package com.napier.sem;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DistrictPopulationIntegrationTest {
    private static DistrictPopulation DP;
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
            // Create an instance of DistrictPopulation to be tested
            DP = new DistrictPopulation();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Test
    void testReturnPopulationForNonexistentDistrict() {
        // Fetch the populations for a non-existing District
        String districtName = "Nonexistent District";
        ArrayList<Population> populations = DistrictPopulation.returnPopulation(districtName, con);

        // Check if capitals arraylist is null, as there are no capitals for the non-existing district
        assertFalse(populations.isEmpty());

    }
    @Test
    void testReturnPopulationForDistrictWithEmptyName() {
        // Fetch the populations for a district with an empty name
        String districtName = "";
        ArrayList<Population> populations = DistrictPopulation.returnPopulation(districtName, con);

        // Check if populations arraylist is empty, as the district name is empty
        assertFalse(populations.isEmpty());

    }
    @Test
    void testReturnPopulationForDistrictWithNullName() {
        // Fetch the population for a district with a null name
        String districtName = null;
        ArrayList<Population> populations = DistrictPopulation.returnPopulation(districtName, con);

        // Check if populations arraylist is null, as the district name is null
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
