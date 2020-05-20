package com.jojo.generators;

import com.jojo.Day10.Instructions;
import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;

import java.util.ArrayList;
import java.util.List;

public class InstructionsGenerator extends Generator<Instructions>{

    public InstructionsGenerator() {
        super(Instructions.class);
    }

    @Override
    public Instructions generate(SourceOfRandomness sourceOfRandomness, GenerationStatus generationStatus){
        List<String> instructions = new ArrayList<>();

        int numOfBots = sourceOfRandomness.nextInt(100, 300);
        int numOfValues = sourceOfRandomness.nextInt(200, 600);
        List<Integer> bots = new ArrayList<>();
        while (bots.size() < numOfBots){
            int bot = sourceOfRandomness.nextInt(0, 100);
            if(!bots.contains(bot)){
                bots.add(bot);
            }
        }
        // generate bots instructions
        for(int i = 0; i < numOfBots + numOfValues; i ++){
            StringBuilder iinstruction = new StringBuilder();
            if(sourceOfRandomness.nextInt(0, 1) == 0 && numOfValues > 0){
                // generate values instruction
                numOfValues --;
                int value = sourceOfRandomness.nextInt(1, 200);
                int bot = bots.get(sourceOfRandomness.nextInt(0, bots.size()-1));
            }else if(sourceOfRandomness.nextInt(0, 1) == 1){
                // generate values instruction

            }
            instructions.add(iinstruction.toString());
        }

        return new Instructions(instructions);
    }

}
