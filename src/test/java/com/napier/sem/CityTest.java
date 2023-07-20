package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CityTest {

    static City city;

    @BeforeAll
    static void init(){
        city = new City();
    }
    @Test
    void setCityNameTestNull() {
        city.setCityName(null);
    }

    @Test
    void setCountryNameTestNull() {
        city.setCountryName(null);
    }

    @Test
    void setDistrictNameTestNull() {
        city.setDistrictName(null);
    }

    @Test
    void setCityPopulationTestNull() {
        city.setCityPopulation(-1);
    }
}