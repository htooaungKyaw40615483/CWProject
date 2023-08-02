package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;

public class AllCitiesInCountry {
    private Connection con; // Class member variable to store the database connection

    // Constructor to initialize the database connection
    public AllCitiesInCountry(Connection con) {
        this.con = con; // Initialize the database connection in the class constructor
    }

    /**
     * Return a country's cities from the world database
     *
     * @param countryName Predefined Country Name
     * @param con         Database Connection
     * @return the City Objects in an ArrayList which is from a single country.
     */
    public static ArrayList<City> returnCity(String countryName, Connection con) {
        // Checking if the country name is entered.
        if (countryName == null) {
            System.out.println("The Country name is not defined.");
            return null; // Return null if the country name is not defined
        }

        // Checking if the connection has been established.
        if (con == null) {
            System.out.println("The connection has not been established");
            return null; // Return null if the database connection is not established
        }

        try {
            // Creating PreparedStatement to avoid SQL injection
            String sqlQueryCityInCountry = "SELECT world.city.Name, world.country.Name, world.city.District, world.city.Population FROM world.city " +
                    "INNER JOIN world.country ON world.city.CountryCode = world.country.Code " +
                    "WHERE world.country.Name= ? " +
                    "ORDER BY world.city.Population DESC;";

            // Creating PreparedStatement object
            PreparedStatement pstmt = con.prepareStatement(sqlQueryCityInCountry);

            // Binding the parameter countryName to the PreparedStatement
            pstmt.setString(1, countryName);

            // Executing the query and storing the results in a ResultSet object
            ResultSet cityInCountryResult = pstmt.executeQuery();

            // Creating an arraylist of city objects to be stored and returned from the method
            ArrayList<City> cities = new ArrayList<>();
            // Creating a City object to be stored in the arraylist
            City city = new City();
            // Retrieving the results from ResultSet object, cityInCountryResult as long as there is data left
            while (cityInCountryResult.next()) {


                // setting the attributes of the city object with Setter
                city.setCityName(cityInCountryResult.getString(1));
                city.setCountryName(cityInCountryResult.getString(2));
                city.setDistrictName(cityInCountryResult.getString(3));
                city.setCityPopulation(cityInCountryResult.getInt(4));
                // adding the city object to the arraylist
                cities.add(city);
            }

            // If no cities were found, return null instead of an empty ArrayList
            if (cities.isEmpty()) {
                return null;
            }

            return cities; // Return the arraylist of city objects
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city populations");
            return null; // Return null if there is an exception
        }
    }
    /**
     * Printing a country's cities from the world database
     * @param cities arraylist of city objects.
     */
    public static void printResult(String countryName, ArrayList<City> cities) {
        // Check if the country name AND cities is null. If not, move on to the next condition.
        if (countryName == null && cities == null) {
            System.out.println("There are no cities or a defined country name");
            return;
        }

        // Check if cities arraylist is null. If not, move on to the next condition.
        if (cities == null) {
            System.out.println("There are no cities");
            return;
        }

        // Check if country name is null. If not, move on to the next condition.
        if (countryName == null) {
            System.out.println("The country name is not defined");
            return;
        }

        // Printing out the headers of the report table.
        System.out.println("-------------------------------------------All Cities in A Country by Largest Population to Smallest-------------------------------------------");
        System.out.println("| Country: " + countryName + "                                                                                         Total Cities: " + cities.size());
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-35s | %-40s | %-35s | %-20s | %n", "Name", "Country", "District", "Population");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");

        // For all the objects in cities arraylist, formatting and printing the values (Strings and Digits)
        for (City city : cities) {

            // Printing the city object's attributes with Getter.
            System.out.printf("| %-35s | %-40s | %-35s | %,20d | %n", city.getCityName(), city.getCountryName(), city.getDistrictName(), city.getCityPopulation());
        }
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");
    }
}
