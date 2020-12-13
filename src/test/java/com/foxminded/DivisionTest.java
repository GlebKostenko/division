package com.foxminded;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DivisionTest {

    Division division = new Division();

    @Test
    void makeDivisionWithSurplus() {
        String expected =   "_14789│20\n" +
                " 140  │---\n" +
                " ---  │739\n" +
                "  _78\n" +
                "   60\n" +
                "   --\n" +
                "  _189\n" +
                "   180\n" +
                "   ---\n" +
                "     9\n";
        assertEquals(expected, division.makeDivision(14789, 20));
    }

    @Test
    void makeWholeDivision() {
        String expected =   "_8│2\n" +
                " 8│-\n" +
                " -│4\n" +
                " 0\n";
        assertEquals(expected, division.makeDivision(8, 2));
    }

    @Test
    void notCorrectDivision(){
        String expected = "Divisor cannot be 0, division by zero";
        assertEquals(expected,division.makeDivision(14,0));
    }

    @Test
    void notWholeDivision(){
        String expected =  "_15│17\n"
                +"  0│--\n"
                +"  -│0\n"
                +" 15\n";
        assertEquals(expected,division.makeDivision(15,17));
    }

}