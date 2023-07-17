package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class AllCitiesInContinent {
    /**
     * Return a continent's cities from the world database
     * @param bc Predefined continent Name
     * @param con Established Database Connection
     * @return the City Objects in an ArrayList which is from a single continent.
     */

    public static ArrayList<City> ReturnCity(String bc, Connection con){
        try{
            Statement stmt = con.createStatement();
            String sqlQueryCityInCountry = "SELECT world.city.Name, world.country.Name, world.city.District, world.city.Population FROM world.city " +
                    "INNER JOIN world.country ON world.city.CountryCode = world.country.Code " +
                    "WHERE world.country.Continent= \"" + bc + "\" " +
                    "ORDER BY world.city.Population DESC;";
            ResultSet cityInCountryResult = stmt.executeQuery(sqlQueryCityInCountry);
            ArrayList<City> Cities = new ArrayList<City>();
            while(cityInCountryResult.next()) {
                City city = new City();
                city.setCity_name(cityInCountryResult.getString(1));
                city.setCountry_name(cityInCountryResult.getString(2));
                city.setDistrict_name(cityInCountryResult.getString(3));
                city.setCity_population(cityInCountryResult.getInt(4));
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
     * Printing a continent's cities from the world database
     * @param cities arraylist of city objects.
     */
    public static void printResult(String bc, ArrayList<City> cities){
        System.out.println("-------------------------------------------All Cities in A Continent by Largest Population to Smallest-----------------------------------------");
        System.out.println("| Continent: " + bc + "                                                                               ORDER: Largest to Smallest Population|");
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
