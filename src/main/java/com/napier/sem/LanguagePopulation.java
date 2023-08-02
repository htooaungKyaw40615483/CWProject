package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/*
 * Purpose: To population of spoken languages in percentage
 */
public class LanguagePopulation {
    /**
     * Return population of spoken languages from the world database
     * @param con Established Database Connection
     * @return the population Objects in an ArrayList which is from a world.
     */
    public static ArrayList<Population> returnPopulation(Connection con){
        try{
            // Creating Statement Object to execute Query
            Statement stmt = con.createStatement();

            /*
             Defining the Query to be executed.
             QUERY: To SELECT language name, total population , population percent
             after JOINing two tables with country and group by language and order by descending.
            */
            String sqlQueryLanguagePopulationPercent = "SELECT countrylanguage.Language, \n" +
                    "ROUND(SUM((country.Population * countrylanguage.Percentage) / 100), 0) AS Total_Population,\n" +
                    "CONCAT(ROUND((SUM((country.Population * countrylanguage.Percentage) / 100) / (SELECT SUM(Population) FROM country)) * 100, 2), '%') AS Population_Percentage\n" +
                    "FROM countrylanguage JOIN country ON country.Code = countrylanguage.CountryCode WHERE countrylanguage.Language " +
                    "IN ('Chinese', 'English','Hindi', 'Spanish', 'Arabic') GROUP BY countrylanguage.Language  \n" +
                    "ORDER BY `Total_Population` DESC;";

            // Storing the results in a ResultSet object, LanguagePopulationPercentResult
            ResultSet languagePopulationPercent = stmt.executeQuery(sqlQueryLanguagePopulationPercent);

            // Creating an arraylist of population objects to be stored and returned from the method
            ArrayList<Population> populations = new ArrayList<Population>();
            // Creating a Population object to be stored in arraylist
            Population population = new Population();
            // Retrieving the results from ResultSet object, LanguagePopulationResult as long as there is data left
            while(languagePopulationPercent.next()) {




                // setting the attributes of population object with Setter
                population.setName(languagePopulationPercent.getString(1));
                population.setTotalPopulation(languagePopulationPercent.getLong(2));
                population.setYesCityPercent(languagePopulationPercent.getString(3));

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
            System.out.println("Failed to get language population of the world");
            return null;
        }
    }
    /**
     * Printing a language population from the world database
     * @param populations arraylist of population objects.
     */
    public static void printResult(ArrayList<Population> populations){
        // Check if populations arraylist is null. If not, move on to the next condition.
        if (populations == null) {
            System.out.println("There is no language population");
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
        System.out.println("------------------------------Population of Spoken Languages By Largest To Smallest---------------------------------------------");
        System.out.printf("| %-5s | %-40s | %-40s | %-30s | %n", "No", "Language Name", "Total Population", "Population in Percent");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------");

        // Initializing the variable to be shown as row number.
        int  i = 1;

        // For all the objects in populations arraylist, formatting and printing the values (Strings and Digits)
        for (Population population :populations){
            // Printing the population object's attributes with Getter.
            System.out.printf("| %,5d | %-40s | %,40d | %-30s | %n", i++, population.getName(), population.getTotalPopulation(), population.getYesCityPercent());
        }
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
    }

}
