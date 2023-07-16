package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
/*
 * Purpose: To Retrieve All The Cities In the world
 */
public class AllCitiesInWorld {
    public static ArrayList<City> getAllCities(Connection con) {
        try {
            // Creating SQL Statement
            Statement stmt = con.createStatement();
            /*
             * Create string for SQL statement for extracting City Name, Country Name, District name, Population,
             * Country Code
             * */
            String sqlQueryAllCities = "SELECT city.Name, country.Name, city.District, city.Population FROM city " +
                    "INNER JOIN country ON city.CountryCode = country.Code " +
                    "ORDER BY city.Population DESC;";

            // Storing the results in a ResultSet object, allCitiesResult
            ResultSet allCitiesResult = stmt.executeQuery(sqlQueryAllCities);

            // Creating an arraylist of city objects to be stored and returned from the method
            ArrayList<City> cities = new ArrayList<>();

            // Retrieving the results from ResultSet object, allCitiesResult as long as there is data left
            while (allCitiesResult.next()) {

                //Creating a city object to be stored in arraylist
                City city = new City();

                // setting the attributes of city object with Setter
                city.setCity_name(allCitiesResult.getString(1));
                city.setCountry_name(allCitiesResult.getString(2));
                city.setDistrict_name(allCitiesResult.getString(3));
                city.setCity_population(allCitiesResult.getInt(4));

                //adding the city object to the arraylist
                cities.add(city);
            }
            return cities;
        /*
         Catching the error if there is
         Printing the error and returning null
        */

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city populations");
            return null;
        }
    }

    public static void printResult(ArrayList<City> cities) {
        System.out.println("-------------------------------------------All Cities in the World by Largest Population to Smallest-------------------------------------------");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-35s | %-40s | %-35s | %-20s |%n", "Name", "Country", "District", "Population");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");

        // For all the objects in cities arraylist, formatting and printing the values (Strings and Digits)
        for (City city : cities) {

            // Printing the city object's attributes with Getter.
            System.out.printf("| %-35s | %-40s | %-35s | %,20d |%n", city.getCityName(), city.getCountryName(), city.getDistrictName(), city.getCityPopulation());
        }
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");
    }
}
