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

    @BeforeAll
    static void init(){
        ACIC = new AllCitiesInCountry();
    }

    // Testing printResults
    @Test
    void printResultTestNull()
    {
        // will throw java.lang.NullPointerException if the null is not checked in AllCitiesInDistrict.
        ACIC.printResult(null, null);
    }

    @Test
    void printResultCitiesTestNull(){
        ACIC.printResult("China", null);
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
        ACIC.printResult("China", cities);
    }

    @Test
    void printResultCityTestEmpty(){
        ArrayList<City> cities = new ArrayList<City>();
        ACIC.printResult("China", cities);
    }

    @Test
    void printResultCityTestContainsNull(){
        ArrayList<City> cities = new ArrayList<City>();
        cities.add(null);
        ACIC.printResult("China", cities);
    }

    @Test
    void returnCityTestNull(){
        ACIC.returnCity(null,null);
    }

    @Test
    void returnCityConTestNull(){
        ACIC.returnCity("China", null);
    }

    @Test
    void returnCityCountryTestNull(){
        Connection con = mock(Connection.class);
        ACIC.returnCity(null, con);
    }
}