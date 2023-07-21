package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Top10CountriesInContinent {
    /**
     * Return a top 10 populated countries in continent from the world database
     * @param continentName Predefined Continent Name
     * @param con Established Database Connection
     * @return the Country Objects in an ArrayList which is from a single continent.
     */
    public static ArrayList<Country> returnCountries(String continentName, Connection con){
        if (continentName == null){
            System.out.println("The Continent name is not defined.");
        }

        try{
            // Creating Statement Object to execute Query
            Statement stmt = con.createStatement();

             /*
             Defining the Query to be executed.
             QUERY: To SELECT CountryCode, CountryName, ContinentName, RegionName, Population of a Country
             and capital name after JOINing two tables with City ID ORDERED by population in descending limit 10.
            */

            String sqlQueryTop10CountryInContinent = "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, city.Name FROM `country`" +
                    "INNER JOIN city ON country.Capital = city.ID " +
                    "WHERE country.Continent = \"" + continentName + "\" ORDER BY country.Population DESC LIMIT 10;";

            // Storing the results in a ResultSet object, Top10CountriesInContinentResult
            ResultSet top10CountriesInContinent = stmt.executeQuery(sqlQueryTop10CountryInContinent);

            // Creating an arraylist of country objects to be stored and returned from the method
            ArrayList<Country> countries = new ArrayList<Country>();

            // Retrieving the results from ResultSet object, Top10CountriesInContinentResult as long as there is data left
            while(top10CountriesInContinent.next()) {

                // Creating a Country object to be stored in arraylist
                Country country = new Country();

                // setting the attributes of country object with Setter
                country.setCountryNo(top10CountriesInContinent.getString(1));
                country.setCountryName(top10CountriesInContinent.getString(2));
                country.setContinentName(top10CountriesInContinent.getString(3));
                country.setRegionName(top10CountriesInContinent.getString(4));
                country.setPopulation(top10CountriesInContinent.getInt(5));
                country.setCapitalName(top10CountriesInContinent.getString(6));

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
            System.out.println("Failed to get top 10 countries population by continent name in the world");
            return null;
        }
    }
    /**
     * Printing a top 10 populated countries in the continent from the world database
     * @param countries arraylist of country objects.
     */
    public static void printResult(String continentName, ArrayList<Country> countries){

        if(continentName == null && countries == null){
            System.out.println("There is no countries or defined district name");
            return;
        }

        // Check if cities arraylist is null. If not, move on to the next condition.
        if (countries == null) {
            System.out.println("There is no countries");
            return;
        }

        // Check if district name is null. If not, move on to the next condition.
        if(continentName == null){
            System.out.println("The continent name is not defined");
            return;
        }

        System.out.println("---------------------------------------------------Top 10 Countries in the Continent By Largest Population To Smallest------------------------------------------------------------------------");
        System.out.println("| Continent: " + continentName + "                                                                                                                  ORDER: Largest to Smallest Population|");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-5s | %-7s | %-40s | %-25s | %-30s | %-15s | %-35s | %n", "No", "Code", "Name", "Continent", "Region", "Population", "Capital");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        int  i = 1;

        // For all the objects in countries arraylist, formatting and printing the values (Strings and Digits)
        for (Country country :countries){
            // Printing the country object's attributes with Getter.
            System.out.printf("| %,5d | %-7s | %-40s | %-25s | %-30s | %,15d | %-35s | %n", i++, country.getCountryNo(), country.getCountryName(), country.getContinentName(), country.getRegionName(), country.getPopulation(), country.getCapitalName());
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

    }
}
