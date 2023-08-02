package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CountryTest {

    static Country country;

    @BeforeAll
    static void init() {
        country = new Country();
    }

    // Testing the setter for Country Number with a null value
    @Test
    void setCountryNoTestNull() {
        country.setCountryNo(null);
    }

    // Testing the setter for Country Number with a non-null value
    @Test
    void setCountryNoTestNotNull() {
        country.setCountryNo("1");
    }

    // Testing the setter for Country Name with a null value
    @Test
    void setCountryNameTestNull() {
        country.setCountryName(null);
    }

    // Testing the setter for Country Name with a non-null value
    @Test
    void setCountryNameTestNotNull() {
        country.setCountryName("TestCountry");
    }

    // Testing the setter for Continent Name with a null value
    @Test
    void setContinentNameTestNull() {
        country.setContinentName(null);
    }

    // Testing the setter for Continent Name with a non-null value
    @Test
    void setContinentNameTestNotNull() {
        country.setContinentName("TestContinent");
    }

    // Testing the setter for Region Name with a null value
    @Test
    void setRegionNameTestNull() {
        country.setRegionName(null);
    }

    // Testing the setter for Region Name with a non-null value
    @Test
    void setRegionNameTestNotNull() {
        country.setRegionName("TestRegion");
    }

    // Testing the setter for Population with a null value
    @Test
    void setPopulationTestNull() {
        country.setPopulation(-1);
    }

    // Testing the setter for Population with a non-null value
    @Test
    void setPopulationTestNotNull() {
        country.setPopulation(99);
    }

    // Testing the setter for Capital Name with a null value
    @Test
    void setCapitalNameTestNull() {
        country.setCapitalName(null);
    }

    // Testing the setter for Capital Name with a non-null value
    @Test
    void setCapitalNameTestNotNull() {
        country.setCapitalName("TestRegion");
    }
}
