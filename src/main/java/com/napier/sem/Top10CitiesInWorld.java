package com.napier.sem;
import java.sql.*;
import java.util.ArrayList;

/*
 * Purpose: To Retrieve Top 10 Cities In The World
 */
public class Top10CitiesInWorld {
    /**
     * Return a country's cities from the world database
     * @param con Established Database Connection
     * @return the City Objects in an ArrayList
     */
    public static ArrayList<City> ReturnCity(Connection con){
        try{
            // Creating Statement Object to execute Query
            Statement stmt = con.createStatement();

            /*
             Defining the Query to be executed.
             QUERY: To SELECT CityName, CountryName, DistrictName, Population of a Country
             after JOINing two tables with country code ORDERED by population in descending.
            */
            String sqlQueryCityInCountry =
                    "SELECT world.city.Name, world.country.Name, world.city.District, world.city.Population " +
                            "FROM world.city INNER JOIN world.country ON world.city.CountryCode = world.country.Code " +
                            "ORDER BY world.city.Population DESC LIMIT 10;";

            // Storing the results in a ResultSet object, cityInCountryResult
            ResultSet cityInCountryResult = stmt.executeQuery(sqlQueryCityInCountry);

            // Creating an arraylist of city objects to be stored and returned from the method
            ArrayList<City> Cities = new ArrayList<City>();

            // Retrieving the results from ResultSet object, cityInCountryResult as long as there is data left
            while(cityInCountryResult.next()) {

                // Creating a City object to be stored in arraylist
                City city = new City();

                // setting the attributes of city object with Setter
                city.setCity_name(cityInCountryResult.getString(1));
                city.setCountry_name(cityInCountryResult.getString(2));
                city.setDistrict_name(cityInCountryResult.getString(3));
                city.setCity_population(cityInCountryResult.getInt(4));

                // adding the city object to the arraylist
                Cities.add(city);
            }
            return Cities;
        }
        /*
         Catching the error if there is
         Printing the error and returning null
        */
        catch(Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city populations");
            return null;
        }
    }

    /**
     * Printing a country's cities from the world database
     * @param cities arraylist of city objects.
     */
    public static void printResult(String cn, ArrayList<City> cities){
        System.out.println("-------------------------------------------Top 10 Populated Cities in The World-------------------------------------------");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-35s | %-40s | %-35s | %-20s | %n", "Name", "Country", "District", "Population");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");

        // For all the objects in cities arraylist, formatting and printing the values (Strings and Digits)
        for (City city :cities){

            // Printing the city object's attributes with Getter.
            System.out.printf("| %-35s | %-40s | %-35s | %,20d | %n", city.getCityName(), city.getCountryName(), city.getDistrictName(), city.getCityPopulation());
        }
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");

    }
}
