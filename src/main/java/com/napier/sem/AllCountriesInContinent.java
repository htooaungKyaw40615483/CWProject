package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;

/*
 * Purpose: To Retrieve All The Countries in Continent
 */

public class AllCountriesInContinent {

    /**
     * Return a countries population in continent from the world database
     * @param continentName Predefined Continent Name
     * @param con Established Database Connection
     * @return the Country Objects in an ArrayList which is from a single continent.
     */
    public static ArrayList<Country> returnCountries(String continentName, Connection con){
        try{
            // Creating Statement Object to execute Query
            Statement stmt = con.createStatement();

             /*
             Defining the Query to be executed.
             QUERY: To SELECT CountryCode, CountryName, ContinentName, RegionName, Population of a Country
             and capital name after JOINing two tables with City ID ORDERED by population in descending.
            */

            String sqlQueryCountryInContinent = "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, city.Name FROM `country` " +
                                            "INNER JOIN city ON country.Capital = city.ID " +
                                            "WHERE country.Continent = \"" + continentName + "\" ORDER BY country.Population DESC;";

            // Storing the results in a ResultSet object, ALlCountriesInContinentResult
            ResultSet countriesInContinent = stmt.executeQuery(sqlQueryCountryInContinent);

            // Creating an arraylist of country objects to be stored and returned from the method
            ArrayList<Country> countries = new ArrayList<Country>();

            // Retrieving the results from ResultSet object, CountriesInContinentResult as long as there is data left
            while(countriesInContinent.next()) {

                // Creating a Country object to be stored in arraylist
                Country country = new Country();

                // setting the attributes of country object with Setter
                country.setCountryNo(countriesInContinent.getString(1));
                country.setCountryName(countriesInContinent.getString(2));
                country.setContinentName(countriesInContinent.getString(3));
                country.setRegionName(countriesInContinent.getString(4));
                country.setPopulation(countriesInContinent.getInt(5));
                country.setCapitalName(countriesInContinent.getString(6));

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
            System.out.println("Failed to get countries population by continent name in the world");
            return null;
        }
    }
    /**
     * Printing a countries' population by continent from the world database
     * @param countries arraylist of country objects.
     */
    public static void printResult(String continentName, ArrayList<Country> countries){
        System.out.println("---------------------------------------------------All Countries in the Continent By Largest Population To Smallest---------------------------------------------------------------");
        System.out.println("| Continent: " + continentName + "                                                                                                                  Total Countries: " + countries.size());
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
