package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;

public class AllCitiesInCountry {
    public static ArrayList<City> ReturnCity(String cn, Connection con){
        try{
            Statement stmt = con.createStatement();
            String sqlQueryCityInCountry = "SELECT world.city.Name, world.country.Name, world.city.District, world.city.Population FROM world.city " +
                    "INNER JOIN world.country ON world.city.CountryCode = world.country.Code " +
                    "WHERE world.country.Name= \"" + cn + "\" " +
                    "ORDER BY world.city.Population DESC;";
            ResultSet cityInCountryResult = stmt.executeQuery(sqlQueryCityInCountry);
            ArrayList<City> Cities = new ArrayList<City>();
            while(cityInCountryResult.next()) {
                City city = new City();
                city.city_name = cityInCountryResult.getString(1);
                city.country_name = cityInCountryResult.getString(2);
                city.district_name = cityInCountryResult.getString(3);
                city.city_population = cityInCountryResult.getInt(4);
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
    public static void printResult(ArrayList<City> cities){
        System.out.println("--------------------------------All Cities In A Country By Largest Population To Smallest----------------------------------");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-30s | %-30s | %-30s | %-20s | %n", "Name", "Country", "District", "Population");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------");
        for (City city :cities){
            System.out.printf("| %-30s | %-30s | %-30s | %20d | %n", city.city_name, city.country_name, city.district_name, city.city_population);
        }
        System.out.println("---------------------------------------------------------------------------------------------------------------------------");

    }
}
