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
    static Connection mockConnection;

    @BeforeAll
    static void init(){
        mockConnection = mock(Connection.class);
        ACIC = new AllCitiesInCountry(mockConnection);
    }

    // Testing printResults
    @Test
    public void printResultTestNull()
    {
        // will throw java.lang.NullPointerException if the null is not checked in AllCitiesInDistrict.
        ACIC.printResult(null, null);
    }

    @Test
    public void printResultCitiesTestNull(){
        ACIC.printResult("China", null);
    }

    @Test
    public void printResultDnTestNull(){
        ArrayList<City> cities = new ArrayList<City>();
        City c = new City();
        cities.add(c);
        ACIC.printResult(null, cities);
    }

    @Test
    public void printResultCityTestNull(){
        ArrayList<City> cities = new ArrayList<City>();
        City city = new City();
        city.setCityPopulation(99999999);
        cities.add(city);
        ACIC.printResult("China", cities);
    }

    @Test
    public void returnCityTestNull(){
        ACIC.returnCity(null,null);
    }

    @Test
    public void returnCityConTestNull(){
        ACIC.returnCity("China", null);
    }

    @Test
    public void returnCityCountryTestNull(){
        Connection con = mock(Connection.class);
        ACIC.returnCity(null, con);
    }
}