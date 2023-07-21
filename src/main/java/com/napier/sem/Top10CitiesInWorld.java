package com.napier.sem;
import java.sql.*;
import java.util.ArrayList;

/*
 * Purpose: To Retrieve Top 10 Cities In The World
 */
public class Top10CitiesInWorld {
    /**
     * Return top 10 populated cities from the world database
     * @param con Established Database Connection
     * @return the City Objects in an ArrayList
     */
    public static ArrayList<City> returnCity(Connection con){
        try{
            // Creating Statement Object to execute Query
            Statement stmt = con.createStatement();

            /*
             Defining the Query to be executed.
             QUERY: To SELECT CityName, CountryName, DistrictName, Population of a City
             after JOINing two tables with country code ORDERED by population in descending.
            */
            String sqlQueryTop10CityInWorld =
                    "SELECT world.city.Name, world.country.Name, world.city.District, world.city.Population " +
                            "FROM world.city INNER JOIN world.country ON world.city.CountryCode = world.country.Code " +
                            "ORDER BY world.city.Population DESC LIMIT 10;";

            // Storing the results in a ResultSet object, top10CityInWorldResult
            ResultSet top10CityInWorldResult = stmt.executeQuery(sqlQueryTop10CityInWorld);

            // Creating an arraylist of city objects to be stored and returned from the method
            ArrayList<City> cities = new ArrayList<City>();

            // Retrieving the results from ResultSet object, top10CityInWorldResult as long as there is data left
            while(top10CityInWorldResult.next()) {

                // Creating a City object to be stored in arraylist
                City city = new City();

                // setting the attributes of city object with Setter
                city.setCityName(top10CityInWorldResult.getString(1));
                city.setCountryName(top10CityInWorldResult.getString(2));
                city.setDistrictName(top10CityInWorldResult.getString(3));
                city.setCityPopulation(top10CityInWorldResult.getInt(4));

                // adding the city object to the arraylist
                cities.add(city);
            }
            return cities;
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
     * Printing Top 10 populated cities from the world database
     * @param cities arraylist of city objects.
     */
    public static void printResult(ArrayList<City> cities){
        System.out.println("------------------------------------------------------Top 10 Populated Cities in The World-----------------------------------------------------");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-35s | %-35s | %-35s | %-21s | %n", "Name", "Country", "District", "Population");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");

        int  i = 1;

        // For all the objects in cities arraylist, formatting and printing the values (Strings and Digits)
        for (City city :cities){

            // Printing the city object's attributes with Getter.
            System.out.printf("| %,2d | %-30s | %-35s | %-34s  | %,20d  |  %n", i++,  city.getCityName(), city.getCountryName(), city.getDistrictName(), city.getCityPopulation());
        }
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");

    }
}
