package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Top10CitiesInDistrict {
    /**
     * Return a list of top 10 populated cities in a district from the world database
     * @param districtName The name of the district
     * @param con Established Database Connection
     * @return An ArrayList of City objects in the specified district
     */
    public static ArrayList<City> returnCitiesInDistrict(String districtName, Connection con){
        //Checking if the district name is entered.
        if (districtName == null){
            System.out.println("The district name is not defined.");
        }
        try{
            // Creating Statement Object to execute the query
            Statement stmt = con.createStatement();

            /*
             * Defining the query to be executed.
             * QUERY: SELECT CityName, CountryName, DistrictName, Population of a city
             *        WHERE DistrictName equals the specified district name
             *        ORDER BY population in descending order with a limit of 10
             */
            String sqlQueryTop10CitiesInDistrict = "SELECT world.city.Name, world.country.Name, world.city.District, world.city.Population FROM world.city " +
                    "INNER JOIN world.country ON world.city.CountryCode = world.country.Code " +
                    "WHERE world.city.District = \"" + districtName + "\" " +
                    "ORDER BY world.city.Population DESC LIMIT 10;";

            // Storing the results in a ResultSet object
            ResultSet top10citiesInDistrictResult = stmt.executeQuery(sqlQueryTop10CitiesInDistrict);

            // Creating an ArrayList of City objects to store and return the results
            ArrayList<City> cities = new ArrayList<>();
            // Creating a City object to be stored in the ArrayList
            City city = new City();
            // Retrieving the results from the ResultSet object as long as there is data left
            while (top10citiesInDistrictResult.next()) {



                // Setting the attributes of the City object
                city.setCityName(top10citiesInDistrictResult.getString(1));
                city.setCountryName(top10citiesInDistrictResult.getString(2));
                city.setDistrictName(top10citiesInDistrictResult.getString(3));
                city.setCityPopulation(top10citiesInDistrictResult.getInt(4));

                // Adding the City object to the ArrayList
                cities.add(city);
            }
            if (cities.isEmpty()) {
                return null;
            }
            return cities;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city populations");
            return null;
        }
    }

    /**
     * Prints the top 10 populated cities in a district from the world database
     * @param districtName The name of the district
     * @param cities An ArrayList of City objects
     */
    public static void printResult(String districtName, ArrayList<City> cities){
        // Check if the district name AND cities is null. If not, move on to the next condition.
        if(districtName == null && cities == null){
            System.out.println("There is no cities or defined district name");
            return;
        }

        // Check if cities arraylist is null. If not, move on to the next condition.
        if (cities == null) {
            System.out.println("There is no cities");
            return;
        }

        // Check if district name is null. If not, move on to the next condition.
        if(districtName == null){
            System.out.println("The district name is not defined");
            return;
        }

        System.out.println("--------------------------------------Top 10 most populated Cities in a District by Largest Population to Smallest-------------------------");
        System.out.println("| District: " + districtName + "                                                                                ORDER: Largest to Smallest Population");
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
