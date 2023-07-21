package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;

/*
 * Purpose: To Retrieve All The Countries in World
 */

public class AllCountriesInWorld {

    /**
     * Return a countries population in world from the world database
     * @param con Established Database Connection
     * @return the Country Objects in an ArrayList which is from a world.
     */
    public static ArrayList<Country> returnCountries(Connection con){
        try{
            // Creating Statement Object to execute Query
            Statement stmt = con.createStatement();
            /*
             Defining the Query to be executed.
             QUERY: To SELECT CountryCode, CountryName, ContinentName, RegionName, Population of a Country
             and capital name after JOINing two tables with City ID ORDERED by population in descending.
            */
            String sqlQueryCountryInWorld = "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, city.Name " +
                                            "FROM country, city WHERE city.ID = country.Capital ORDER BY country.Population DESC;";
            // Storing the results in a ResultSet object, ALlCountriesInWorldResult
            ResultSet countriesInWorld = stmt.executeQuery(sqlQueryCountryInWorld);
            // Creating an arraylist of country objects to be stored and returned from the method
            ArrayList<Country> countries = new ArrayList<Country>();
            // Retrieving the results from ResultSet object, CountriesInWorldResult as long as there is data left
            while(countriesInWorld.next()) {
                // Creating a Country object to be stored in arraylist
                Country country = new Country();
                // setting the attributes of country object with Setter
                country.setCountryNo(countriesInWorld.getString(1));
                country.setCountryName(countriesInWorld.getString(2));
                country.setContinentName(countriesInWorld.getString(3));
                country.setRegionName(countriesInWorld.getString(4));
                country.setPopulation(countriesInWorld.getInt(5));
                country.setCapitalName(countriesInWorld.getString(6));
                // adding the country object to the arraylist
                countries.add(country);
            }
            return countries;
        }
         /*
         Catching the error if there is
         Printing the error and returning null
        */
        catch(Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get countries population in the world");
            return null;
        }
    }

    /**
     * Printing a countries population in the world from the world database
     * @param countries arraylist of country objects.
     */
    public static void printResult(ArrayList<Country> countries){

        // Check if countries arraylist is null. If not, move on to the next condition.
        if (countries == null) {
            System.out.println("There is no countries");
            return;
        }

        System.out.println("---------------------------------------------------All Countries in the World By Largest Population To Smallest-------------------------------------------------------------------------------");
        System.out.println("Total Countries: " + countries.size() + " -------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-5s | %-8s | %-40s | %-30s | %-30s | %-20s | %-35s | %n", "No", "Code", "Name", "Continent", "Region", "Population", "Capital");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        int  i = 1;

        // For all the objects in countries arraylist, formatting and printing the values (Strings and Digits)
        for (Country country :countries){
            // Printing the country object's attributes with Getter.
            System.out.printf("| %,5d | %-8s | %-40s | %-30s | %-30s | %,20d | %-35s | %n", i++, country.getCountryNo(), country.getCountryName(), country.getContinentName(), country.getRegionName(), country.getPopulation(), country.getCapitalName());
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

    }
}
