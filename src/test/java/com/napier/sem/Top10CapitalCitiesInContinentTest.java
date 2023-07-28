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
public class Top10CapitalCitiesInContinentTest {
    static Top10CapitalCitiesInContinent TCCC;

    @BeforeAll
    static void init(){
        TCCC = new Top10CapitalCitiesInContinent();
    }

    // Testing printResults
    @Test
    void printResultTestNull()
    {
        // will throw java.lang.NullPointerException if the null is not checked in Top10CapitalCitiesInContinent.
        TCCC.printResult(null, null);
    }

    @Test
    void printResultCapitalCitiesTestNull(){
        TCCC.printResult("North America", null);
    }

    @Test
    void printResultDnTestNull(){
        ArrayList<Capital> capitals = new ArrayList<>();
        Capital c = new Capital();
        capitals.add(c);
        TCCC.printResult(null, capitals);
    }

    @Test
    void printResultCapitalCityTestNull(){
        ArrayList<Capital> capitals = new ArrayList<>();
        Capital capital = new Capital();
        capital.setCapitalPopulation(99999999);
        capitals.add(capital);
        TCCC.printResult("North America", capitals);
    }

    @Test
    void returnCapitalCityTestNull(){
        TCCC.returnCapital(null,null);
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
