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
        AllCitiesInCountry.printResult(country, AllCitiesInCountry.ReturnCity(country,con));
        AllCitiesInDistrict.printResult(district, AllCitiesInDistrict.ReturnCity(district,con));

        AllCitiesInRegion.printResult(region,AllCitiesInRegion.ReturnCity(region,con));
        AllCitiesInContinent.printResult(continent,AllCitiesInContinent.ReturnCity(continent,con));

        AllCountriesInWorld.printResult(AllCountriesInWorld.ReturnCountries(con));
        AllCountriesInContinent.printResult(AllCountriesInContinent.ReturnCountries(continent,con));
        AllCountriesInRegion.printResult(AllCountriesInRegion.ReturnCountries(region,con));

        connection.disconnect();
    }

}