package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;

public class AllCountriesInWorld {
    public static ArrayList<Country> ReturnCountries(Connection con){
        try{
            Statement stmt = con.createStatement();
            String sqlQueryCountryInWorld = "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, city.Name " +
                                            "FROM country, city WHERE city.ID = country.Capital ORDER BY country.Population DESC;";
            ResultSet CountriesInWorld = stmt.executeQuery(sqlQueryCountryInWorld);
            ArrayList<Country> Countries = new ArrayList<Country>();
            while(CountriesInWorld.next()) {
                Country country = new Country();
                country.country_no = CountriesInWorld.getString(1);
                country.country_name = CountriesInWorld.getString(2);
                country.continent_name = CountriesInWorld.getString(3);
                country.region_name = CountriesInWorld.getString(4);
                country.population = CountriesInWorld.getInt(5);
                country.capital_name = CountriesInWorld.getString(6);
                Countries.add(country);
            }
            return Countries;
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get countries population in the world");
            return null;
        }
    }
    public static void printResult(ArrayList<Country> countries){
        System.out.println("--------------------------------All Countries in the world By Largest Population To Smallest-------------------------------");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-4s | %-40s | %-30s | %-30s | %-20s | %-30s | %n", "Code", "Name", "Continent", "Region", "Population", "Capital");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------");
        for (Country country :countries){
            System.out.printf("| %-4s | %-40s | %-30s | %-30s | %20d | %-30s | %n", country.country_no, country.country_name, country.continent_name, country.region_name, country.population, country.capital_name);
        }
        System.out.println("---------------------------------------------------------------------------------------------------------------------------");

    }
}
