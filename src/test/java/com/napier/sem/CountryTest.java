package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CountryTest {

    static Country country;

    @BeforeAll
    static void init() { country = new Country(); }

    @Test
    void setCountryNoTestNull() { country.setCountryNo(null); }

    @Test
    void setCountryNoTestNotNull() { country.setCountryNo("1"); }

    @Test
    void setCountryNameTestNull() { country.setCountryName(null); }

    @Test
    void setCountryNameTestNotNull() { country.setCountryName("TestCountry"); }

    @Test
    void setContinentNameTestNull() { country.setContinentName(null); }

    @Test
    void setContinentNameTestNotNull() { country.setContinentName("TestContinent"); }

    @Test
    void setRegionNameTestNull() { country.setRegionName(null); }

    @Test
    void setRegionNameTestNotNull() { country.setRegionName("TestRegion"); }

    @Test
    void setPopulationTestNull() { country.setPopulation(-1); }

    @Test
    void setPopulationTestNotNull() { country.setPopulation(99); }

    @Test
    void setCapitalNameTestNull() { country.setCapitalName(null); }

    @Test
    void setCapitalNameTestNotNull() { country.setCapitalName("TestRegion"); }

}
