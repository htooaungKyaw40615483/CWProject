package com.napier.sem;

// Encapsulation Applied: Used Getters, Setters, and Private variables.
// Class Attributes used only with getMethod (read-only) or setMethod(write-only).
public class City {
    // City Name
    private String city_name;
    // Country Name
    private String country_name;
    // District Name
    private String district_name;
    // City Population
    private int city_population;


    // Getters for accessing the class attributes only for reading values.

    /**
     * Getting the City Name of the class
     * @return City Name
     */
    public String getCityName(){
        return city_name;
    }

    /**
     * Getting the Country Name of the class
     * @return Country Name
     */
    public String getCountryName(){
        return country_name;
    }

    /**
     * Getting the District Name of the class
     * @return District Name
     */
    public String getDistrictName(){
        return district_name;
    }

    /**
     * Getting the City Population of the class
     * @return City Population
     */
    public int getCityPopulation(){
        return city_population;
    }


    // Setters for accessing the class attributes only for changing/inserting values.

    /**
     * Setting the City Name of the Class
     * @param city_name City Name
     */
    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }
    /**
     * Setting the Country Name of the Class
     * @param country_name Country Name
     */
    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }
    /**
     * Setting the District Name of the Class
     * @param district_name District Name
     */
    public void setDistrict_name(String district_name) {
        this.district_name = district_name;
    }
    /**
     * Setting the City Population of the Class
     * @param city_population City Population
     */
    public void setCity_population(int city_population) {
        this.city_population = city_population;
    }
}
