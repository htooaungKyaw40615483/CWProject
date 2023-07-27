package com.napier.sem;

// Encapsulation Applied: Used Getters, Setters, and Private variables.
// Class Attributes used only with getMethod (read-only) or setMethod(write-only).

public class Capital {
    private String capitalName;
    // name of capital name

    private String country;
    // name of country

    private int capitalPopulation;
    // total population of capital city

    // Getters for accessing the class attributes only for reading values.

    /**
     * Getting the Capital Name of the Class
     * @return capitalName
     */
    public String getCapitalName() {
        return capitalName;
    }
    /**
     * Getting the Country Name of the Class
     * @return country
     */
    public String getCountry() {
        return country;
    }
    /**
     * Getting the Capital population of the Class
     * @return capitalPopulation
     */
    public int getCapitalPopulation() {
        return capitalPopulation;
    }
    // Setters for accessing the class attributes only for changing/inserting values.
    /**
     * Setting the name of the Class
     * @param capitalName Name of capital  city
     */
    public void setCapitalName(String capitalName) {
        if (capitalName == null){
            System.out.println("The Capital name is null");
            this.capitalName = "-";
        }
        this.capitalName = capitalName;
    }
    /**
     * Setting the name of the Class
     * @param country Name of country
     */
    public void setCountry(String country) {
        if (country == null){
            System.out.println("The country name is null");
            this.country = "-";
        }
        this.country = country;
    }
    /**
     * Setting the name of the Class
     * @param capitalPopulation Population of capital  city
     */
    public void setCapitalPopulation(int capitalPopulation) {
        if (capitalPopulation < 0){
            System.out.println("The population is less than zero. Value is set to zero");
            this.capitalPopulation = 0;
        }
        this.capitalPopulation = capitalPopulation;
    }
}
