package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Top10CountriesInRegion {
    /**
     * Return a top 10 populated countries in region from the world database
     * @param regionName Predefined Region Name
     * @param con Established Database Connection
     * @return the Country Objects in an ArrayList which is from a single region.
     */
    public static ArrayList<Country> returnCountries(String regionName, Connection con){
        if (regionName == null){
            System.out.println("The Region name is not defined.");
        }
        try{
            // Creating Statement Object to execute Query
            Statement stmt = con.createStatement();
            /*
             Defining the Query to be executed.
             QUERY: To SELECT CountryCode, CountryName, ContinentName, RegionName, Population of a Country
             and capital name after JOINing two tables with City ID ORDERED by population in descending limit 10.
            */
            String sqlQueryTop10CountryInRegion = "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, city.Name " +
                    "FROM `country` INNER JOIN city ON country.Capital = city.ID " +
                    "WHERE country.Region = \"" + regionName + "\" ORDER BY country.Population DESC LIMIT 10;;";
            // Storing the results in a ResultSet object, Top10CountriesInRegionResult
            ResultSet top10CountriesInRegion = stmt.executeQuery(sqlQueryTop10CountryInRegion);
            // Creating an arraylist of country objects to be stored and returned from the method
            ArrayList<Country> countries = new ArrayList<Country>();
            // Retrieving the results from ResultSet object, Top10CountriesInRegionResult as long as there is data left
            while(top10CountriesInRegion.next()) {
                // Creating a Country object to be stored in arraylist
                Country country = new Country();
                // setting the attributes of country object with Setter
                country.setCountryNo(top10CountriesInRegion.getString(1));
                country.setCountryName(top10CountriesInRegion.getString(2));
                country.setContinentName(top10CountriesInRegion.getString(3));
                country.setRegionName(top10CountriesInRegion.getString(4));
                country.setPopulation(top10CountriesInRegion.getInt(5));
                country.setCapitalName(top10CountriesInRegion.getString(6));
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
            System.out.println("Failed to get Top 10 countries population by region name in the world");
            return null;
        }
    }

    /**
     * Printing a top 10 populated countries in a region from the world database
     * @param countries arraylist of country objects.
     */

    public static void printResult(String regionName, ArrayList<Country> countries){
        if(regionName == null && countries == null){
            System.out.println("There is no country or defined region name");
            return;
        }

        // Check if countries arraylist is null. If not, move on to the next condition.
        if (countries == null) {
            System.out.println("There is no countries");
            return;
        }

        // Check if region name is null. If not, move on to the next condition.
        if(regionName == null){
            System.out.println("The district name is not defined");
            return;
        }

        System.out.println("---------------------------------------------------Top 10 Countries in the Region By Largest Population To Smallest---------------------------------------------------------------------------");
        System.out.println("| Region: " + regionName + "                                                                                                                         ORDER: Largest to Smallest Population|");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
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
