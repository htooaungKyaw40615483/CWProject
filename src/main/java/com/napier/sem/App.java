package com.napier.sem;

import java.sql.*;

/*
Purpose: Main App to call classes to make reports
*/
public class App {
    public static void main(String[] args) {
        // Initialize the variables.
        String DISTRICT = "Saitama"; // District variable initialized with "Saitama"
        String COUNTRY = "China"; // Country variable initialized with "China"
        String REGION = "Caribbean"; // Region variable initialized with "Caribbean"
        String CONTINENT = "North America"; // Continent variable initialized with "North America"
        String CITY = "Blackpool"; // City variable initialized with "Blackpool"

        /*
        Establishing the sql connection for the first time
        SQLConnection Class object is created.
        connect() is used to connect to the database
        */
        SQLConnection connection = new SQLConnection(); // Create an SQLConnection object
        if (args.length < 1) {
            connection.connect("localhost:33060", 30000); // Connect to the database with default arguments
        } else {
            connection.connect(args[0], Integer.parseInt(args[1])); // Connect to the database with provided arguments
        }
        Connection con = connection.getCon(); // Get the database connection

        // Creating the classes to make reports

        // REPORT: All Countries in the World/Continent/Region
        AllCountriesInWorld.printResult(AllCountriesInWorld.returnCountries(con)); // Print all countries in the world
        AllCountriesInContinent.printResult(CONTINENT, AllCountriesInContinent.returnCountries(CONTINENT, con)); // Print all countries in the given continent
        AllCountriesInRegion.printResult(REGION, AllCountriesInRegion.returnCountries(REGION, con)); // Print all countries in the given region

        // REPORT: Top 10 Countries in the World/Continent/Region
        Top10CountriesInWorld.printResult(Top10CountriesInWorld.returnCountries(con)); // Print top 10 countries in the world
        Top10CountriesInContinent.printResult(CONTINENT, Top10CountriesInContinent.returnCountries(CONTINENT, con)); // Print top 10 countries in the given continent
        Top10CountriesInRegion.printResult(REGION, Top10CountriesInRegion.returnCountries(REGION, con)); // Print top 10 countries in the given region

        // REPORT: All Cities in the World/Continent/Region/Country/District
        AllCitiesInWorld.printResult(AllCitiesInWorld.returnCity(con)); // Print all cities in the world
        AllCitiesInContinent.printResult(CONTINENT, AllCitiesInContinent.returnCity(CONTINENT, con)); // Print all cities in the given continent
        AllCitiesInRegion.printResult(REGION, AllCitiesInRegion.returnCity(REGION, con)); // Print all cities in the given region
        AllCitiesInCountry.printResult(COUNTRY, AllCitiesInCountry.returnCity(COUNTRY, con)); // Print all cities in the given country
        AllCitiesInDistrict.printResult(DISTRICT, AllCitiesInDistrict.returnCity(DISTRICT, con)); // Print all cities in the given district

        // REPORT: Top 10 Cities in the World/Continent/Region/Country/District
        Top10CitiesInWorld.printResult(Top10CitiesInWorld.returnCity(con)); // Print top 10 cities in the world
        Top10CitiesInContinent.printResult(CONTINENT, Top10CitiesInContinent.returnCity(CONTINENT, con)); // Print top 10 cities in the given continent
        Top10CitiesInRegion.printResult(REGION, Top10CitiesInRegion.returnCity(REGION, con)); // Print top 10 cities in the given region
        Top10CitiesInDistrict.printResult(DISTRICT, Top10CitiesInDistrict.returnCitiesInDistrict(DISTRICT, con)); // Print top 10 cities in the given district
        Top10CitiesInCountry.printResult(COUNTRY, Top10CitiesInCountry.returnCitiesInCountry(COUNTRY, con)); // Print top 10 cities in the given country

        // REPORT: Capital Cities in the World/Continent/Region
        CapitalCitiesInWorld.printResult((CapitalCitiesInWorld.returnCapital(con))); // Print capital cities in the world
        CapitalCitiesInContinent.printResult(CONTINENT, CapitalCitiesInContinent.returnCapital(CONTINENT, con)); // Print capital cities in the given continent
        CapitalCitiesInRegion.printResult(REGION, CapitalCitiesInRegion.returnCapital(REGION, con)); // Print capital cities in the given region

        // REPORT: Top 10 Capital Cities in the World/Continent/Region
        Top10CapitalCitiesInWorld.printResult(Top10CapitalCitiesInWorld.returnCapital(con)); // Print top 10 capital cities in the world
        Top10CapitalCitiesInContinent.printResult(CONTINENT, Top10CapitalCitiesInContinent.returnCapital(CONTINENT, con)); // Print top 10 capital cities in the given continent
        Top10CapitalCitiesInRegion.printResult(REGION, Top10CapitalCitiesInRegion.returnCapital(REGION, con)); // Print top 10 capital cities in the given region

        // REPORT: Population percentage of Continent/Region/Country
        PopulationPercentInContinent.printResult(PopulationPercentInContinent.returnPopulation(con)); // Print population percentage in the given continent
        PopulationPercentInRegion.printResult(PopulationPercentInRegion.returnPopulation(con)); // Print population percentage in the given region
        PopulationPercentInCountry.printResult(PopulationPercentInCountry.returnPopulation(con)); // Print population percentage in the given country

        WorldPopulation.printResult(WorldPopulation.returnPopulation(con)); // Print world population
        ContinentPopulation.printResult(CONTINENT, ContinentPopulation.returnPopulation(CONTINENT, con)); // Print population of the given continent
        RegionPopulation.printResult(REGION, RegionPopulation.returnPopulation(REGION, con)); // Print population of the given region
        CountryPopulation.printResult(COUNTRY, CountryPopulation.returnPopulation(COUNTRY, con)); // Print population of the given country
        DistrictPopulation.printResult(DISTRICT, DistrictPopulation.returnPopulation(DISTRICT, con)); // Print population of the given district
        CityPopulation.printResult(CITY, CityPopulation.returnPopulation(CITY, con)); // Print population of the given city
        LanguagePopulation.printResult(LanguagePopulation.returnPopulation(con)); // Print population of each language

        // disconnecting the database
        connection.disconnect(); // Disconnect from the database
    }
}
