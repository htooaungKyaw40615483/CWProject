package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class AllCitiesInContinent {
    /**
     * Return a continent's cities from the world database
     * @param continentName Predefined continent Name
     * @param con Established Database Connection
     * @return the City Objects in an ArrayList which is from a single continent.
     */

    public static ArrayList<City> returnCity(String continentName, Connection con){
        if (continentName == null){
            System.out.println("The continent name is not defined.");
        }
        try{
            Statement stmt = con.createStatement();
            String sqlQueryCityInContinent = "SELECT world.city.Name, world.country.Name, world.city.District, world.city.Population FROM world.city " +
                    "INNER JOIN world.country ON world.city.CountryCode = world.country.Code " +
                    "WHERE world.country.Continent= \"" + continentName + "\" " +
                    "ORDER BY world.city.Population DESC;";
            ResultSet cityInContinentResult = stmt.executeQuery(sqlQueryCityInContinent);
            ArrayList<City> cities = new ArrayList<City>();
            while(cityInContinentResult.next()) {
                City city = new City();
                city.setCityName(cityInContinentResult.getString(1));
                city.setCountryName(cityInContinentResult.getString(2));
                city.setDistrictName(cityInContinentResult.getString(3));
                city.setCityPopulation(cityInContinentResult.getInt(4));
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
     * Printing a continent's cities from the world database
     * @param cities arraylist of city objects.
     */
    public static void printResult(String continentName, ArrayList<City> cities){
        // Check if the continent name AND cities is null. If not, move on to the next condition.
        if(continentName == null && cities == null){
            System.out.println("There is no cities or defined continent name");
            return;
        }

        // Check if cities arraylist is null. If not, move on to the next condition.
        if (cities == null) {
            System.out.println("There is no cities");
            return;
        }

        // Check if district name is null. If not, move on to the next condition.
        if(continentName == null){
            System.out.println("The continent name is not defined");
            return;
        }
        System.out.println("-------------------------------------------All Cities in A Continent by Largest Population to Smallest--------------------------------------------");
        System.out.println("| Continent: " + continentName + "                                                                               Total Cities: " + cities.size());
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-5s | %-35s | %-37s | %-32s | %-21s | %n", "No", "Name", "Country", "District", "Population");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------");

        int  i = 1;

        // For all the objects in cities arraylist, formatting and printing the values (Strings and Digits)
        for (City city :cities){
            // Printing the city object's attributes with Getter.
            System.out.printf("| %,5d | %-35s | %-37s | %-31s  | %,20d  |  %n", i++,  city.getCityName(), city.getCountryName(), city.getDistrictName(), city.getCityPopulation());
        }
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------");

    }
}