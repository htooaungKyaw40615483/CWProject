package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.Connection;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest
{
    static AllCitiesInDistrict ACID;

    @BeforeAll
    static void init(){
        ACID = new AllCitiesInDistrict();
    }

    @Test
    void printResultsTestNull()
    {
        // will throw java.lang.NullPointerException if the null is not checked in AllCitiesInDistrict.
        ACID.printResult(null, null);
    }
}