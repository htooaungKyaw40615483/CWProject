package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Top10CitiesInCountry {
    /**
     * Returns the top 10 populated cities in a country from the world database.
     *
     * @param countryName The name of the country.
     * @param con         Established database connection.
     * @return An ArrayList of City objects representing the top 10 populated cities in the country.
     */
    public static ArrayList<City> returnCitiesInCountry(String countryName, Connection con) {
        if (con == null && countryName == null){
            System.out.println("The country name is empty and connection has not been established.");
            return null;
        }

        //Checking if the country name is entered.
        if (countryName == null){
            System.out.println("The Country name is not defined.");
            return null;
        }

        // Checking if the connection has been established.
        if (con == null){
            System.out.println("The connection has not been established");
            return null;
        }
        try {
            // Creating a Statement object to execute the query
            Statement stmt = con.createStatement();

            /*
             * Defining the query to be executed.
             * QUERY: SELECT CityName, CountryName, DistrictName, Population of a city
             *        WHERE CountryName equals the specified country name
             *        ORDER BY population in descending order with a limit of 10
             */
            String sqlQueryTop10CitiesInCountry = "SELECT world.city.Name, world.country.Name, world.city.District, world.city.Population FROM world.city " +
                    "INNER JOIN world.country ON world.city.CountryCode = world.country.Code " +
                    "WHERE world.country.Name = \"" + countryName + "\" " +
                    "ORDER BY world.city.Population DESC LIMIT 10;";

            // Storing the results in a ResultSet object
            ResultSet top10CitiesInCountryResult = stmt.executeQuery(sqlQueryTop10CitiesInCountry);

            // Creating an ArrayList of City objects to store and return the results
            ArrayList<City> cities = new ArrayList<>();

            // Retrieving the results from the ResultSet object as long as there is data left
            while (top10CitiesInCountryResult.next()) {
                // Creating a City object to be stored in the ArrayList
                City city = new City();

                // Setting the attributes of the City object
                city.setCityName(top10CitiesInCountryResult.getString(1));
                city.setCountryName(top10CitiesInCountryResult.getString(2));
                city.setDistrictName(top10CitiesInCountryResult.getString(3));
                city.setCityPopulation(top10CitiesInCountryResult.getInt(4));

                // Adding the City object to the ArrayList
                cities.add(city);
            }

            return cities;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city populations");
            return null;
        }
    }

    /**
     * Prints the top 10 populated cities in a country from the world database.
     *
     * @param countryName The name of the country.
     * @param cities      An ArrayList of City objects representing the top 10 populated cities in the country.
     */
    public static void printResult(String countryName, ArrayList<City> cities) {
        // Check if the country name AND cities is null. If not, move on to the next condition.
        if(countryName == null && cities == null){
            System.out.println("There is no cities or defined country name");
            return;
        }

        // Check if cities arraylist is null. If not, move on to the next condition.
        if (cities == null) {
            System.out.println("There is no cities");
            return;
        }

        // Check if country name is null. If not, move on to the next condition.
        if(countryName == null){
            System.out.println("The country name is not defined");
            return;
        }
        System.out.println("-----------------------------------Top 10 most populated Cities in a Country by Largest Population to Smallest-----------------------------");
        System.out.println("| Country: " + countryName + "                                                                               ORDER: Largest to Smallest Population");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-2s | %-30s | %-35s | %-34s | %-20s | %n", "No", "Name", "Country", "District", "Population");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------");

        int  i = 1;

        // For all the objects in cities arraylist, formatting and printing the values (Strings and Digits)
        for (City city :cities){

            // Printing the city object's attributes with Getter.
            System.out.printf("| %,2d | %-30s | %-35s | %-34s  | %,20d  | %n", i++,  city.getCityName(), city.getCountryName(), city.getDistrictName(), city.getCityPopulation());
        }
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------");
    }
}
