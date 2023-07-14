package com.napier.sem;

import java.sql.*;

public class App
{
    public static void main(String[] args)
    {
        String district = "Bíobío";
        String country = "China";
        String region = "Caribbean";
        String continent="North America";

        SQLConnection connection = new SQLConnection();
        connection.connect();
        Connection con = connection.getCon();

        AllCitiesInWorld.printResult(AllCitiesInWorld.getAllCities(con));
//
//        AllCitiesInCountry.printResult(AllCitiesInCountry.ReturnCity(country,con));
//        AllCitiesInDistrict.printResult(AllCitiesInDistrict.ReturnCity(district,con));
//
//        AllCitiesInRegion.printResult(AllCitiesInRegion.ReturnCity(region,con));
//        AllCitiesInContinent.printResult(AllCitiesInContinent.ReturnCity(continent,con));
//
//        AllCountriesInWorld.printResult(AllCountriesInWorld.ReturnCountries(con));
//
        connection.disconnect();
    }

}