package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class RegionPopulation {
    /**
     * Return population of region from the world database
     * @param con Established Database Connection
     * @return the Population Objects in an ArrayList which is from a world.
     */
    public static ArrayList<Population> returnPopulation(String regionName, Connection con){
        if (regionName == null){
            System.out.println("The Region name is not defined.");
        }
        try{
            // Creating Statement Object to execute Query
            Statement stmt = con.createStatement();

            /*
             Defining the Query to be executed.
             QUERY: continent Population
            */
            String sqlQueryRegionPopulation = "SELECT country.Region, SUM(country.Population) AS Region_Population,\n" +
                    "CONCAT(ROUND((SUM(coalesce(city.Population, 0)) / SUM(country.Population)) * 100, 2), '%') AS people_living_in_cities, CONCAT(ROUND(((SUM(country.Population) - SUM(coalesce(city.Population, 0))) / SUM(country.Population)) * 100, 2), '%') AS people_not_living_in_cities FROM country LEFT JOIN \n" +
                    "(SELECT CountryCode, SUM(Population) AS Population FROM city GROUP BY CountryCode) city ON \n" +
                    "country.Code = city.CountryCode WHERE country.Region = \"" + regionName +"\" GROUP BY country.Region";

            // Storing the results in a ResultSet object, regionPopulationResult
            ResultSet regionPopulation = stmt.executeQuery(sqlQueryRegionPopulation);

            // Creating an arraylist of population objects to be stored and returned from the method
            ArrayList<Population> populations = new ArrayList<Population>();
            // Creating a Population object to be stored in arraylist
            Population population = new Population();
            // Retrieving the results from ResultSet object, RegionPopulationResult as long as there is data left
            while(regionPopulation.next()) {




                // setting the attributes of population object with Setter
                population.setName(regionPopulation.getString(1));
                population.setTotalPopulation(regionPopulation.getLong(2));
                population.setYesCityPercent(regionPopulation.getString(3));
                population.setNoCityPercent(regionPopulation.getString(4));

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
            System.out.println("Failed to get RegionPopulation");
            return null;
        }
    }
    /**
     * Printing a region population from the world database
     * @param populations arraylist of population objects.
     */
    public static void printResult(String regionName, ArrayList<Population> populations){
        // Check if populations arraylist is null. If not, move on to the next condition.
        if (populations == null) {
            System.out.println("There is no population");
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
        System.out.println("-----------------------------------------------Region Population------------------------------------------------------------------------");
        System.out.println("| Region: " + regionName + "                                                         ORDER: Largest to Smallest Population");
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
