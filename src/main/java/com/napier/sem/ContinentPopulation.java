package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ContinentPopulation {
    /**
     * Return population of continent from the world database
     * @param con Established Database Connection
     * @return the Population Objects in an ArrayList which is from a world.
     */
    public static ArrayList<Population> returnPopulation(String continentName, Connection con){
        if (continentName == null){
            System.out.println("The Continent name is not defined.");
        }
        try{
            // Creating Statement Object to execute Query
            Statement stmt = con.createStatement();

            /*
             Defining the Query to be executed.
             QUERY: continent Population
            */
            String sqlQueryContinentPopulation = "SELECT country.Continent, SUM(country.Population) AS Continent_Population,\n" +
                    "CONCAT(ROUND((SUM(COALESCE(city.Population, 0)) / SUM(country.Population)) * 100, 2), '%') AS percent_people_living_in_cities,\n" +
                    "CONCAT(ROUND(((SUM(country.Population) - SUM(COALESCE(city.Population, 0))) / SUM(country.Population)) * 100, 2), '%') AS percent_people_not_living_in_cities\n" +
                    "FROM country LEFT JOIN (SELECT CountryCode, SUM(Population) AS Population FROM city GROUP BY CountryCode) city\n" +
                    "ON country.Code = city.CountryCode WHERE country.Continent = \"" + continentName +"\" GROUP BY country.Continent;";

            // Storing the results in a ResultSet object, continentPopulationResult
            ResultSet continentPopulation = stmt.executeQuery(sqlQueryContinentPopulation);

            // Creating an arraylist of population objects to be stored and returned from the method
            ArrayList<Population> populations = new ArrayList<Population>();

            // Retrieving the results from ResultSet object, WorldPopulationResult as long as there is data left
            while(continentPopulation.next()) {

                // Creating a Population object to be stored in arraylist
                Population population = new Population();

                // setting the attributes of population object with Setter
                population.setName(continentPopulation.getString(1));
                population.setTotalPopulation(continentPopulation.getLong(2));
                population.setYesCityPercent(continentPopulation.getString(3));
                population.setNoCityPercent(continentPopulation.getString(4));

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
     * Printing a continent population from the world database
     * @param populations arraylist of population objects.
     */
    public static void printResult(String continentName, ArrayList<Population> populations){
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
        for(int i = 0; i<= populations.size()-1; i++){
            if (populations.get(i) == null){
                System.out.println("The populations ArrayList contains null value.");
                return;
            }
        }

        // Printing out the headers of the report table.
        System.out.println("-------------------------------Continent Population-----------------------------------");
        System.out.println("| Continent: " + continentName + "                                                         ORDER: Largest to Smallest Population");
        System.out.printf("| %-5s | %-40s | %-25s | %-25s | %-25s | %n", "No", "Continent Name", "Total Population", "City Percentage", "Not City Percentage");
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
