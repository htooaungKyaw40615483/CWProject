package com.napier.sem;

// Encapsulation Applied: Used Getters, Setters, and Private variables.
// Class Attributes used only with getMethod (read-only) or setMethod(write-only).
public class Population {

    private String name;
    // name of continent or region or country

    private long totalPopulation;
    // total populaton of continent or region or country

    private String yesCityPercent;
    // people living in city

    private String noCityPercent;
    // people not living in city

    // Getters for accessing the class attributes only for reading values.


    /**
     * Getting the Name of the Class
     * @return Name
     */
    public String getName(){ return name; }

    /**
     * Getting the Total population of the Class
     * @return Totalpopulation
     */

    public long getTotalPopulation(){ return totalPopulation; }

    /**
     * Getting the people living in cities of the Class
     * @return yes city
     */

    public String getYesCityPercent(){ return yesCityPercent; }

    /**
     * Getting the people not living in cities of the Class
     * @return no city
     */

    public String getNoCityPercent(){ return noCityPercent;}

    // Setters for accessing the class attributes only for changing/inserting values.

    /**
     * Setting the name of the Class
     * @param name Name of continent, region, country
     */
    public void setName(String name) {
        if (name == null){
            System.out.println("The name is null");
            this.name = "-";
        }
        else {
            this.name = name;
        }
    }

    /**
     * Setting the total population of the Class
     * @param  totalPopulation Total population of continent, region, country
     */
    public void setTotalPopulation(long totalPopulation) {
        if (totalPopulation < 0){
            System.out.println("The population is less than zero. Value is set to zero");
            this.totalPopulation = 0;
        }
        else {
            this.totalPopulation = totalPopulation;
        }
    }

    /**
     * Setting the yes city percent of the Class
     * @param  yesCityPercent people living in population of continent, region, country
     */

    public void setYesCityPercent(String yesCityPercent) {
        if (yesCityPercent == null){
            System.out.println("The Percent is less than 0. Value is set to zero");
            this.yesCityPercent = "-";
        }
        else {
            this.yesCityPercent = yesCityPercent;
        }
    }

    /**
     * Setting the no city percent of the Class
     * @param  noCityPercent people living in population of continent, region, country
     */
    public void setNoCityPercent(String noCityPercent){
        if (noCityPercent == null){
            System.out.println("The Percent is less than 0. Value is set to zero");
            this.noCityPercent = "-";
        }
        else {
            this.noCityPercent = noCityPercent;
        }
    }
}
