package com.napier.sem;

import java.sql.*;

/*
Purpose: Main App to call classes to make reports
 */
public class App
{
    public static void main(String[] args)
    {
        // Initialize the variables.
        String district = "Bíobío";
        String country = "China";
        String region = "Caribbean";
        String continent="North America";

        /*
        Establishing the sql connection for the first time
        SQLConnection Class object is created.
        connect() is used to connect to the database
         */
        SQLConnection connection = new SQLConnection();
        connection.connect();
        Connection con = connection.getCon();

        // Creating the classes to make reports
        // REPORT: All Countries in the World/Continent/Region
        AllCountriesInWorld.printResult(AllCountriesInWorld.ReturnCountries(con));
        AllCountriesInContinent.printResult(continent, AllCountriesInContinent.ReturnCountries(continent,con));
        AllCountriesInRegion.printResult(region, AllCountriesInRegion.ReturnCountries(region,con));

        // REPORT: Top 10 Countries in the World/Continent/Region
        Top10CountriesInWorld.printResult(Top10CountriesInWorld.ReturnCountries(con));
        Top10CountriesInContinent.printResult(continent, Top10CountriesInContinent.ReturnCountries(continent,con));
        Top10CountriesInRegion.printResult(region, Top10CountriesInRegion.ReturnCountries(region,con));

        // REPORT: All Cities in the World/Continent/Region/Country/District
        AllCitiesInWorld.printResult(AllCitiesInWorld.getAllCities(con));
        AllCitiesInContinent.printResult(continent, AllCitiesInContinent.ReturnCity(continent,con));
        AllCitiesInRegion.printResult(region, AllCitiesInRegion.ReturnCity(region,con));
        AllCitiesInCountry.printResult(country, AllCitiesInCountry.ReturnCity(country,con));
        AllCitiesInDistrict.printResult(district, AllCitiesInDistrict.ReturnCity(district,con));

        // REPORT: Top 10 Cities in the World/Continent/Region/Country/District
        Top10CitiesInWorld.printResult(Top10CitiesInWorld.ReturnCity(con));
        Top10CitiesInContinent.printResult(continent, Top10CitiesInContinent.ReturnCity(continent,con));
        Top10CitiesInRegion.printResult(region, Top10CitiesInRegion.ReturnCity(region,con));
        Top10CitiesInDistrict.printResult(district, Top10CitiesInDistrict.returnCitiesInDistrict(district, con));
        Top10CitiesInCountry.printResult(country, Top10CitiesInCountry.returnCitiesInCountry(country, con));




        // disconnecting the database
        connection.disconnect();
    }

}