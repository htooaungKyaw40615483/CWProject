package com.napier.sem;

import java.sql.*;

public class App
{
    public static void main(String[] args)
    {
        String country = "China";
        SQLConnection connection = new SQLConnection();
        connection.connect();
        Connection con = connection.getCon();
        AllCitiesInCountry.printResult(AllCitiesInCountry.ReturnCity(country,con));
        connection.disconnect();
    }

}