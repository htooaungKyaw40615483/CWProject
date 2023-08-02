package com.napier.sem;

/*
 * Purpose: To Retrieve population percentage of the region
 */

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class PopulationPercentInRegion {
    /**
     * Return population percent in the region from the world database
     * @param con Established Database Connection
     * @return the population Objects in an ArrayList which is from a world.
     */
    public static ArrayList<Population> returnPopulation(Connection con){
        try{
            // Creating Statement Object to execute Query
            Statement stmt = con.createStatement();

            /*
             Defining the Query to be executed.
             QUERY: To SELECT region name, total population , people living in city percent and no city percent
             after JOINing two tables with City and group by region.
            */
            String sqlQueryPopulationPercentRegion = "SELECT comp.Region, comp.total_population,\n" +
                    "CONCAT(ROUND((city_population / comp.total_population) * 100, 2), '%') AS in_city_percent,\n" +
                    "CONCAT(ROUND(((comp.total_population - city_population) / comp.total_population) * 100, 2), '%') AS not_city_percent\n" +
                    "FROM (SELECT Region, SUM(population) AS total_population FROM country GROUP BY Region) comp\n" +
                    "JOIN (SELECT country.Region, SUM(city.population) AS city_population FROM city JOIN country \n" +
                    "ON country.code = city.countrycode GROUP BY country.Region) cip ON comp.Region = cip.Region ORDER BY comp.total_population DESC;";

            // Storing the results in a ResultSet object, PopulationPercentRegionResult
            ResultSet populationInRegion = stmt.executeQuery(sqlQueryPopulationPercentRegion);

            // Creating an arraylist of population objects to be stored and returned from the method
            ArrayList<Population> populations = new ArrayList<Population>();
            // Creating a Population object to be stored in arraylist
            Population population = new Population();
            // Retrieving the results from ResultSet object, PopulationPercentRegionResult as long as there is data left
            while(populationInRegion.next()) {




                // setting the attributes of population object with Setter
                population.setName(populationInRegion.getString(1));
                population.setTotalPopulation(populationInRegion.getLong(2));
                population.setYesCityPercent(populationInRegion.getString(3));
                population.setNoCityPercent(populationInRegion.getString(4));


                // adding the population object to the arraylist
                populations.add(population);
            }
            return populations;
        }
         /*
         Catching the error if there is
         Printing the error and returning null
        */
        catch(Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population percent in the continent");
            return null;
        }
    }
    /**
     * Printing a population percent in the region from the world database
     * @param populations arraylist of population objects.
     */

    public static void printResult(ArrayList<Population> populations){
        // Check if populations arraylist is null. If not, move on to the next condition.
        if (populations == null) {
            System.out.println("There is no region population");
            return;
        }

        // Checking if the arraylist of populations is initialized but empty.
        if (populations.isEmpty()){
            System.out.print("The populations ArrayList is empty.");
            return;
        }

        // Checking if the element of arraylist is null
        for (Population population : populations) {
            if (population == null) {
                System.out.println("The populations ArrayList contains null value.");
                return;
            }
        }

        // Printing out the headers of the report table.
        System.out.println("--------------------------------------------------Population Report For All Regions-----------------------------------------------------");
        System.out.printf("| %-5s | %-40s | %-25s | %-25s | %-25s | %n", "No", "Region Name", "Total Population", "City Percentage", "Not City Percentage");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");

        // Initializing the variable to be shown as row number.
        int  i = 1;

        // For all the objects in populations arraylist, formatting and printing the values (Strings and Digits)
        for (Population population :populations){
            // Printing the population object's attributes with Getter.
            System.out.printf("| %-5s | %-40s | %,25d | %-25s | %-25s | %n", i++, population.getName(), population.getTotalPopulation(), population.getYesCityPercent(), population.getNoCityPercent());
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
    }
}
