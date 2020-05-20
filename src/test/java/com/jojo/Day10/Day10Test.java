package com.jojo.Day10;


import com.jojo.util.Util;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class Day10Test {
    private static List<String> rooms;
    private static Day10 day10;

    @BeforeClass
    public static void init(){
        rooms = Util.load("src/main/resources/day10.txt");
        day10 = new Day10();
    }

    @Before
    public void setUp() {

    }

    @Test      // more tests should go in PBT
    public void resolveShouldReturnExpectedResultWhenInputIsValid() {
        int[] result = day10.resolve(rooms);
        int[] expected = new int[]{116, 23903};

        assertEquals(expected.length, result.length);
        assertEquals(expected[0], result[0]);
        assertEquals(expected[1], result[1]);
    }

    @Test
    public void moveChipsSuccessfullyMovedChipToBotOrOutputWhenInputValid() {

    }

    @Test
    public void moveChipsSuccessfullyMovedChipToBotOrOutputWhenInputNotValid() {

    }
}