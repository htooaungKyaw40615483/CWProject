package com.napier.sem;

// Encapsulation Applied: Used Getters, Setters, and Private variables.
// Class Attributes used only with getMethod (read-only) or setMethod(write-only).
public class City {
    // City Name
    private String cityName;
    // Country Name
    private String countryName;
    // District Name
    private String districtName;
    // City Population
    private int cityPopulation;


    // Getters for accessing the class attributes only for reading values.

    /**
     * Getting the City Name of the class
     * @return City Name
     */
    public String getCityName(){
        return cityName;
    }

    /**
     * Getting the Country Name of the class
     * @return Country Name
     */
    public String getCountryName(){
        return countryName;
    }

    /**
     * Getting the District Name of the class
     * @return District Name
     */
    public String getDistrictName(){
        return districtName;
    }

    /**
     * Getting the City Population of the class
     * @return City Population
     */
    public int getCityPopulation(){
        return cityPopulation;
    }


    // Setters for accessing the class attributes only for changing/inserting values.

    /**
     * Setting the City Name of the Class
     * @param cityName City Name
     */
    public void setCityName(String cityName) {
        if (cityName == null){
            System.out.println("The city name is null");
            this.cityName = "-";
        }
        else {
            this.cityName = cityName;
        }
    }
    /**
     * Setting the Country Name of the Class
     * @param countryName Country Name
     */
    public void setCountryName(String countryName) {
        if (countryName == null){
            System.out.println("The country name is null");
            this.countryName = "-";
        }
        else this.countryName = countryName;
    }
    /**
     * Setting the District Name of the Class
     * @param districtName District Name
     */
    public void setDistrictName(String districtName) {
        if (districtName == null) {
            System.out.println("The district name is null");
            this.districtName = "-";
        } else this.districtName = districtName;
    }

    /**
     * Setting the City Population of the Class
     * @param cityPopulation City Population
     */
    public void setCityPopulation(int cityPopulation) {
        if (cityPopulation < 0){
            System.out.println("City Population is less than zero. Storing as zero population");
            this.cityPopulation = 0;
        }
        else{
            this.cityPopulation = cityPopulation;
        }
    }
}
