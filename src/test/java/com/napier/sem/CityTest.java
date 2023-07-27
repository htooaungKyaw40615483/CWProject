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
//    @Test
//    void setCityNameTestNull() {
//        city.setCityName(null);
//    }

    @Test
    void setCityNameTestNotNull() {
        city.setCityName("Texas");
    }

    @Test
    void setCountryNameTestNull() {
        city.setCountryName(null);
    }

    @Test
    void setCountryNameTestNotNull() {
        city.setCountryName("TestingCountry");
    }

    @Test
    void setDistrictNameTestNull() {
        city.setDistrictName(null);
    }

    @Test
    void setDistrictNameTestNotNull() { city.setDistrictName("TestingDistrict");}

    @Test
    void setCityPopulationTestLessThanZero() {
        city.setCityPopulation(-1);
    }

    @Test
    void setCityPopulationTestMoreThanZero() {
        city.setCityPopulation(1000);
    }
}