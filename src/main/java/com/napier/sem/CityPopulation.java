package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class CityPopulation {
    /**
     * Return population of city from the world database
     * @param con Established Database Connection
     * @return the Population Objects in an ArrayList which is from a world.
     */
    public static ArrayList<Population> returnPopulation(String cityName, Connection con){
        if (cityName == null){
            System.out.println("The City name is not defined.");
        }
        try{
            // Creating Statement Object to execute Query
            Statement stmt = con.createStatement();

            /*
             Defining the Query to be executed.
             QUERY: city Population
            */
            String sqlQueryCityPopulation = "SELECT city.Name, SUM(city.Population) AS Total_Population FROM city WHERE city.Name='" + cityName + "'";

            // Storing the results in a ResultSet object, cityPopulationResult
            ResultSet cityPopulation = stmt.executeQuery(sqlQueryCityPopulation);

            // Creating an arraylist of population objects to be stored and returned from the method
            ArrayList<Population> populations = new ArrayList<Population>();
            // Creating a Population object to be stored in arraylist
            Population population = new Population();
            // Retrieving the results from ResultSet object, RegionPopulationResult as long as there is data left
            while(cityPopulation.next()) {




                // setting the attributes of population object with Setter
                population.setName(cityPopulation.getString(1));
                population.setTotalPopulation(cityPopulation.getLong(2));


                // adding the population object to the arraylist
                populations.add(population);
            }
            if (populations.isEmpty()) {
                return null;
            }
            return populations;
        }
         /*
         Catching the error if there is
         Printing the error and returning null
        */
        catch(Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City Population");
            return null;
        }
    }
    /**
     * Printing a city population from the world database
     * @param populations arraylist of population objects.
     */
    public static void printResult(String cityName, ArrayList<Population> populations){
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
        System.out.println("-------------------------City Population----------------------------------------");
        System.out.println("| City: " + cityName + "                                               ");
        System.out.printf("| %-5s | %-40s | %-25s | %n", "No", "City Name", "Total Population");
        System.out.println("-------------------------------------------------------------------------------");

        // Initializing the variable to be shown as row number.
        int  i = 1;

        // For all the objects in populations arraylist, formatting and printing the values (Strings and Digits)
        for (Population population :populations){
            // Printing the population object's attributes with Getter.
            System.out.printf("| %-5s | %-40s | %,25d | %n", i++, population.getName(), population.getTotalPopulation());
        }
        System.out.println("--------------------------------------------------------------------------------");
    }

}
