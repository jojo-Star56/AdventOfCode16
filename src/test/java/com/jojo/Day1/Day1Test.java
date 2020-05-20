package com.jojo.Day1;

import com.jojo.util.Util;
import org.hamcrest.beans.SamePropertyValuesAs;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;


public class Day1Test{
    private static List<String> input;
    private static String[] steps;
    private static Steps stp;
    private static Day1 day1;
    private static Set<String> positions;

    @BeforeClass
    public static void init(){
        input = Util.load("src/main/resources/day1.txt");
        day1 = new Day1();
        steps = input.get(0).split(", ");
        stp = new Steps(steps);
    }

    @Before
    public void setUp() {
        positions = new HashSet<>();
        positions.add("2-3");
        positions.add("4-5");
    }

    @Test
    public void resolveShouldGetLocationAndFirstVisitedTwiceWhenInputValid() {

        int[] result = day1.resolve(stp);

        assertEquals(2, 2);
        assertEquals(246, result[0]);
        assertEquals(124, result[1]);
    }

    // this should go to PBT
//    @Test
//    public void ResolveShouldNotGetLocationAndFirstVisitedTwiceWhenInputInValid() { }

    @Test
    public void walkEastShouldReturnExpectedResult() {
        Day1.ResultType result = day1.walk(positions, "H:+", 15, 16, 3, -1);
        Day1.ResultType expected = new Day1.ResultType(18, 16,-1);

        assertThat(expected, SamePropertyValuesAs.samePropertyValuesAs(result));
    }

    @Test
    public void walkWestShouldReturnExpectedResult() {
        Day1.ResultType result = day1.walk(positions, "H:-", 15, 16, 3, -1);
        Day1.ResultType expected = new Day1.ResultType(12, 16,-1);

        assertThat(expected, SamePropertyValuesAs.samePropertyValuesAs(result));
    }

    @Test
    public void walkNorthShouldReturnExpectedResult() {
        Day1.ResultType result = day1.walk(positions, "V:+", 15, 16, 3, -1);
        Day1.ResultType expected = new Day1.ResultType(15, 19,-1);

        assertThat(expected, SamePropertyValuesAs.samePropertyValuesAs(result));
    }

    @Test
    public void walkSouthShouldReturnExpectedResult() {
        Day1.ResultType result = day1.walk(positions, "V:-", 15, 16, 3, -1);
        Day1.ResultType expected = new Day1.ResultType(15, 13,-1);

        assertThat(expected, SamePropertyValuesAs.samePropertyValuesAs(result));
    }

    @Test
    public void findFirstVisitedPositionFindsPosition() {

        positions.add("5-6");

        int result = day1.findFirstVisitedPosition(-1, positions, 5, 6);

        assertTrue(positions.contains("5-6"));
        assertEquals(11, result);
    }

    @Test
    public void findFirstVisitedPositionDidNotFindPosition() {

        int result = day1.findFirstVisitedPosition(-1, positions, 5, 6);

        assertTrue(positions.contains("5-6"));
        assertEquals(3, positions.size());
        assertEquals(-1, result);
    }
}