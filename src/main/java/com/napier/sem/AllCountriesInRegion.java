package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;

/*
 * Purpose: To Retrieve All The Countries in Region
 */

public class AllCountriesInRegion {

    /**
     * Return a countries population in region from the world database
     * @param regionName Predefined Region Name
     * @param con Established Database Connection
     * @return the Country Objects in an ArrayList which is from a single region.
     */
    public static ArrayList<Country> returnCountries(String regionName, Connection con){
        try{
            // Creating Statement Object to execute Query
            Statement stmt = con.createStatement();
            /*
             Defining the Query to be executed.
             QUERY: To SELECT CountryCode, CountryName, ContinentName, RegionName, Population of a Country
             and capital name after JOINing two tables with City ID ORDERED by population in descending.
            */
            String sqlQueryCountryInRegion = "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, city.Name " +
                    "FROM `country` INNER JOIN city ON country.Capital = city.ID " +
                    "WHERE country.Region = \"" + regionName + "\" ORDER BY country.Population DESC;;";
            // Storing the results in a ResultSet object, ALlCountriesInRegionResult
            ResultSet countriesInRegion = stmt.executeQuery(sqlQueryCountryInRegion);
            // Creating an arraylist of country objects to be stored and returned from the method
            ArrayList<Country> countries = new ArrayList<Country>();
            // Retrieving the results from ResultSet object, CountriesInRegionResult as long as there is data left
            while(countriesInRegion.next()) {
                // Creating a Country object to be stored in arraylist
                Country country = new Country();
                // setting the attributes of country object with Setter
                country.setCountryNo(countriesInRegion.getString(1));
                country.setCountryName(countriesInRegion.getString(2));
                country.setContinentName(countriesInRegion.getString(3));
                country.setRegionName(countriesInRegion.getString(4));
                country.setPopulation(countriesInRegion.getInt(5));
                country.setCapitalName(countriesInRegion.getString(6));
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
            System.out.println("Failed to get countries population by region name in the world");
            return null;
        }
    }

    /**
     * Printing a countries' population by a region name from the world database
     * @param countries arraylist of country objects.
     */

    public static void printResult(String regionName, ArrayList<Country> countries){
        System.out.println("---------------------------------------------------All Countries in the Region By Largest Population To Smallest------------------------------------------------------------------");
        System.out.println("| Region: " + regionName + "                                                                                                                         Total Countries: " + countries.size());
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-4s | %-40s | %-30s | %-30s | %-20s | %-35s | %n", "Code", "Name", "Continent", "Region", "Population", "Capital");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        // For all the objects in countries arraylist, formatting and printing the values (Strings and Digits)
        for (Country country :countries){
            // Printing the country object's attributes with Getter.
            System.out.printf("| %-4s | %-40s | %-30s | %-30s | %,20d | %-35s | %n", country.getCountryNo(), country.getCountryName(), country.getContinentName(), country.getRegionName(), country.getPopulation(), country.getCapitalName());
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

    }
}
