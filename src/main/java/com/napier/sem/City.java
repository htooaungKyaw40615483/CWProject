package com.napier.sem;

// Encapsulation Applied: Used Getters, Setters, and Private variables.
// Class Attributes used only with getMethod (read-only) or setMethod(write-only).
public class City {
    /*** Cityname */
    private String city_name;

    /*** Country of the city */
    private String country_name;

    /*** District */
    private String district_name;

    /** * City's population */
    private int city_population;

    // Getters for accessing the class attributes only for reading values.

    public String getCityName(){
        return city_name;
    }

    public String getCountryName(){
        return country_name;
    }

    public String getDistrictName(){
        return district_name;
    }

    public int getCityPopulation(){
        return city_population;
    }

    // Setters for accessing the class attributes only for changing/inserting values.

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public void setDistrict_name(String district_name) {
        this.district_name = district_name;
    }

    public void setCity_population(int city_population) {
        this.city_population = city_population;
    }
}
