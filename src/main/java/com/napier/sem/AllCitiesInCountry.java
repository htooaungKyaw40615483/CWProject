package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;

/*
 * Purpose: To Retrieve All The Cities In A Country
 */
public class AllCitiesInCountry {
    /**
     * Return a country's cities from the world database
     * @param countryName Predefined Country Name
     * @param con Established Database Connection
     * @return the City Objects in an ArrayList which is from a single country.
     */
    public static ArrayList<City> returnCity(String countryName, Connection con){
        //Checking if the country name is entered.
        if (countryName == null){
            System.out.println("The Country name is not defined.");
        }

        try{
            // Creating Statement Object to execute Query
            Statement stmt = con.createStatement();

            /*
             Defining the Query to be executed.
             QUERY: To SELECT CityName, CountryName, DistrictName, Population of a Country
             after JOINing two tables with country code ORDERED by population in descending.
            */
            String sqlQueryCityInCountry = "SELECT world.city.Name, world.country.Name, world.city.District, world.city.Population FROM world.city " +
                    "INNER JOIN world.country ON world.city.CountryCode = world.country.Code " +
                    "WHERE world.country.Name= \"" + countryName + "\" " +
                    "ORDER BY world.city.Population DESC;";

            // Storing the results in a ResultSet object, cityInCountryResult
            ResultSet cityInCountryResult = stmt.executeQuery(sqlQueryCityInCountry);

            // Creating an arraylist of city objects to be stored and returned from the method
            ArrayList<City> cities = new ArrayList<City>();

            // Retrieving the results from ResultSet object, cityInCountryResult as long as there is data left
            while(cityInCountryResult.next()) {

                // Creating a City object to be stored in arraylist
                City city = new City();

                // setting the attributes of city object with Setter
                city.setCityName(cityInCountryResult.getString(1));
                city.setCountryName(cityInCountryResult.getString(2));
                city.setDistrictName(cityInCountryResult.getString(3));
                city.setCityPopulation(cityInCountryResult.getInt(4));

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
     * Printing a country's cities from the world database
     * @param cities arraylist of city objects.
     */
    public static void printResult(String countryName, ArrayList<City> cities){
        // Check if the country name AND cities is null. If not, move on to the next condition.
        if(countryName == null && cities == null){
            System.out.println("There is no cities or defined district name");
            return;
        }

        // Check if cities arraylist is null. If not, move on to the next condition.
        if (cities == null) {
            System.out.println("There is no cities");
            return;
        }

        // Check if country name is null. If not, move on to the next condition.
        if(countryName == null){
            System.out.println("The district name is not defined");
            return;
        }

        System.out.println("-------------------------------------------All Cities in A Country by Largest Population to Smallest-------------------------------------------");
        System.out.println("| Country: " + countryName + "                                                                                         Total Cities: " + cities.size());
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
