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
        AllCountriesInWorld.printResult(AllCountriesInWorld.ReturnCountries(con));
        AllCountriesInContinent.printResult(continent, AllCountriesInContinent.ReturnCountries(continent,con));
        AllCountriesInRegion.printResult(region, AllCountriesInRegion.ReturnCountries(region,con));
        Top5CountriesInWorld.printResult(Top5CountriesInWorld.ReturnCountries(con));
        Top5CountriesInContinent.printResult(continent, Top5CountriesInContinent.ReturnCountries(continent,con));
        Top5CountriesInRegion.printResult(region, Top5CountriesInRegion.ReturnCountries(region,con));


        AllCitiesInWorld.printResult(AllCitiesInWorld.getAllCities(con));
        AllCitiesInContinent.printResult(continent, AllCitiesInContinent.ReturnCity(continent,con));
        AllCitiesInRegion.printResult(region, AllCitiesInRegion.ReturnCity(region,con));
        AllCitiesInCountry.printResult(country, AllCitiesInCountry.ReturnCity(country,con));
        AllCitiesInDistrict.printResult(district, AllCitiesInDistrict.ReturnCity(district,con));





        // disconnecting the database
        connection.disconnect();
    }

}