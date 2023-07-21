package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.StartsWith;


import java.sql.Connection;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class Top10CitiesInWorldTest {

    static Top10CitiesInWorld TCW;

    @BeforeAll
    static void init(){
        TCW = new Top10CitiesInWorld();
    }

    // Testing printResults
    @Test
    void printResultTestNull()
    {
        // will throw java.lang.NullPointerException if the null is not checked in AllCitiesInDistrict.
        TCW.printResult(null);
    }

    @Test
    void returnCityTestNull(){
        TCW.returnCity(null);
    }
}
