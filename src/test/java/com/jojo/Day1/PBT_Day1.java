package com.jojo.Day1;

import com.jojo.generators.StepsGenerator;
import com.pholser.junit.quickcheck.From;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import junit.framework.TestCase;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import java.util.HashSet;
import java.util.Set;

import static com.google.common.truth.Truth.assertThat;

@RunWith(JUnitQuickcheck.class)
public class PBT_Day1 extends TestCase {
    private static Day1 day1;
    @BeforeClass
    public static void init(){
        day1 = new Day1();
    }
    @Property
    public void testResolveReturnsCorrectHQLocation(@From(StepsGenerator.class) Steps stp) {
        String[] steps = stp.steps;
        String currDir = "N";
        int horizontal_distance = 0;
        int vertical_distance = 0;

        Set<String> positions = new HashSet<>();
        positions.add("0-0");
        boolean foundHQ = false;
        int firstVisitedTwice = -1;

        for(String step : steps){
            String direction = step.substring(0,1);
            int move = Integer.parseInt(step.substring(1, step.length()));

            String dir = currDir + "->" + direction;

            switch (dir) {
                case "N->L":
                    currDir = "W";
                    Day1.ResultType result = PBT_Day1.day1.walk(positions, "H:-", horizontal_distance, vertical_distance, move, firstVisitedTwice);
                    horizontal_distance = result.horizontal_distance;
                    vertical_distance = result.vertical_distance;
                    firstVisitedTwice = result.firstVisitedTwice;
                    break;
                case "N->R":
                    currDir = "E";
                    Day1.ResultType result1 = PBT_Day1.day1.walk(positions, "H:+", horizontal_distance, vertical_distance, move, firstVisitedTwice);
                    horizontal_distance = result1.horizontal_distance;
                    vertical_distance = result1.vertical_distance;
                    firstVisitedTwice = result1.firstVisitedTwice;
                    break;
                case "E->L":
                    currDir = "N";
                    Day1.ResultType result2 = PBT_Day1.day1.walk(positions, "V:+", horizontal_distance, vertical_distance, move, firstVisitedTwice);
                    horizontal_distance = result2.horizontal_distance;
                    vertical_distance = result2.vertical_distance;
                    firstVisitedTwice = result2.firstVisitedTwice;
                    break;
                case "E->R":
                    currDir = "S";
                    Day1.ResultType result3 = PBT_Day1.day1.walk(positions, "V:-", horizontal_distance, vertical_distance, move, firstVisitedTwice);
                    horizontal_distance = result3.horizontal_distance;
                    vertical_distance = result3.vertical_distance;
                    firstVisitedTwice = result3.firstVisitedTwice;
                    break;
                case "S->L":
                    currDir = "E";
                    Day1.ResultType result4 = PBT_Day1.day1.walk(positions, "H:+", horizontal_distance, vertical_distance, move, firstVisitedTwice);
                    horizontal_distance = result4.horizontal_distance;
                    vertical_distance = result4.vertical_distance;
                    firstVisitedTwice = result4.firstVisitedTwice;
                    break;
                case "S->R":
                    currDir = "W";
                    Day1.ResultType result5 = PBT_Day1.day1.walk(positions, "H:-", horizontal_distance, vertical_distance, move, firstVisitedTwice);
                    horizontal_distance = result5.horizontal_distance;
                    vertical_distance = result5.vertical_distance;
                    firstVisitedTwice = result5.firstVisitedTwice;
                    break;
                case "W->L":
                    currDir = "S";
                    Day1.ResultType result6 = PBT_Day1.day1.walk(positions, "V:-", horizontal_distance, vertical_distance, move, firstVisitedTwice);
                    horizontal_distance = result6.horizontal_distance;
                    vertical_distance = result6.vertical_distance;
                    firstVisitedTwice = result6.firstVisitedTwice;
                    break;
                case "W->R":
                    currDir = "N";
                    Day1.ResultType result7 = PBT_Day1.day1.walk(positions, "V:+", horizontal_distance, vertical_distance, move, firstVisitedTwice);
                    horizontal_distance = result7.horizontal_distance;
                    vertical_distance = result7.vertical_distance;
                    firstVisitedTwice = result7.firstVisitedTwice;
                    break;
            }
        }
        int[] result = new int[2];
        result[0] = Math.abs(horizontal_distance) + Math.abs(vertical_distance);
        result[1] = firstVisitedTwice;

        int[] res = PBT_Day1.day1.resolve(stp);
        assertEquals(result[0], res[0]);
        assertEquals(result[1], res[1]);
        assertThat(res[0] >= 0 && res[1] >= 0);
    }
}