package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.util.ArrayList;
import static org.mockito.Mockito.mock;

public class AllCitiesInRegionTest {
    static AllCitiesInRegion ACIR;

    @BeforeAll
    static void init(){
        ACIR = new AllCitiesInRegion();
    }

    // Testing printResults
    @Test
    void printResultTestNull()
    {
        // will throw java.lang.NullPointerException if the null is not checked in AllCitiesInRegion.
        ACIR.printResult(null, null);
    }

    @Test
    void printResultCitiesTestNull(){
        ACIR.printResult("Caribbean", null);
    }

    @Test
    void printResultDnTestNull(){
        ArrayList<City> cities = new ArrayList<City>();
        City c = new City();
        cities.add(c);
        ACIR.printResult(null, cities);
    }

    @Test
    void printResultCityTestNull(){
        ArrayList<City> cities = new ArrayList<City>();
        City city = new City();
        city.setCityPopulation(99999999);
        cities.add(city);
        ACIR.printResult("Caribbean", cities);
    }

    @Test
    void printResultCityTestEmpty(){
        ArrayList<City> cities = new ArrayList<City>();
        ACIR.printResult("Caribbean", cities);
    }

    @Test
    void printResultCityTestContainsNull(){
        ArrayList<City> cities = new ArrayList<City>();
        cities.add(null);
        ACIR.printResult("Caribbean", cities);
    }

    // Testing returnCity
    @Test
    void returnCityTestNull(){
        ACIR.returnCity(null,null);
    }

    @Test
    void returnCityConTestNull(){
        ACIR.returnCity("Caribbean", null);
    }

    @Test
    void returnCityCountryTestNull(){
        Connection con = mock(Connection.class);
        ACIR.returnCity(null, con);
    }
}