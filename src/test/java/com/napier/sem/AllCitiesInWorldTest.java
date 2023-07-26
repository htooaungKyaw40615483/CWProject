package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.util.ArrayList;
import static org.mockito.Mockito.mock;

public class AllCitiesInWorldTest {
    static AllCitiesInWorld ACIW;

    @BeforeAll
    static void init() { ACIW = new AllCitiesInWorld();}

    //Testing printResults
    @Test
    void printResultTestNull()
    {
        // will throw java.lang.NullPointerException if the null is not checked in AllCitiesInWorld.
        ACIW.printResult(null);
    }

    @Test
    void printResultCitiesTestNull() { ACIW.printResult( null);}

    @Test
    void printResultCityTestNull(){
        ArrayList<City> cities = new ArrayList<City>();
        City city = new City();
        city.setCityPopulation(99999999);
        cities.add(city);
        ACIW.printResult(cities);
    }

    @Test
    void printResultCityTestEmpty(){
        ArrayList<City> cities = new ArrayList<City>();
        ACIW.printResult(cities);
    }

    @Test
    void printResultCityTestContainsNull(){
        ArrayList<City> cities = new ArrayList<City>();
        cities.add(null);
        ACIW.printResult(cities);
    }

    @Test
    void returnCityTestNull() { ACIW.returnCity(null);}

    @Test
    void returnCityCountryTestNull(){
        Connection con = mock(Connection.class);
        ACIW.returnCity(con);
    }
}
