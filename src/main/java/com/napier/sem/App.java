package com.napier.sem;

import java.sql.*;

/*
Purpose: Main App to call classes to make reports
 */
public class App
{
    static String DISTRICT;
    static String COUNTRY;
    static String REGION;
    static String CONTINENT;
    static Connection con;

    // Method to initialize the variables
    public static void initializeVariables() {
        DISTRICT = "Saitama";
        COUNTRY = "China";
        REGION = "Caribbean";
        CONTINENT = "North America";
    }

    public static void main(String[] args)
    {
        // Variable Initialization for District, Region, Country, Continent.
        initializeVariables();

        SQLConnection connection = new SQLConnection();
        connection.connect();
        con = connection.getCon();

        // Establishing the connection.
        connection.connect();

        // Creating the classes to make reports
        // REPORT: All Countries in the World/Continent/Region
        AllCountriesInWorld.printResult(AllCountriesInWorld.returnCountries(con));
        AllCountriesInContinent.printResult(CONTINENT, AllCountriesInContinent.returnCountries(CONTINENT,con));
        AllCountriesInRegion.printResult(REGION, AllCountriesInRegion.returnCountries(REGION,con));

        // REPORT: Top 10 Countries in the World/Continent/Region
        Top10CountriesInWorld.printResult(Top10CountriesInWorld.returnCountries(con));
        Top10CountriesInContinent.printResult(CONTINENT, Top10CountriesInContinent.returnCountries(CONTINENT,con));
        Top10CountriesInRegion.printResult(REGION, Top10CountriesInRegion.returnCountries(REGION,con));

        // REPORT: All Cities in the World/Continent/Region/Country/District
        AllCitiesInWorld.printResult(AllCitiesInWorld.returnCity(con));
        AllCitiesInContinent.printResult(CONTINENT, AllCitiesInContinent.returnCity(CONTINENT,con));
        AllCitiesInRegion.printResult(REGION, AllCitiesInRegion.returnCity(REGION,con));
        AllCitiesInCountry.printResult(COUNTRY, AllCitiesInCountry.returnCity(COUNTRY,con));
        AllCitiesInDistrict.printResult(DISTRICT, AllCitiesInDistrict.returnCity(DISTRICT,con));

        // REPORT: Top 10 Cities in the World/Continent/Region/Country/District
        Top10CitiesInWorld.printResult(Top10CitiesInWorld.returnCity(con));
        Top10CitiesInContinent.printResult(CONTINENT, Top10CitiesInContinent.returnCity(CONTINENT,con));
        Top10CitiesInRegion.printResult(REGION, Top10CitiesInRegion.returnCity(REGION,con));
        Top10CitiesInDistrict.printResult(DISTRICT, Top10CitiesInDistrict.returnCitiesInDistrict(DISTRICT, con));
        Top10CitiesInCountry.printResult(COUNTRY, Top10CitiesInCountry.returnCitiesInCountry(COUNTRY, con));

        // disconnecting the database
        connection.disconnect();
    }

}