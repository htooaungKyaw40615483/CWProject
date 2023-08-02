package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.util.ArrayList;
import static org.mockito.Mockito.mock;

public class AllCountriesInContinentTest {

    static AllCountriesInContinent ACIC;

    // This method is executed once before all the test methods in this class.
    @BeforeAll
    static void init() {
        ACIC = new AllCountriesInContinent();
    }

    // Testing printResults
    @Test
    void printResultTestNull() {
        // This test method checks the behavior of the printResult method when the continent name and countries ArrayList parameters are null.
        // It will throw java.lang.NullPointerException if the null is not checked in AllCountriesInContinent.
        ACIC.printResult(null, null);
    }

    @Test
    void printResultCountriesTestNull() {
        // This test method checks the behavior of the printResult method when the countries ArrayList parameter is null.
        ACIC.printResult("North America", null);
    }

    @Test
    void printResultCnTestNull() {
        // This test method checks the behavior of the printResult method when the countries ArrayList parameter contains
        // a valid Country object.
        ArrayList<Country> countries = new ArrayList<Country>();
        Country c = new Country();
        countries.add(c);
        ACIC.printResult(null, countries);
    }

    @Test
    void printResultCountryTestNull() {
        // This test method checks the behavior of the printResult method when the countries ArrayList parameter contains a
        // valid Country object with a non-null population.
        ArrayList<Country> countries = new ArrayList<Country>();
        Country country = new Country();
        country.setPopulation(99999999);
        countries.add(country);
        ACIC.printResult("North America", countries);
    }

    @Test
    void printResultCityTestEmpty() {
        // This test method checks the behavior of the printResult method when the countries ArrayList parameter is empty.
        ArrayList<Country> countries = new ArrayList<Country>();
        ACIC.printResult("North America", countries);
    }

    @Test
    void printResultCityTestContainsNull() {
        // This test method checks the behavior of the printResult method when the countries ArrayList parameter contains null values.
        ArrayList<Country> countries = new ArrayList<Country>();
        countries.add(null);
        ACIC.printResult("North America", countries);
    }

    // Testing returnCity
    @Test
    void returnCityTestNull() {
        // This test method checks the behavior of the returnCountries method when both the continent name and the connection parameters are null.
        ACIC.returnCountries(null, null);
    }

    @Test
    void returnCountryConTestNull() {
        // This test method checks the behavior of the returnCountries method when the connection parameter is null.
        ACIC.returnCountries("North America", null);
    }

    @Test
    void returnCountryTestNull() {
        // This test method checks the behavior of the returnCountries method when the continent name parameter is not null (mocked Connection object).
        Connection con = mock(Connection.class);
        ACIC.returnCountries(null, con);
    }
}
