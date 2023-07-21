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
        System.out.println("-------------------------------------------All Cities in A Region by Largest Population to Smallest-----------------------------------------------");
        System.out.println("| Region: " + regionName + "                                                                                      Total Cities: " + cities.size());
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
