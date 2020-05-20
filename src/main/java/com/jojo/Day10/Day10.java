package com.jojo.Day10;

import java.util.*;
import com.jojo.util.Util;

public class Day10 {
    public static void main(String[] args){
        Day10 day10 = new Day10();
        List<String> instructions = day10.getInstructions("src/main/resources/day10.txt");
        day10.resolve(instructions);
    }

    public int[] resolve(List<String> instructions){
        Map<Integer, Bot> botList = new HashMap<>();
        Map<Integer, Integer> outputList = new TreeMap<>();
        // 1.init the bots and chips they hold before executing bot instructions
        init(botList, instructions);

        // 2. execute all bot instructions for 1 time
        Set<Integer> executedBots = new HashSet<>();
        int targetBotNumber = -1;

        while (botList.size() > executedBots.size()) {
            for (String s : instructions){
                if(!s.startsWith("bot")){
                    continue;
                }
                String[] instruction = s.split(" ");
                int bot = Integer.parseInt(instruction[1]);
                if ( !executedBots.contains(bot) && botList.get(bot).chips.size() > 1){
                    // 1. add bot to execution check
                    executedBots.add(bot);


                    // 2. find result for Part 1;
                    if(targetBotNumber == -1 && botList.get(bot).getLowValue() == 17 && botList.get(bot).getHighValue() == 61){
                        targetBotNumber = bot;
                    }


                    // 3. copy chips to bot or output when bot possesses more than 1 chip
                    moveChips(instruction, botList, outputList, bot);
                }
            }
        }

        if(targetBotNumber == -1){
            System.out.println("Part1: bot compares chip 61 and chip 17 is ---- NOT FOUND");
        } else {
            System.out.println("Part1: bot compares chip 61 and chip 17 is ---- " + targetBotNumber);
        }
        System.out.println("Part2: multiply together the values of one chip in each of outputs 0, 1, and 2 is ---- " + outputList.get(0)*outputList.get(1)*outputList.get(2));
        int[] res = new int[2];
        res[0] = targetBotNumber;
        res[1] = outputList.get(0)*outputList.get(1)*outputList.get(2);
        return res;
    }

    public void init(Map<Integer, Bot> botList, List<String> instructions) {
        for (String s : instructions){
            String[] instruction = s.split(" ");

            if(s.startsWith("bot")){
                int botNumber = Integer.parseInt(instruction[1]);
                if(!botList.containsKey(botNumber)){
                    botList.put(botNumber, new Bot());
                }
            } else if(s.startsWith("value")){
                int botToGo = Integer.parseInt(instruction[5]);
                if (!botList.containsKey(botToGo)) {
                    botList.put(botToGo, new Bot());
                    botList.get(botToGo).addChip(Integer.parseInt(instruction[1]));
                }else {
                    botList.get(botToGo).addChip(Integer.parseInt(instruction[1]));
                }
            }
        }
    }

    public void moveChips(String[] instruction, Map<Integer, Bot> botList, Map<Integer, Integer> outputList, int bot) {
        int lowToGo = Integer.parseInt(instruction[6]);
        if (instruction[5].equals("bot")){
            botList.get(lowToGo).addChip(botList.get(bot).getLowValue());
        } else {
            outputList.put(lowToGo , botList.get(bot).getLowValue());
        }

        int highToGo = Integer.parseInt(instruction[11]);
        if (instruction[10].equals("bot")){
            botList.get(highToGo).addChip(botList.get(bot).getHighValue());
        } else {
            outputList.put(highToGo, botList.get(bot).getHighValue());
        }
    }

    public List<String> getInstructions(String input) {
        List<String> instructions = Util.load(input);
        return instructions;
    }

    private class Bot{
        private List<Integer> chips;
        private int low;
        private int high;
        Bot(){
            this.chips = new ArrayList<>();
        }
        public void addChip(int chip){
            if(chips.size() == 1){
                chips.add(chip);
                low = Math.min(chips.get(0), chips.get(1));
                high = Math.max(chips.get(0), chips.get(1));
            }else {
                chips.add(chip);
            }
        }

        public int getLowValue(){
            return low;
        }

        public int getHighValue(){
            return high;
        }
    }
}