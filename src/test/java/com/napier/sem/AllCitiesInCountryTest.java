package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;

public class AllCitiesInCountryTest
{
    static AllCitiesInCountry ACIC;

    @BeforeAll
    static void init(){
        ACIC = new AllCitiesInCountry();
    }

    // Testing printResults
    @Test
    void printResultsTestNull()
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
    void ReturnCityTestNull(){
        ACIC.returnCity(null,null);
    }
}