package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;



public class AllCitiesInRegion {
    /**
     * Return a region's cities from the world database
     * @param regionName Predefined region Name
     * @param con Established Database Connection
     * @return the City Objects in an ArrayList which is from a single region.
     */
    public static ArrayList<City> returnCity(String regionName, Connection con){
        if (regionName == null){
            System.out.println("The region name is not defined.");
        }
        try{
            Statement stmt = con.createStatement();
            String sqlQueryCityInRegion = "SELECT world.city.Name, world.country.Name, world.city.District, world.city.Population FROM world.city " +
                    "INNER JOIN world.country ON world.city.CountryCode = world.country.Code " +
                    "WHERE world.country.Region= \"" + regionName + "\" " +
                    "ORDER BY world.city.Population DESC;";
            ResultSet cityInRegionResult = stmt.executeQuery(sqlQueryCityInRegion);
            ArrayList<City> cities = new ArrayList<City>();
            while(cityInRegionResult.next()) {
                City city = new City();
                city.setCityName(cityInRegionResult.getString(1));
                city.setCountryName(cityInRegionResult.getString(2));
                city.setDistrictName(cityInRegionResult.getString(3));
                city.setCityPopulation(cityInRegionResult.getInt(4));
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
     * Printing a region's cities from the world database
     * @param cities arraylist of city objects.
     */
    public static void printResult(String regionName, ArrayList<City> cities){
        // Check if the region name AND cities is null. If not, move on to the next condition.
        if(regionName == null && cities == null){
            System.out.println("There is no cities or defined region name");
            return;
        }

        // Check if cities arraylist is null. If not, move on to the next condition.
        if (cities == null) {
            System.out.println("There is no cities");
            return;
        }

        // Check if district name is null. If not, move on to the next condition.
        if(regionName == null){
            System.out.println("The region name is not defined");
            return;
        }
        System.out.println("-------------------------------------------All Cities in A Region by Largest Population to Smallest--------------------------------------------");
        System.out.println("| Region: " + regionName + "                                                                                      Total Cities: " + cities.size());
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
