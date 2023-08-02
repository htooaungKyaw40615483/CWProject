package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.util.ArrayList;
import static org.mockito.Mockito.mock;

public class AllCitiesInWorldTest {
    static AllCitiesInWorld ACIW;

    // This method is executed once before all the test methods in this class.
    @BeforeAll
    static void init() {
        ACIW = new AllCitiesInWorld();
    }

    // Testing printResults
    @Test
    void printResultTestNull() {
        // This test method checks the behavior of the printResult method when the cities ArrayList parameter is null.
        // It will throw java.lang.NullPointerException if the null is not checked in AllCitiesInWorld.
        ACIW.printResult(null);
    }

    @Test
    void printResultCityTestNull() {
        // This test method checks the behavior of the printResult method when the cities ArrayList parameter contains
        // a valid City object with a non-null population.
        ArrayList<City> cities = new ArrayList<City>();
        City city = new City();
        city.setCityPopulation(99999999);
        cities.add(city);
        ACIW.printResult(cities);
    }

    @Test
    void printResultCityTestEmpty() {
        // This test method checks the behavior of the printResult method when the cities ArrayList parameter is empty.
        ArrayList<City> cities = new ArrayList<City>();
        ACIW.printResult(cities);
    }

    @Test
    void printResultCityTestContainsNull() {
        // This test method checks the behavior of the printResult method when the cities ArrayList parameter contains null values.
        ArrayList<City> cities = new ArrayList<City>();
        cities.add(null);
        ACIW.printResult(cities);
    }

    // Testing returnCity
    @Test
    void returnCityTestNull() {
        // This test method checks the behavior of the returnCity method when the connection parameter is null.
        ACIW.returnCity(null);
    }

    @Test
    void returnCityCountryTestNull() {
        // This test method checks the behavior of the returnCity method when the database connection parameter is not null (mocked Connection object).
        Connection con = mock(Connection.class);
        ACIW.returnCity(con);
    }
}
