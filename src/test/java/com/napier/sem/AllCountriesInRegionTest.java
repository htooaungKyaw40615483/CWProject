package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class AllCountriesInRegionTest {
    static AllCountriesInRegion ACIR;

    @BeforeAll
    static void init() { ACIR = new AllCountriesInRegion();}

    //Testing printResults
    @Test
    void printResultTestNull(){
        // will throw java.lang.NullPointerException if the null is not checked in AllCountriesInRegion.
        ACIR.printResult(null,null);
    }

    @Test
    void printResultCountriesTestNull() { ACIR.printResult("Caribbean", null);}

    @Test
    void printResultRnTestNull(){
        ArrayList<Country> countries = new ArrayList<Country>();
        Country c = new Country();
        countries.add(c);
        ACIR.printResult(null,countries);
    }
    @Test
    void printResultCountryTestNull(){
        ArrayList<Country> countries = new ArrayList<Country>();
        Country country = new Country();
        country.setPopulation(99999999);
        countries.add(country);
        ACIR.printResult("Caribbean", countries);

    }

    @Test
    void returnCountryTestNull() { ACIR.returnCountries(null,null);}

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
