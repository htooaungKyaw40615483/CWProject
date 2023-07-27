package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;

/*
 * Purpose: To Retrieve All The Cities In the world
 */
public class AllCitiesInWorld {
    /**
     * Return a country's cities from the world database
     * @param con Established Database Connection
     * @return the City Objects in an ArrayList which is from a single country.
     */
    public static ArrayList<City> returnCity(Connection con) {
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
                city.setCityName(allCitiesResult.getString(1));
                city.setCountryName(allCitiesResult.getString(2));
                city.setDistrictName(allCitiesResult.getString(3));
                city.setCityPopulation(allCitiesResult.getInt(4));

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
    /**
     * Printing a country's cities from the world database
     * @param cities arraylist of city objects.
     */
    public static void printResult(ArrayList<City> cities) {
        // Check if cities arraylist is null. If not, move on to the next condition.
        if (cities == null) {
            System.out.println("There is no cities");
            return;
        }

        // Checking if the arraylist of cities is initialized but empty.
        if (cities.isEmpty()){
            System.out.print("The cities ArrayList is empty.");
            return;
        }

        // Checking if the element of arraylist is null
        for(int i = 0; i<= cities.size()-1; i++){
            if (cities.get(i) == null){
                System.out.println("The cities ArrayList contains null value.");
                return;
            }
        }

        // Printing out the headers of the report table.
        System.out.println("-----------------------------------------All Cities in the World by Largest Population to Smallest---------------------");
        System.out.println("Total number of Cities: " + cities.size());
        System.out.println("-----------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-5s | %-35s | %-37s | %-32s | %-19s | %n", "No", "Name", "Country", "District", "Population");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------");

        // Initializing the variable to be shown as row number.
        int  i = 1;

        // For all the objects in cities arraylist, formatting and printing the values (Strings and Digits)
        for (City city :cities){
            // Printing the city object's attributes with Getter.
            System.out.printf("| %,5d | %-35s | %-37s | %-31s  | %,20d  |  %n", i++,  city.getCityName(), city.getCountryName(), city.getDistrictName(), city.getCityPopulation());
        }
        System.out.println("------------------------------------------------------------------------------------------------------------------------");
    }
}
