package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    static App app;

    @BeforeAll
    static void init() { app = new App(); }

    @Test
    void variableInitializationTest (){
        // Variable Initialization
        App.initializeVariables();

        // Assertion to check if the variable is initialized properly.
        assertEquals("Saitama", App.DISTRICT);
        assertEquals("China", App.COUNTRY);
        assertEquals("Caribbean", App.REGION);
        assertEquals("North America", App.CONTINENT);
    }

}