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
        AllCitiesInWorld.printResult(AllCitiesInWorld.getAllCities(con));
        AllCitiesInCountry.printResult(AllCitiesInCountry.ReturnCity(country,con));
        AllCitiesInDistrict.printResult(AllCitiesInDistrict.ReturnCity(district,con));
        AllCitiesInRegion.printResult(AllCitiesInRegion.ReturnCity(region,con));
        AllCitiesInContinent.printResult(AllCitiesInContinent.ReturnCity(continent,con));

        AllCountriesInWorld.printResult(AllCountriesInWorld.ReturnCountries(con));
        AllCountriesInContinent.printResult(AllCountriesInContinent.ReturnCountries(continent,con));
        AllCountriesInRegion.printResult(AllCountriesInRegion.ReturnCountries(region,con));

        // disconnecting the database
        connection.disconnect();
    }

}