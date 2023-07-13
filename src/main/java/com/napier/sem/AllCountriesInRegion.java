package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;

public class AllCountriesInRegion {
    public static ArrayList<Country> ReturnCountries(String rn, Connection con){
        try{
            Statement stmt = con.createStatement();
            String sqlQueryCountryInWorld = "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, city.Name " +
                    "FROM `country` INNER JOIN city ON country.Capital = city.ID " +
                    "WHERE country.Region = \"" + rn + "\" ORDER BY country.Population DESC;;";
            ResultSet CountriesInWorld = stmt.executeQuery(sqlQueryCountryInWorld);
            ArrayList<Country> Countries = new ArrayList<Country>();
            while(CountriesInWorld.next()) {
                Country country = new Country();
                country.setCountry_no(CountriesInWorld.getString(1));
                country.setCountry_name(CountriesInWorld.getString(2));
                country.setContinent_name(CountriesInWorld.getString(3));
                country.setRegion_name(CountriesInWorld.getString(4));
                country.setPopulation(CountriesInWorld.getInt(5));
                country.setCapital_name(CountriesInWorld.getString(6));
                Countries.add(country);
            }
            return Countries;
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get countries population by continent name in the world");
            return null;
        }
    }
    public static void printResult(ArrayList<Country> countries){
        System.out.println("---------------------------------------------------All Countries in the Region By Largest Population To Smallest------------------------------------------------------------------");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-4s | %-40s | %-30s | %-30s | %-20s | %-35s | %n", "Code", "Name", "Continent", "Region", "Population", "Capital");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        for (Country country :countries){
            System.out.printf("| %-4s | %-40s | %-30s | %-30s | %20d | %-35s | %n", country.getCountry_no(), country.getCountry_name(), country.getContinent_name(), country.getRegion_name(), country.getPopulation(), country.getCapital_name());
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

    }
}
