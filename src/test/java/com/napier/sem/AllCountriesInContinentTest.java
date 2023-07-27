package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.util.ArrayList;
import static org.mockito.Mockito.mock;

public class AllCountriesInContinentTest {

    static AllCountriesInContinent ACIC;

    @BeforeAll
    static void init() {
        ACIC = new AllCountriesInContinent();
    }

    //Testing printResults
    @Test
    void printResultTestNull()
    {
        // will throw java.lang.NullPointerException if the null is not checked in AllCountriesInContinent.
        ACIC.printResult(null,null);
    }

    @Test
    void printResultCountriesTestNull() {
        ACIC.printResult("North America", null);
    }

    @Test
    void printResultCnTestNull(){
        ArrayList<Country> countries = new ArrayList<Country>();
        Country c = new Country();
        countries.add(c);
        ACIC.printResult(null,countries);
    }

    @Test
    void printResultCountryTestNull(){
        ArrayList<Country> countries = new ArrayList<Country>();
        Country country = new Country();
        country.setPopulation(99999999);
        countries.add(country);
        ACIC.printResult("North America", countries);
    }

    @Test
    void printResultCityTestEmpty(){
        ArrayList<Country> countries = new ArrayList<Country>();
        ACIC.printResult("North America", countries);
    }

    @Test
    void printResultCityTestContainsNull(){
        ArrayList<Country> countries = new ArrayList<Country>();
        countries.add(null);
        ACIC.printResult("North America", countries);
    }

    // Testing returnCity
    @Test
    void returnCityTestNull(){
        ACIC.returnCountries(null,null);
    }

    @Test
    void returnCountryConTestNull(){
        ACIC.returnCountries("North America", null);
    }

    @Test
    void returnCountryTestNull(){
        Connection con = mock(Connection.class);
        ACIC.returnCountries(null, con);
    }
}