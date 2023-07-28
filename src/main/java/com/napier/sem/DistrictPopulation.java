package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DistrictPopulation {
    /**
     * Return population of region from the world database
     * @param con Established Database Connection
     * @return the Population Objects in an ArrayList which is from a world.
     */
    public static ArrayList<Population> returnPopulation(String districtName, Connection con){
        if (districtName == null){
            System.out.println("The District name is not defined.");
        }
        try{
            // Creating Statement Object to execute Query
            Statement stmt = con.createStatement();

            /*
             Defining the Query to be executed.
             QUERY: district Population
            */
            String sqlQueryDistrictPopulation = "SELECT SUM(city.Population) AS Total_Population FROM city WHERE city.District='" + districtName + "'";

            // Storing the results in a ResultSet object, districtPopulationResult
            ResultSet districtPopulation = stmt.executeQuery(sqlQueryDistrictPopulation);

            // Creating an arraylist of population objects to be stored and returned from the method
            ArrayList<Population> populations = new ArrayList<Population>();

            // Retrieving the results from ResultSet object, DistrictPopulationResult as long as there is data left
            while(districtPopulation.next()) {

                // Creating a Population object to be stored in arraylist
                Population population = new Population();

                // setting the attributes of population object with Setter
                population.setTotalPopulation(districtPopulation.getLong(1));

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
            System.out.println("Failed to get District Population");
            return null;
        }
    }
    /**
     * Printing a district population from the world database
     * @param populations arraylist of population objects.
     */
    public static void printResult(String districtName, ArrayList<Population> populations){
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
        System.out.println("-------------------------District Population-----------------------------------");
        System.out.println("| District: " + districtName + "                                               ");
        System.out.printf("| %-5s | %-25s | %n", "No", "Total Population");
        System.out.println("-------------------------------------------------------------------------------");

        // Initializing the variable to be shown as row number.
        int  i = 1;

        // For all the objects in populations arraylist, formatting and printing the values (Strings and Digits)
        for (Population population :populations){
            // Printing the population object's attributes with Getter.
            System.out.printf("| %-5s| %,25d | %n", i++, population.getTotalPopulation());
        }
        System.out.println("--------------------------------------------------------------------------------");
    }
}
