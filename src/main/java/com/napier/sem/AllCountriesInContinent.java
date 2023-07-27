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
        // Check if the continent name is null.
        if (continentName == null){
            System.out.println("The Continent name is not defined.");
        }

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
                                            "WHERE country.Continent = \"" + continentName +
                                            "\" ORDER BY country.Population DESC;";

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
            if (countries.isEmpty()) {
                return null;
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
    public static void printResult(String continentName, ArrayList<Country> countries) {
        // Check if both continent name and countries arraylist is null
        if (continentName == null && countries == null) {
            System.out.println("There is no countries or defined district name");
            return;
        }

        // Check if cities arraylist is null. If not, move on to the next condition.
        if (countries == null) {
            System.out.println("There is no countries");
            return;
        }

        // Check if district name is null. If not, move on to the next condition.
        if (continentName == null) {
            System.out.println("The continent name is not defined");
            return;
        }

        // Checking if the arraylist of countries is initialized but empty.
        if (countries.isEmpty()) {
            System.out.print("The countries ArrayList is empty.");
            return;
        }

        // Checking if the element of arraylist is null
        for (int i = 0; i <= countries.size() - 1; i++) {
            if (countries.get(i) == null) {
                System.out.println("The countries ArrayList contains null value.");
                return;
            }
        }

        // Printing out the headers of the report table.
        System.out.println("-------------------------------------------All Countries in the Continent By Largest Population To Smallest-------------------------------------");
        System.out.println("| Continent: " + continentName + "                                                                          Total Countries: " + countries.size());
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-3s | %-3s | %-38s | %-14s | %-26s | %-15s | %-35s | %n", "No", "Code", "Name", "Continent", "Region", "Population", "Capital");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");

        // Initializing the variable to be shown as row number.
        int i = 1;

        // For all the objects in countries arraylist, formatting and printing the values (Strings and Digits)
        for (Country country : countries) {
            // Printing the country object's attributes with Getter.
            System.out.printf("| %,3d | %-3s | %-38s | %-14s | %-26s | %,15d | %-35s | %n", i++, country.getCountryNo(), country.getCountryName(), country.getContinentName(), country.getRegionName(), country.getPopulation(), country.getCapitalName());
        }
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");
    }
}
