package com.jojo.Day1;

import com.jojo.util.Util;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day1 {
    public static void main(String[] args) {
        List<String> input = Util.load("src/main/resources/day1.txt");
        String[] steps = input.get(0).split(", ");
        Steps stp = new Steps(steps);
        int[] res = new Day1().resolve(stp);

        System.out.println("Part1 : " + res[0]);
        System.out.println("Part2 : " + res[1]);
    }

    public int[] resolve(Steps stp) {
        String[] steps = stp.steps;
        String currDir = "N";
        int horizontal_distance = 0;
        int vertical_distance = 0;

        Set<String> positions = new HashSet<>();
        positions.add("0-0");
        int firstVisitedTwice = -1;

        for(String step : steps){
            String direction = step.substring(0,1);
            int move = Integer.parseInt(step.substring(1));

            String dir = currDir + "->" + direction;

            switch (dir) {
                case "N->L":
                    currDir = "W";
                    ResultType result = walk(positions, "H:-", horizontal_distance, vertical_distance, move, firstVisitedTwice);
                    horizontal_distance = result.horizontal_distance;
                    vertical_distance = result.vertical_distance;
                    firstVisitedTwice = result.firstVisitedTwice;
                    break;
                case "N->R":
                    currDir = "E";
                    ResultType result1 = walk(positions, "H:+", horizontal_distance, vertical_distance, move, firstVisitedTwice);
                    horizontal_distance = result1.horizontal_distance;
                    vertical_distance = result1.vertical_distance;
                    firstVisitedTwice = result1.firstVisitedTwice;
                    break;
                case "E->L":
                    currDir = "N";
                    ResultType result2 = walk(positions, "V:+", horizontal_distance, vertical_distance, move, firstVisitedTwice);
                    horizontal_distance = result2.horizontal_distance;
                    vertical_distance = result2.vertical_distance;
                    firstVisitedTwice = result2.firstVisitedTwice;
                    break;
                case "E->R":
                    currDir = "S";
                    ResultType result3 = walk(positions, "V:-", horizontal_distance, vertical_distance, move, firstVisitedTwice);
                    horizontal_distance = result3.horizontal_distance;
                    vertical_distance = result3.vertical_distance;
                    firstVisitedTwice = result3.firstVisitedTwice;
                    break;
                case "S->L":
                    currDir = "E";
                    ResultType result4 = walk(positions, "H:+", horizontal_distance, vertical_distance, move, firstVisitedTwice);
                    horizontal_distance = result4.horizontal_distance;
                    vertical_distance = result4.vertical_distance;
                    firstVisitedTwice = result4.firstVisitedTwice;
                    break;
                case "S->R":
                    currDir = "W";
                    ResultType result5 = walk(positions, "H:-", horizontal_distance, vertical_distance, move, firstVisitedTwice);
                    horizontal_distance = result5.horizontal_distance;
                    vertical_distance = result5.vertical_distance;
                    firstVisitedTwice = result5.firstVisitedTwice;
                    break;
                case "W->L":
                    currDir = "S";
                    ResultType result6 = walk(positions, "V:-", horizontal_distance, vertical_distance, move, firstVisitedTwice);
                    horizontal_distance = result6.horizontal_distance;
                    vertical_distance = result6.vertical_distance;
                    firstVisitedTwice = result6.firstVisitedTwice;
                    break;
                case "W->R":
                    currDir = "N";
                    ResultType result7 = walk(positions, "V:+", horizontal_distance, vertical_distance, move, firstVisitedTwice);
                    horizontal_distance = result7.horizontal_distance;
                    vertical_distance = result7.vertical_distance;
                    firstVisitedTwice = result7.firstVisitedTwice;
                    break;
            }
        }
        int[] result = new int[2];
        result[0] = Math.abs(horizontal_distance) + Math.abs(vertical_distance);
        result[1] = firstVisitedTwice;

        return result;
    }

    public ResultType walk(Set<String> positions, String operation, int hori, int verti, int move, int fVT) {
        int vertical_distance = verti;
        int horizontal_distance = hori;
        int firstVisitedTwice = fVT;
        for(int i = 0; i < move; i ++){
            if(operation.equals("H:-")){
                horizontal_distance -= 1;
            }else if(operation.equals("H:+")){
                horizontal_distance += 1;
            }else if(operation.equals("V:-")){
                vertical_distance -= 1;
            }else if(operation.equals("V:+")){
                vertical_distance += 1;
            }

            firstVisitedTwice = findFirstVisitedPosition(firstVisitedTwice, positions, horizontal_distance, vertical_distance);
        }
        return new ResultType(horizontal_distance, vertical_distance, firstVisitedTwice);
    }

    public int findFirstVisitedPosition(int fVT, Set<String> positions, int hor, int vert) {
        int firstVisitedTwice = fVT;
        if(firstVisitedTwice == -1 && positions.contains(hor + "-" + vert)) {
            firstVisitedTwice = Math.abs(hor) + Math.abs(vert);
        }

        if(firstVisitedTwice == -1){
            positions.add(hor + "-" +vert);
        }

        return firstVisitedTwice;
    }

    public static class ResultType {
        public int horizontal_distance;
        public int vertical_distance;
        public int firstVisitedTwice;
        ResultType(int hori, int verti, int fVT) {
            horizontal_distance = hori;
            vertical_distance = verti;
            firstVisitedTwice = fVT;
        }
    }
}
