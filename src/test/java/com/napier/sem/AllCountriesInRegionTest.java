package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.util.ArrayList;
import static org.mockito.Mockito.mock;

public class AllCountriesInRegionTest {

    static AllCountriesInRegion ACIR;

    @BeforeAll
    static void init() {
        ACIR = new AllCountriesInRegion();
    }

    //Testing printResults
    @Test
    void printResultTestNull()
    {
        // will throw java.lang.NullPointerException if the null is not checked in AllCountriesInRegion.
        ACIR.printResult(null,null);
    }

    @Test
    void printResultCountriesTestNull() {
        ACIR.printResult("Caribbean", null);
    }

    @Test
    void printResultRnTestNull(){
        ArrayList<Country> countries = new ArrayList<Country>();
        Country c = new Country();
        countries.add(c);
        ACIR.printResult(null,countries);
    }

    @Test
    void printResultCountryTestNull(){
        ArrayList<Country> countries = new ArrayList<Country>();
        Country country = new Country();
        country.setPopulation(99999999);
        countries.add(country);
        ACIR.printResult("Caribbean", countries);

    }

    @Test
    void returnCountryTestNull() {
        ACIR.returnCountries(null,null);
    }

    @Test
    void printResultCityTestEmpty(){
        ArrayList<Country> countries = new ArrayList<Country>();
        ACIR.printResult("North America", countries);
    }

    @Test
    void printResultCityTestContainsNull(){
        ArrayList<Country> countries = new ArrayList<Country>();
        countries.add(null);
        ACIR.printResult("North America", countries);
    }

    // Testing returnCity
    @Test
    void returnCountriesTestNull(){
        ACIR.returnCountries(null,null);
    }

    @Test
    void returnCountryConTestNull(){
        ACIR.returnCountries("North America", null);
    }

    @Test
    void returnCountryRegionTestNull(){
        Connection con = mock(Connection.class);
        ACIR.returnCountries(null, con);
    }
}
