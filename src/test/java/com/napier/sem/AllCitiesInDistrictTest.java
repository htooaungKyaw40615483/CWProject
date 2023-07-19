package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;

public class AllCitiesInDistrictTest
{
    static AllCitiesInDistrict ACID;

    @BeforeAll
    static void init(){
        ACID = new AllCitiesInDistrict();
    }

    // Testing printResults
    @Test
    void printResultTestNull()
    {
        // will throw java.lang.NullPointerException if the null is not checked in AllCitiesInDistrict.
        ACID.printResult(null, null);
    }

    @Test
    void printResultCitiesTestNull(){
        ACID.printResult("Bíobío", null);
    }

    @Test
    void printResultDnTestNull(){
        ArrayList<City> cities = new ArrayList<City>();
        City c = new City();
        cities.add(c);
        ACID.printResult(null, cities);
    }

    @Test
    void printResultCityTestNull(){
        ArrayList<City> cities = new ArrayList<City>();
        City city = new City();
        city.setCityPopulation(99999999);
        cities.add(city);
        ACID.printResult("Bíobío", cities);
    }

    @Test
    void returnCityTestNull(){
        ACID.returnCity(null,null);
    }
}