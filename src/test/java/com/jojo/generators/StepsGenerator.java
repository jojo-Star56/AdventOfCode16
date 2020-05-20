package com.jojo.generators;

import com.jojo.Day1.Steps;
import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;

public class StepsGenerator extends Generator<Steps> {
    private int lowerNumOfSteps = 10;
    private int upperNumOfSteps = 100;
    private int lowerNumOfMoves = 2;
    private int upperNumOfMoves = 500;

    public StepsGenerator() {
        super(Steps.class);
    }

    @Override
    public Steps generate(SourceOfRandomness sourceOfRandomness, GenerationStatus generationStatus) {
        int numOfSteps = sourceOfRandomness.nextInt(lowerNumOfSteps, upperNumOfSteps);
        String[] steps = new String[numOfSteps];
        for(int i = 0; i < numOfSteps; i ++){
            int direction = sourceOfRandomness.nextInt(0, 1);
            int move = sourceOfRandomness.nextInt(lowerNumOfMoves,upperNumOfMoves);
            steps[i] = direction == 0 ? "L" + move : "R" + move;
        }
        return new Steps(steps);
    }

    public void configure(int sLower, int sUpper, int mLower, int mUpper) {
        this.lowerNumOfSteps = sLower;
        this.upperNumOfSteps = sUpper;
        this.lowerNumOfMoves = mLower;
        this.upperNumOfMoves = mUpper;
    }

}
