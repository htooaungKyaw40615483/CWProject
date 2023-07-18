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
    public static ArrayList<City> ReturnCitiesInCountry(String countryName, Connection con) {
        try {
            // Creating a Statement object to execute the query
            Statement stmt = con.createStatement();

            /*
             * Defining the query to be executed.
             * QUERY: SELECT CityName, CountryName, DistrictName, Population of a city
             *        WHERE CountryName equals the specified country name
             *        ORDER BY population in descending order with a limit of 10
             */
            String sqlQueryCitiesInCountry = "SELECT world.city.Name, world.country.Name, world.city.District, world.city.Population FROM world.city " +
                    "INNER JOIN world.country ON world.city.CountryCode = world.country.Code " +
                    "WHERE world.country.Name = \"" + countryName + "\" " +
                    "ORDER BY world.city.Population DESC LIMIT 10;";

            // Storing the results in a ResultSet object
            ResultSet citiesInCountryResult = stmt.executeQuery(sqlQueryCitiesInCountry);

            // Creating an ArrayList of City objects to store and return the results
            ArrayList<City> cities = new ArrayList<>();

            // Retrieving the results from the ResultSet object as long as there is data left
            while (citiesInCountryResult.next()) {
                // Creating a City object to be stored in the ArrayList
                City city = new City();

                // Setting the attributes of the City object
                city.setCity_name(citiesInCountryResult.getString(1));
                city.setCountry_name(citiesInCountryResult.getString(2));
                city.setDistrict_name(citiesInCountryResult.getString(3));
                city.setCity_population(citiesInCountryResult.getInt(4));

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
        System.out.println("-----------------------------------Top 10 most populated Cities in a Country by Largest Population to Smallest-----------------------------------");
        System.out.println("| Country: " + countryName + "                                                                                   ORDER: Largest to Smallest Population|");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-35s | %-40s | %-35s | %-20s | %n", "Name", "Country", "District", "Population");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");

        // For all the objects in the cities ArrayList, format and print the values (Strings and Digits)
        for (City city : cities) {
            // Printing the City object's attributes
            System.out.printf("| %-35s | %-40s | %-35s | %,20d | %n", city.getCityName(), city.getCountryName(), city.getDistrictName(), city.getCityPopulation());
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
    }
}
