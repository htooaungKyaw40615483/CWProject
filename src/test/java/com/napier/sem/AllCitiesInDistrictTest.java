package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class AllCitiesInDistrictTest
{
    static AllCitiesInDistrict ACID;

    @BeforeAll
    static void init(){
        ACID = new AllCitiesInDistrict();
    }

    // Testing printResults
    @Test
    void printResultTestNull()
    {
        // will throw java.lang.NullPointerException if the null is not checked in AllCitiesInDistrict.
        ACID.printResult(null, null);
    }

    @Test
    void printResultCitiesTestNull(){
        ACID.printResult("Bíobío", null);
    }

    @Test
    void printResultDnTestNull(){
        ArrayList<City> cities = new ArrayList<City>();
        City c = new City();
        cities.add(c);
        ACID.printResult(null, cities);
    }

    @Test
    void printResultCityTestNull(){
        ArrayList<City> cities = new ArrayList<City>();
        City city = new City();
        city.setCityPopulation(99999999);
        cities.add(city);
        ACID.printResult("Bíobío", cities);
    }

    @Test
    void returnCityTestNull(){
        ACID.returnCity(null,null);
    }
    @Test
    public void statementQueryTesting() throws SQLException {
        // Creating the mock connection, statement, and result set.
        Connection mockCon = mock(Connection.class);
        Statement mockStatement = mock(Statement.class);
        ResultSet mockResultSet = mock(ResultSet.class);

        // Return mock statement when the createStatement() is called.
        when(mockCon.createStatement()).thenReturn(mockStatement);

        // Return mock result set when the executeQuery() is called.
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);

        Statement stmt = mockCon.createStatement();

        // assert a statement that is not null
        assertNotNull(stmt);

        // creating the mock statement with the mock connection, with the
        verify(mockCon, times(1)).createStatement();
    }
}