package com.napier.sem;

/*
 * Purpose: To Retrieve All The Capital Cities In the world
 */

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class CapitalCitiesInWorld {
    /**
     * Return a world capital cities from the world database
     * @param con Established Database Connection
     * @return the capital Objects in an ArrayList which is from the world.
     */
    public static ArrayList<Capital> returnCapital(Connection con) {
        try {
            // Creating SQL Statement
            Statement stmt = con.createStatement();

            /*
             Defining the Query to be executed.
             QUERY: To SELECT Capital Name, Country Name, Population after JOINing two tables with City ID ORDERED by population in descending.
            */

            String sqlQueryWorldCapitalCity = "SELECT city.Name, country.Name , city.Population FROM city\n" +
                    "JOIN country ON city.ID = country.Capital\n" +
                    "ORDER BY city.Population DESC;";

            // Storing the results in a ResultSet object, WorldCapitalCitiesResult
            ResultSet worldCapitalCity = stmt.executeQuery(sqlQueryWorldCapitalCity);

            // Creating an arraylist of capitals objects to be stored and returned from the method
            ArrayList<Capital> capitals = new ArrayList<>();
            //Creating a capital object to be stored in arraylist
            Capital capital = new Capital();
            // Retrieving the results from ResultSet object, WorldCapitalCitiesResult as long as there is data left
            while (worldCapitalCity.next()) {




                // setting the attributes of capital object with Setter
                capital.setCapitalName(worldCapitalCity.getString(1));
                capital.setCountry(worldCapitalCity.getString(2));
                capital.setCapitalPopulation(worldCapitalCity.getInt(3));

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
     * Printing a capital cities population in the world from the world database
     * @param capitals arraylist of capital objects.
     */
    public static void printResult(ArrayList<Capital> capitals){
        // Check if capitals arraylist is null. If not, move on to the next condition.
        if (capitals == null) {
            System.out.println("There is no captial");
            return;
        }

        // Checking if the arraylist of capitals is initialized but empty.
        if (capitals.isEmpty()){
            System.out.print("The capital ArrayList is empty.");
            return;
        }

        // Checking if the element of arraylist is null
        for (Capital capital : capitals) {
            if (capital == null) {
                System.out.println("The capitals ArrayList contains null value.");
                return;
            }
        }


        // Printing out the headers of the report table.
        System.out.println("----------------------------------All Capital Cities in the World By Largest Population To Smallest-----------------------------");
        System.out.println("Total Capital: " + capitals.size() + " -------------------------------------------------------------------------------------------------------------");
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
