package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Top10CitiesInRegion {
    /**
     * Return a top 10 region's cities from the world database
     * @param regionName Predefined region Name
     * @param con Established Database Connection
     * @return the City Objects in an ArrayList which is from a single region.
     */
    public static ArrayList<City> returnCity(String regionName, Connection con){
        try{

            // Creating Statement Object to execute Query
            Statement stmt = con.createStatement();

               /*
             Defining the Query to be executed.
             QUERY: To SELECT  CityName, CountryName, DistrictName, Population of a city
             and region name after JOINing two tables with City ID ORDERED by population in descending with limit of 10.
            */

            String sqlQueryTop10CityInRegion = "SELECT world.city.Name, world.country.Name, world.city.District, world.city.Population FROM world.city " +
                    "INNER JOIN world.country ON world.city.CountryCode = world.country.Code " +
                    "WHERE world.country.Region= \"" + regionName + "\" " +
                    "ORDER BY world.city.Population DESC LIMIT 10;";

            // Storing the results in a ResultSet object, ALlCitiesInRegionResult
            ResultSet top10CityInRegionResult = stmt.executeQuery(sqlQueryTop10CityInRegion);

            // Creating an arraylist of city objects to be stored and returned from the method
            ArrayList<City> cities = new ArrayList<City>();

            // Retrieving the results from ResultSet object, CitiesInRegionResult as long as there is data left
            while(top10CityInRegionResult.next()) {

                // Creating a City object to be stored in arraylist
                City city = new City();

                // setting the attributes of city object with Setter
                city.setCityName(top10CityInRegionResult.getString(1));
                city.setCountryName(top10CityInRegionResult.getString(2));
                city.setDistrictName(top10CityInRegionResult.getString(3));
                city.setCityPopulation(top10CityInRegionResult.getInt(4));

                // adding the city object to the arraylist
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
     * Printing a top 10 cities in region from the world database
     * @param cities arraylist of city objects.
     */
    public static void printResult(String regionName, ArrayList<City> cities){
        System.out.println("--------------------------------------Top 10 most populated Cities in A Region by Largest Population to Smallest-------------------------------");
        System.out.println("| Region: " + regionName + "                                                                                      ORDER: Largest to Smallest Population|");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-35s | %-40s | %-35s | %-20s | %n", "Name", "Country", "District", "Population");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");

        // For all the objects in cities arraylist, formatting and printing the values (Strings and Digits)
        for (City city :cities){
            // Printing the city object's attributes with Getter.
            System.out.printf("| %-35s | %-40s | %-35s | %,20d | %n", city.getCityName(), city.getCountryName(), city.getDistrictName(), city.getCityPopulation());
        }
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");

    }
}
