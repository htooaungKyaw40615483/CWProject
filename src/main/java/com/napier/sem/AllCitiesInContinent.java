package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class AllCitiesInContinent {
    public static ArrayList<City> ReturnCity(String ab, Connection con){
        try{
            Statement stmt = con.createStatement();
            String sqlQueryCityInCountry = "SELECT world.city.Name, world.country.Name, world.city.District, world.city.Population FROM world.city " +
                    "INNER JOIN world.country ON world.city.CountryCode = world.country.Code " +
                    "WHERE world.country.Continent= \"" + ab + "\" " +
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
    public static void printResult(ArrayList<City> cities){
        System.out.println("-------------------------------All Cities In A District By Largest Population To Smallest----------------------------------");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-30s | %-30s | %-30s | %-20s | %n", "Name", "Country", "District", "Population");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------");
        for (City city :cities){
            System.out.printf("| %-30s | %-30s | %-30s | %20d | %n", city.getCityName(), city.getCountryName(), city.getDistrictName(), city.getCityPopulation());
        }
        System.out.println("---------------------------------------------------------------------------------------------------------------------------");

    }
}
