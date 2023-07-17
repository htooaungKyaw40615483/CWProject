package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Top10CitiesInContinent {
    /**
     * Return a continent's cities from the world database
     * @param bc Predefined continent Name
     * @param con Established Database Connection
     * @return the City Objects in an ArrayList which is from a single continent.
     */

    public static ArrayList<City> ReturnCity(String bc, Connection con){
        try{
            // Creating Statement Object to execute Query
            Statement stmt = con.createStatement();
            /*
             Defining the Query to be executed.
             QUERY: To SELECT  CityName, CountryName, DistrictName, Population of a city
             and continent name after JOINing two tables with City ID ORDERED by population in descending.
            */
            String sqlQueryCityInContinent = "SELECT world.city.Name, world.country.Name, world.city.District, world.city.Population FROM world.city " +
                    "INNER JOIN world.country ON world.city.CountryCode = world.country.Code " +
                    "WHERE world.country.Continent= \"" + bc + "\" " +
                    "ORDER BY world.city.Population DESC LIMIT 10;";

            // Storing the results in a ResultSet object, ALlCitiesInContinentResult
            ResultSet cityInContinentResult = stmt.executeQuery(sqlQueryCityInContinent);

            // Creating an arraylist of city objects to be stored and returned from the method
            ArrayList<City> Cities = new ArrayList<City>();

            // Retrieving the results from ResultSet object, CitiesInWorldResult as long as there is data left
            while(cityInContinentResult.next()) {

                // Creating a City object to be stored in arraylist
                City city = new City();
                city.setCity_name(cityInContinentResult.getString(1));
                city.setCountry_name(cityInContinentResult.getString(2));
                city.setDistrict_name(cityInContinentResult.getString(3));
                city.setCity_population(cityInContinentResult.getInt(4));

                // adding the city object to the arraylist
                Cities.add(city);
            }
            return Cities;
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
     * Printing a continent's cities from the world database
     * @param cities arraylist of city objects.
     */
    public static void printResult(String bc, ArrayList<City> cities){
        System.out.println("-------------------------------------------Top 10 Cities in A Continent by Largest Population to Smallest-----------------------------------------");
        System.out.println("| Continent: " + bc + "                                                                               ORDER: Largest to Smallest Population|");
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
