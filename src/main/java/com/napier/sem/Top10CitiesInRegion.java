package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Top10CitiesInRegion {
    /**
     * Return a region's cities from the world database
     * @param ab Predefined region Name
     * @param con Established Database Connection
     * @return the City Objects in an ArrayList which is from a single region.
     */
    public static ArrayList<City> ReturnCity(String ab, Connection con){
        try{
            Statement stmt = con.createStatement();
            String sqlQueryCityInRegion = "SELECT world.city.Name, world.country.Name, world.city.District, world.city.Population FROM world.city " +
                    "INNER JOIN world.country ON world.city.CountryCode = world.country.Code " +
                    "WHERE world.country.Region= \"" + ab + "\" " +
                    "ORDER BY world.city.Population DESC LIMIT 10;";
            ResultSet cityInRegionResult = stmt.executeQuery(sqlQueryCityInRegion);
            ArrayList<City> Cities = new ArrayList<City>();
            while(cityInRegionResult.next()) {
                City city = new City();
                city.setCity_name(cityInRegionResult.getString(1));
                city.setCountry_name(cityInRegionResult.getString(2));
                city.setDistrict_name(cityInRegionResult.getString(3));
                city.setCity_population(cityInRegionResult.getInt(4));
                Cities.add(city);
            }
            return Cities;
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
    public static void printResult(String ab, ArrayList<City> cities){
        System.out.println("------------------------------------------Top 10 most populated Cities in A Region by Largest Population to Smallest--------------------------------------------");
        System.out.println("| Region: " + ab + "                                                                                      ORDER: Largest to Smallest Population|");
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
