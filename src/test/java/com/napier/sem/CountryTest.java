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
    void setCountryNameTestNull() { country.setCountryName(null); }

    @Test
    void setContinentNameTestNull() { country.setContinentName(null); }

    @Test
    void setRegionNameTestNull() { country.setRegionName(null); }

    @Test
    void setPopulationTestNull() { country.setPopulation(-1); }

    @Test
    void setCapitalNameTestNull() { country.setCapitalName(null); }

}
