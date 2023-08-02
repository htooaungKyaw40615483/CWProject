package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;

/*
 * Purpose: To Retrieve All The Cities In A District
 */
public class AllCitiesInDistrict {
    /**
     * Return a district's cities from the world database
     *
     * @param districtName Predefined District Name
     * @param con          Established Database Connection
     * @return the City Objects in an ArrayList which is from a single District.
     */
    private Connection con; // Class member variable to store the database connection

    // Constructor to initialize the database connection
    public AllCitiesInDistrict(Connection con) {
        this.con = con;
    }
    public static ArrayList<City> returnCity(String districtName, Connection con) {
        // Checking if the district name is entered.
        if (districtName == null) {
            System.out.println("The district name is not defined.");
            return new ArrayList<>();
        }

        try {
            // Creating Statement Object to execute Query
            Statement stmt = con.createStatement();

            /*
             Defining the Query to be executed.
             QUERY: To SELECT CityName, CountryName, DistrictName, Population of a District
             after JOINing two tables with country code ORDERED by population in descending.
            */
            String sqlQueryCityInDistrict = "SELECT world.city.Name, world.country.Name, world.city.District, world.city.Population FROM world.city " +
                    "INNER JOIN world.country ON world.city.CountryCode = world.country.Code " +
                    "WHERE world.city.District = \"" + districtName + "\" " +
                    "ORDER BY world.city.Population DESC;";

            // Storing the results in a ResultSet object, cityInCountryResult
            ResultSet cityInDistrictResult = stmt.executeQuery(sqlQueryCityInDistrict);

            // Creating an arraylist of city objects to be stored and returned from the method
            ArrayList<City> cities = new ArrayList<>();
            // Creating a City object to be stored in the arraylist
            City city = new City();
            // Retrieving the results from ResultSet object, cityInCountryResult as long as there is data left
            while (cityInDistrictResult.next()) {

                // setting the attributes of city object with Setter
                city.setCityName(cityInDistrictResult.getString(1));
                city.setCountryName(cityInDistrictResult.getString(2));
                city.setDistrictName(cityInDistrictResult.getString(3));
                city.setCityPopulation(cityInDistrictResult.getInt(4));

                // adding the city object to the arraylist
                cities.add(city);
            }
            if (cities.isEmpty()) {
                return null;
            }
            return cities;
        }
        /*
         Catching the error if there is
         Printing the error and returning an empty list
        */
        catch (SQLException e) {
            System.out.println("Failed to get city populations: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Printing a district's cities from the world database
     *
     * @param cities       arraylist of city objects.
     * @param districtName predefined district name.
     */
    public static void printResult(String districtName, ArrayList<City> cities) {
        // Check if district name is null or cities arraylist is null or empty
        if (districtName == null || cities == null || cities.isEmpty()) {
            System.out.println("There are no cities or the district name is not defined.");
            return;
        }

        // Printing out the headers of the report table.
        System.out.println("-------------------------------------------All Cities in A District by Largest Population to Smallest------------------------------------------");
        System.out.println("| District: " + districtName + "                                                                                       Total Cities: " + cities.size());
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-35s | %-35s | %-35s | %-21s | %n", "Name", "Country", "District", "Population");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");

        int i = 1;

        // For all the objects in cities arraylist, formatting and printing the values (Strings and Digits)
        for (City city : cities) {
            //Printing the city object's attributes with Getter.
            System.out.printf("| %,2d | %-30s | %-35s | %-34s  | %,20d  |  %n", i++, city.getCityName(), city.getCountryName(), city.getDistrictName(), city.getCityPopulation());
        }
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");

    }
}
