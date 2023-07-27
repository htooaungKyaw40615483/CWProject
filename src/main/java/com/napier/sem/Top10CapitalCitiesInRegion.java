package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Top10CapitalCitiesInRegion {
    /**
     * Return a top 10 populated capital cities of region from the world database
     * @param regionName Predefined Region Name
     * @param con Established Database Connection
     * @return the capital Objects in an ArrayList which is from the world.
     */
    public static ArrayList<Capital> returnCapital(String regionName, Connection con) {
        // Check if the region name is null.
        if (regionName == null){
            System.out.println("The Region name is not defined.");
        }
        try {
            // Creating SQL Statement
            Statement stmt = con.createStatement();


            /*
             Defining the Query to be executed.
             QUERY: To SELECT Capital Name, Country Name, Population after JOINing two tables with City ID ORDERED by population in descending .
            */

            String sqlQueryTop10CapitalCitiesInRegion = "SELECT city.Name, country.Name , city.Population FROM city\n" +
                    "JOIN country ON city.ID = country.Capital WHERE country.Region = \"" + regionName + "\"ORDER BY city.Population DESC LIMIT 10;";

            // Storing the results in a ResultSet object, Top10CapitalCitiesInRegionResult
            ResultSet Top10CapitalCitiesInRegionResult = stmt.executeQuery(sqlQueryTop10CapitalCitiesInRegion);

            // Creating an arraylist of capitals objects to be stored and returned from the method
            ArrayList<Capital> capitals = new ArrayList<>();

            // Retrieving the results from ResultSet object, Top10CapitalCitiesInRegionResult as long as there is data left
            while (Top10CapitalCitiesInRegionResult.next()) {

                //Creating a capital object to be stored in arraylist
                Capital capital = new Capital();

                // setting the attributes of capital object with Setter
                capital.setCapitalName(Top10CapitalCitiesInRegionResult.getString(1));
                capital.setCountry(Top10CapitalCitiesInRegionResult.getString(2));
                capital.setCapitalPopulation(Top10CapitalCitiesInRegionResult.getInt(3));

                //adding the capital object to the arraylist
                capitals.add(capital);
            }
            return capitals;
        /*
         Catching the error if there is
         Printing the error and returning null
        */

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get capital cities population");
            return null;
        }
    }
    /**
     * Printing a population of top 10 capital cities  in the region from the world database
     * @param capitals arraylist of capital objects.
     */
    public static void printResult(String regionName, ArrayList<Capital> capitals){
        // Check if capitals arraylist is null. If not, move on to the next condition.
        if(regionName == null && capitals == null){
            System.out.println("There is no capital or defined region name");
            return;
        }
        if (capitals == null) {
            System.out.println("There is no capital");
            return;
        }
        // Check if district name is null. If not, move on to the next condition.
        if(regionName == null){
            System.out.println("The region name is not defined");
            return;
        }

        // Checking if the arraylist of capitals is initialized but empty.
        if (capitals.isEmpty()){
            System.out.print("The capital ArrayList is empty.");
            return;
        }

        // Checking if the element of arraylist is null
        for(int i = 0; i<= capitals.size()-1; i++){
            if (capitals.get(i) == null){
                System.out.println("The capitals ArrayList contains null value.");
                return;
            }
        }

        // Printing out the headers of the report table.
        System.out.println("---------------------------------------Top 10 Populated Capital Cities in The Region--------------------------------------------");
        System.out.println("| Region: " + regionName + "                                                                    Total Cities: " + capitals.size());
        System.out.printf("| %-5s | %-40s | %-40s | %-30s | %n", "No", "Capital Name", "Country Name", "Population");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------");

        // Initializing the variable to be shown as row number.
        int  i = 1;

        // For all the objects in capitals arraylist, formatting and printing the values (Strings and Digits)
        for (Capital capital :capitals){
            // Printing the capital object's attributes with Getter.
            System.out.printf("| %,5d | %-40s | %-40s | %,30d | %n", i++, capital.getCapitalName(), capital.getCountry(), capital.getCapitalPopulation());
        }
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
    }
}