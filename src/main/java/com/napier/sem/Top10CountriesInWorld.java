package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Top10CountriesInWorld {
    /**
     * Return a top 10 populated countries in world from the world database
     * @param con Established Database Connection
     * @return the Country Objects in an ArrayList which is from the whole world.
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
            String sqlQueryTop10CountryInWorld = "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, city.Name " +
                    "FROM country, city WHERE city.ID = country.Capital ORDER BY country.Population DESC LIMIT 10;";
            // Storing the results in a ResultSet object, Top10CountriesInWorldResult
            ResultSet top10CountriesInWorld = stmt.executeQuery(sqlQueryTop10CountryInWorld);
            // Creating an arraylist of country objects to be stored and returned from the method
            ArrayList<Country> countries = new ArrayList<Country>();
            // Retrieving the results from ResultSet object, Top10CountriesInWorldResult as long as there is data left
            while(top10CountriesInWorld.next()) {
                // Creating a Country object to be stored in arraylist
                Country country = new Country();
                // setting the attributes of country object with Setter
                country.setCountryNo(top10CountriesInWorld.getString(1));
                country.setCountryName(top10CountriesInWorld.getString(2));
                country.setContinentName(top10CountriesInWorld.getString(3));
                country.setRegionName(top10CountriesInWorld.getString(4));
                country.setPopulation(top10CountriesInWorld.getInt(5));
                country.setCapitalName(top10CountriesInWorld.getString(6));
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
            System.out.println("Failed to get top 10 countries population in the world");
            return null;
        }
    }

    /**
     * Printing a top 10 populated countries in the world from the world database
     * @param countries arraylist of country objects.
     */
    public static void printResult(ArrayList<Country> countries){
        System.out.println("---------------------------------------------------Top 10 Countries in the World By Largest Population To Smallest------------------------------");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-4s | %-25s | %-22s | %-30s | %-20s | %-33s | %n", "Code", "Name", "Continent", "Region", "Population", "Capital");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");
        // For all the objects in countries arraylist, formatting and printing the values (Strings and Digits)
        for (Country country :countries){
            // Printing the country object's attributes with Getter.
            System.out.printf("| %,2d | %-4s | %-25s | %-22s | %-30s | %,20d | %-33s | %n", country.getCountryNo(), country.getCountryName(), country.getContinentName(), country.getRegionName(), country.getPopulation(), country.getCapitalName());
        }
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");

    }
}
