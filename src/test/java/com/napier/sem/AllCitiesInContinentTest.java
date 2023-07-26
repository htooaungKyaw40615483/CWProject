package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.util.ArrayList;
import static org.mockito.Mockito.mock;

public class AllCitiesInContinentTest {
    static AllCitiesInContinent ACIC;

//    @BeforeAll
//    static void init(){
//        ACIC = new AllCitiesInContinent();
//    }

    // Testing printResults
    @Test
    void printResultTestNull()
    {
        // will throw java.lang.NullPointerException if the null is not checked in AllCitiesInContinent.
        ACIC.printResult(null, null);
    }

    @Test
    void printResultCitiesTestNull(){
        ACIC.printResult("North America", null);
    }

    @Test
    void printResultDnTestNull(){
        ArrayList<City> cities = new ArrayList<City>();
        City c = new City();
        cities.add(c);
        ACIC.printResult(null, cities);
    }

    @Test
    void printResultCityTestNull(){
        ArrayList<City> cities = new ArrayList<City>();
        City city = new City();
        city.setCityPopulation(99999999);
        cities.add(city);
        ACIC.printResult("North America", cities);
    }

    @Test
    void printResultCityTestEmpty(){
        ArrayList<City> cities = new ArrayList<City>();
        ACIC.printResult("North America", cities);
    }

    @Test
    void printResultCityTestContainsNull(){
        ArrayList<City> cities = new ArrayList<City>();
        cities.add(null);
        ACIC.printResult("North America", cities);
    }

    @Test
    void returnCityTestNull(){
        ACIC.returnCity(null,null);
    }

    @Test
    void returnCityConTestNull(){
        ACIC.returnCity("North America", null);
    }

    @Test
    void returnCityCountryTestNull(){
        Connection con = mock(Connection.class);
        ACIC.returnCity(null, con);
    }
}