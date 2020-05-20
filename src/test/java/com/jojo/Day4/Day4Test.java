package com.jojo.Day4;

import com.jojo.util.Util;
import org.hamcrest.beans.SamePropertyValuesAs;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class Day4Test {
    private static List<String> rooms;
    private static Day4 day4;
    private static String[] name;

    @BeforeClass
    public static void init(){
        rooms = Util.load("src/main/resources/day4.txt");
        day4 = new Day4();
    }

    @Before
    public void setUp() {
        String s = "kloqemlib-lygbzq-pqloxdb-991[lbqod]";
        name = s.split("-");
    }

    @Test   // for more tests of this function, refer to PBT test
    public void findSumAndTargetShouldGetExpectedSumAndTarget() {
        Day4.ResultType result = day4.findSumAndTarget(rooms);
        Day4.ResultType expected = new Day4.ResultType(409147,991);

        assertThat(expected, SamePropertyValuesAs.samePropertyValuesAs(result));
    }

    @Test
    public void decryptShouldDecryptNameWhenNameIsValid() {
        String result = day4.decrypt(name, 991);
        String expected = "northpole object storage";

        assertTrue(expected.equals(result));
    }

    @Test
    public void decryptShouldReturnEmptyStringWhenNameLengthIsZero() {
        String result = day4.decrypt(new String[0], 5);
        String expected = "";

        assertTrue(expected.equals(result));

    }
}