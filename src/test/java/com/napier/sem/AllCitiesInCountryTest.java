package com.napier.sem;

import org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.util.ArrayList;

import static org.mockito.Mockito.mock;

public class AllCitiesInCountryTest
{
    static AllCitiesInCountry ACIC;

    // This method is executed once before all the test methods in this class.
    @BeforeAll
    static void setup() {
        // Create an instance of the AllCitiesInCountry class to be used in the test methods.
        Connection con = mock(Connection.class); // Creating a mock Connection object to pass as a parameter for testing purposes.
        ACIC = new AllCitiesInCountry(con);
    }

    @Test
    public void printResultTestNull() {
        // This test method checks the behavior of the printResult method when both parameters are null.
        // It will throw java.lang.NullPointerException if the null is not checked in AllCitiesInCountry.
        ACIC.printResult(null, null);
    }

    @Test
    public void printResultCitiesTestNull() {
        // This test method checks the behavior of the printResult method when the cities ArrayList parameter is null.
        ACIC.printResult("China", null);
    }

    @Test
    public void printResultDnTestNull() {
        // This test method checks the behavior of the printResult method when the country name parameter is null
        // but the cities ArrayList parameter contains a valid City object.
        ArrayList<City> cities = new ArrayList<City>();
        City c = new City();
        cities.add(c);
        ACIC.printResult(null, cities);
    }

    @Test
    public void printResultCityTestNull() {
        // This test method checks the behavior of the printResult method when the country name parameter is not null
        // but the cities ArrayList parameter is null.
        ArrayList<City> cities = new ArrayList<City>();
        City city = new City();
        city.setCityPopulation(99999999);
        cities.add(city);
        ACIC.printResult("China", cities);
    }

    @Test
    public void returnCityTestNull() {
        // This test method checks the behavior of the returnCity method when both parameters are null.
        ACIC.returnCity(null, null);
    }

    @Test
    public void returnCityConTestNull() {
        // This test method checks the behavior of the returnCity method when the country name parameter is not null
        // but the database connection parameter is null.
        ACIC.returnCity("China", null);
    }
}
