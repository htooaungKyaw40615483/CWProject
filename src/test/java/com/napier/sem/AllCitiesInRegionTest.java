package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.util.ArrayList;
import static org.mockito.Mockito.mock;

public class AllCitiesInRegionTest {
    static AllCitiesInRegion ACIR;

    // This method is executed once before all the test methods in this class.
    @BeforeAll
    static void setup() {
        // Create an instance of the AllCitiesInRegion class to be used in the test methods.
        Connection con = mock(Connection.class); // Creating a mock Connection object to pass as a parameter for testing purposes.
        ACIR = new AllCitiesInRegion(con);
    }

    // Testing printResults
    @Test
    void printResultTestNull() {
        // This test method checks the behavior of the printResult method when both parameters are null.
        // It will throw java.lang.NullPointerException if the null is not checked in AllCitiesInRegion.
        ACIR.printResult(null, null);
    }

    @Test
    void printResultCitiesTestNull() {
        // This test method checks the behavior of the printResult method when the cities ArrayList parameter is null.
        ACIR.printResult("Caribbean", null);
    }

    @Test
    void printResultDnTestNull() {
        // This test method checks the behavior of the printResult method when the region name parameter is null
        // but the cities ArrayList parameter contains a valid City object.
        ArrayList<City> cities = new ArrayList<City>();
        City c = new City();
        cities.add(c);
        ACIR.printResult(null, cities);
    }

    @Test
    void printResultCityTestNull() {
        // This test method checks the behavior of the printResult method when the region name parameter is not null
        // but the cities ArrayList parameter is null.
        ArrayList<City> cities = new ArrayList<City>();
        City city = new City();
        city.setCityPopulation(99999999);
        cities.add(city);
        ACIR.printResult("Caribbean", cities);
    }

    @Test
    void printResultCityTestEmpty() {
        // This test method checks the behavior of the printResult method when the region name parameter is not null
        // and the cities ArrayList parameter is empty.
        ArrayList<City> cities = new ArrayList<City>();
        ACIR.printResult("Caribbean", cities);
    }

    @Test
    void printResultCityTestContainsNull() {
        // This test method checks the behavior of the printResult method when the cities ArrayList parameter contains null values.
        ArrayList<City> cities = new ArrayList<City>();
        cities.add(null);
        ACIR.printResult("Caribbean", cities);
    }

    // Testing returnCity
    @Test
    void returnCityTestNull() {
        // This test method checks the behavior of the returnCity method when both parameters are null.
        ACIR.returnCity(null, null);
    }

    @Test
    void returnCityConTestNull() {
        // This test method checks the behavior of the returnCity method when the region name parameter is not null
        // but the database connection parameter is null.
        ACIR.returnCity("Caribbean", null);
    }

    @Test
    void returnCityCountryTestNull() {
        // This test method checks the behavior of the returnCity method when the region name parameter is null
        // but the database connection parameter is not null (mocked Connection object).
        Connection con = mock(Connection.class);
        ACIR.returnCity(null, con);
    }
}
