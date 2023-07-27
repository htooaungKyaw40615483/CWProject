package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.util.ArrayList;
import static org.mockito.Mockito.mock;

public class AllCountriesInWorldTest {

    static AllCountriesInWorld ACIW;

    @BeforeAll
    static void init() {
        ACIW = new AllCountriesInWorld();
    }

    //Testing printResults
    @Test
    void printResultTestNull()
    {
        // will throw java.lang.NullPointerException if the null is not checked in AllCountriesInWorld.
        ACIW.printResult(null);
    }

    @Test
    void printResultCountryTestNull(){
        ArrayList<Country> countries = new ArrayList<Country>();
        Country country = new Country();
        country.setPopulation(99999999);
        countries.add(country);
        ACIW.printResult(countries);
    }

    @Test
    void printResultCountryTestEmpty(){
        ArrayList<Country> countries = new ArrayList<Country>();
        ACIW.printResult(countries);
    }

    @Test
    void printResultCountryTestContainsNull(){
        ArrayList<Country> countries = new ArrayList<Country>();
        countries.add(null);
        ACIW.printResult(countries);
    }
    @Test
    void returnCountryTestNull() { ACIW.returnCountries(null);}

    @Test
    void returnCityCountryTestNull(){
        Connection con = mock(Connection.class);
        ACIW.returnCountries(con);
    }
}
