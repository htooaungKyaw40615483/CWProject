package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Top10CountriesInContinent {
    /**
     * Return a top 10 populated countries in continent from the world database
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
             and capital name after JOINing two tables with City ID ORDERED by population in descending limit 10.
            */

            String sqlQueryTop10CountryInContinent = "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, city.Name FROM `country`" +
                    "INNER JOIN city ON country.Capital = city.ID " +
                    "WHERE country.Continent = \"" + ctn + "\" ORDER BY country.Population DESC LIMIT 10;";

            // Storing the results in a ResultSet object, Top10CountriesInContinentResult
            ResultSet Top10CountriesInContinent = stmt.executeQuery(sqlQueryTop10CountryInContinent);

            // Creating an arraylist of country objects to be stored and returned from the method
            ArrayList<Country> Countries = new ArrayList<Country>();

            // Retrieving the results from ResultSet object, Top10CountriesInContinentResult as long as there is data left
            while(Top10CountriesInContinent.next()) {

                // Creating a Country object to be stored in arraylist
                Country country = new Country();

                // setting the attributes of country object with Setter
                country.setCountry_no(Top10CountriesInContinent.getString(1));
                country.setCountry_name(Top10CountriesInContinent.getString(2));
                country.setContinent_name(Top10CountriesInContinent.getString(3));
                country.setRegion_name(Top10CountriesInContinent.getString(4));
                country.setPopulation(Top10CountriesInContinent.getInt(5));
                country.setCapital_name(Top10CountriesInContinent.getString(6));

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
            System.out.println("Failed to get top 10 countries population by continent name in the world");
            return null;
        }
    }
    /**
     * Printing a top 10 populated countries in the continent from the world database
     * @param countries arraylist of country objects.
     */
    public static void printResult(String ctn, ArrayList<Country> countries){
        System.out.println("---------------------------------------------------Top 10 Countries in the Continent By Largest Population To Smallest-------------------------------------------------------------");
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
