package com.jojo.Day10;

import com.jojo.generators.InstructionsGenerator;
import com.pholser.junit.quickcheck.From;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import junit.framework.TestCase;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(JUnitQuickcheck.class)
public class PBT_Day10 extends TestCase {
    private static Day10 day10;
    @BeforeClass
    public static void init(){
        day10 = new Day10();
    }
    @Property
    public void testResolve(@From(InstructionsGenerator.class) Instructions instr) {
        int[] result = new int[2];
        List<String> instructions = instr.instructions;
        assertEquals(result[0], day10.resolve(instructions)[0]);
        assertEquals(result[1], day10.resolve(instructions)[1]);
    }
}