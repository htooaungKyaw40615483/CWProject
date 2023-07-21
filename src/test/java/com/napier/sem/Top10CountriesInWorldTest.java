package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class Top10CountriesInWorldTest {
    static Top10CountriesInWorld TCW;

    @BeforeAll
    static void init() { TCW = new Top10CountriesInWorld();}

    // Testing printResults
    @Test
    void printResultTestNull()
    {
        // will throw java.lang.NullPointerException if the null is not checked in AllCountriesInWorld.
        TCW.printResult(null);
    }
    // Testing returnCountry
    @Test
    void returnCountryTestNull(){
        TCW.returnCountries(null);
    }
}

