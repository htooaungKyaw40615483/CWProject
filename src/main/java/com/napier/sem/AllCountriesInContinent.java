package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;

/*
 * Purpose: To Retrieve All The Countries in Continent
 */

public class AllCountriesInContinent {

    /**
     * Return a country's cities from the world database
     * @param ctn Predefined Continent Name
     * @param con Established Database Connection
     * @return the Country Objects in an ArrayList which is from a single continent.
     */
    public static ArrayList<Country> ReturnCountries(String ctn, Connection con){
        try{
            // Creating Statement Object to execute Query
            Statement stmt = con.createStatement();

             /*
             Defining the Query to be executed.
             QUERY: To SELECT CountryCode, CountryName, ContinentName, RegionName, Population of a Country
             and capital name after JOINing two tables with City ID ORDERED by population in descending.
            */

            String sqlQueryCountryInWorld = "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, city.Name FROM `country` " +
                                            "INNER JOIN city ON country.Capital = city.ID " +
                                            "WHERE country.Continent = \"" + ctn + "\" ORDER BY country.Population DESC;";

            // Storing the results in a ResultSet object, ALlCountriesInContinentResult
            ResultSet CountriesInWorld = stmt.executeQuery(sqlQueryCountryInWorld);

            // Creating an arraylist of country objects to be stored and returned from the method
            ArrayList<Country> Countries = new ArrayList<Country>();

            // Retrieving the results from ResultSet object, CountriesInWorldResult as long as there is data left
            while(CountriesInWorld.next()) {

                // Creating a Country object to be stored in arraylist
                Country country = new Country();

                // setting the attributes of country object with Setter
                country.setCountry_no(CountriesInWorld.getString(1));
                country.setCountry_name(CountriesInWorld.getString(2));
                country.setContinent_name(CountriesInWorld.getString(3));
                country.setRegion_name(CountriesInWorld.getString(4));
                country.setPopulation(CountriesInWorld.getInt(5));
                country.setCapital_name(CountriesInWorld.getString(6));

                // adding the country object to the arraylist
                Countries.add(country);
            }
            return Countries;
        }
        /*
         Catching the error if there is
         Printing the error and returning null
        */
        catch(Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get countries population by continent name in the world");
            return null;
        }
    }
    /**
     * Printing a country's cities from the world database
     * @param countries arraylist of city objects.
     */
    public static void printResult(String ctn, ArrayList<Country> countries){
        System.out.println("---------------------------------------------------All Countries in the Continent By Largest Population To Smallest---------------------------------------------------------------");
        System.out.println("| Continent: " + ctn + "                                                                                                                  ORDER: Largest to Smallest Population|");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-4s | %-40s | %-30s | %-30s | %-20s | %-35s | %n", "Code", "Name", "Continent", "Region", "Population", "Capital");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        // For all the objects in countries arraylist, formatting and printing the values (Strings and Digits)
        for (Country country :countries){
            // Printing the country object's attributes with Getter.
            System.out.printf("| %-4s | %-40s | %-30s | %-30s | %,20d | %-35s | %n", country.getCountry_no(), country.getCountry_name(), country.getContinent_name(), country.getRegion_name(), country.getPopulation(), country.getCapital_name());
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

    }
}
