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

public class ContinentPopulationTest {
    static ContinentPopulation CP;

    @BeforeAll
    static void init(){
        CP = new ContinentPopulation();
    }

    // Testing printResults
    @Test
    void printResultTestNull()
    {
        // will throw java.lang.NullPointerException if the null is not checked in ContinentPopulation.
        CP.printResult(null, null);
    }

    @Test
    void printResultContinentsTestNull(){
        CP.printResult("North America", null);
    }

    @Test
    void printResultDnTestNull(){
        ArrayList<Population> populations = new ArrayList<>();
        Population p = new Population();
        populations.add(p);
        CP.printResult(null, populations);
    }

    @Test
    void printResultContinentTestNull(){
        ArrayList<Population> populations = new ArrayList<Population>();
        Population population = new Population();
        population.setTotalPopulation(99999999);
        populations.add(population);
        CP.printResult("North America", populations);
    }

    @Test
    void returnContinentTestNull(){
        CP.returnPopulation(null,null);
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
