package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;


/*
 * Purpose: To Retrieve All Cities In A Region
 */
public class AllCitiesInRegion {
    /**
     * Return a region's cities from the world database
     * @param regionName Predefined region Name
     * @param con Established Database Connection
     * @return the City Objects in an ArrayList which is from a single region.
     */
    private Connection con; // Class member variable to store the database connection

    // Constructor to initialize the database connection
    public AllCitiesInRegion(Connection con) {
        this.con = con;
    }
    public static ArrayList<City> returnCity(String regionName, Connection con){
        //Checking if the country name is entered.
        if (regionName == null){
            System.out.println("The region name is not defined.");
        }

        try{
            // Creating Statement Object to execute Query
            Statement stmt = con.createStatement();

            /*
             Defining the Query to be executed.
             QUERY: To SELECT CityName, CountryName, DistrictName, Population of a Country
             after JOINing two tables with country code ORDERED by population in descending.
            */
            String sqlQueryCityInRegion = "SELECT world.city.Name, world.country.Name, world.city.District, world.city.Population FROM world.city " +
                    "INNER JOIN world.country ON world.city.CountryCode = world.country.Code " +
                    "WHERE world.country.Region= \"" + regionName + "\" " +
                    "ORDER BY world.city.Population DESC;";

            // Storing the results in a ResultSet object, cityInCountryResult
            ResultSet cityInRegionResult = stmt.executeQuery(sqlQueryCityInRegion);

            // Creating an arraylist of city objects to be stored and returned from the method
            ArrayList<City> cities = new ArrayList<City>();

            // Retrieving the results from ResultSet object, cityInCountryResult as long as there is data left
            while(cityInRegionResult.next()) {

                // Creating a City object to be stored in arraylist
                City city = new City();

                // setting the attributes of city object with Setter
                city.setCityName(cityInRegionResult.getString(1));
                city.setCountryName(cityInRegionResult.getString(2));
                city.setDistrictName(cityInRegionResult.getString(3));
                city.setCityPopulation(cityInRegionResult.getInt(4));

                // setting the attributes of city object with Setter
                cities.add(city);
            }
            return cities;
        }
        /*
         Catching the error if there is
         Printing the error and returning null
        */
        catch(Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city populations");
            return null;
        }
    }

    /**
     * Printing a region's cities from the world database
     * @param cities arraylist of city objects.
     */
    public static void printResult(String regionName, ArrayList<City> cities){
        // Check if the region name AND cities is null. If not, move on to the next condition.
        if(regionName == null && cities == null){
            System.out.println("There is no cities or defined region name");
            return;
        }

        // Check if cities arraylist is null. If not, move on to the next condition.
        if (cities == null) {
            System.out.println("There is no cities");
            return;
        }

        // Check if district name is null. If not, move on to the next condition.
        if(regionName == null){
            System.out.println("The region name is not defined");
            return;
        }

        // Checking if the arraylist is empty
        if (cities.isEmpty()){
            System.out.print("The cities ArrayList is empty.");
            return;
        }

        // Checking if the element of arraylist is null
        for(int i = 0; i<= cities.size()-1; i++){
            if (cities.get(i) == null) {
                System.out.println("The cities ArrayList contains null value.");
                return;
            }
        }

        // Printing out the headers of the report table.
        System.out.println("-------------------------------------------All Cities in A Region by Largest Population to Smallest-----------------------------------------------");
        System.out.println("| Region: " + regionName + "                                                                                      Total Cities: " + cities.size());
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-5s | %-35s | %-37s | %-32s | %-21s | %n", "No", "Name", "Country", "District", "Population");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------");

        // Initializing the variable to be shown as row number.
        int  i = 1;

        // For all the objects in cities arraylist, formatting and printing the values (Strings and Digits)
        for (City city :cities){
            // Printing the city object's attributes with Getter.
            System.out.printf("| %,5d | %-35s | %-37s | %-31s  | %,20d  |  %n", i++,  city.getCityName(), city.getCountryName(), city.getDistrictName(), city.getCityPopulation());
        }
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------");
    }
}