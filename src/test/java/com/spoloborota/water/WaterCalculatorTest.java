package com.spoloborota.water;

import org.junit.Test;

import static com.spoloborota.water.WaterCalculator.*;
import static org.junit.Assert.*;

public class WaterCalculatorTest {

    @Test(expected = LandscapeValidationException.class)
    public void incorrectLandscape_negativeHeight() {
        calculateWaterAmount(new int[] {16, 2048, -256, 512});
    }

    @Test(expected = LandscapeValidationException.class)
    public void incorrectLandscape_inflatedHeight() {
        calculateWaterAmount(new int[] {16, HIGHEST_HEIGHT + 1, 64, 512});
    }

    @Test(expected = LandscapeValidationException.class)
    public void incorrectLandscape_inflatedArraySize() {
        calculateWaterAmount(new int[MAX_POSITIONS_NUMBER + 1]);
    }

    @Test
    public void zeroResult_oneElement() {
        assertEquals(0, calculateWaterAmount(new int[] {LOWEST_HEIGHT}));
    }

    @Test
    public void zeroResult_sameHeight() {
        assertEquals(0, calculateWaterAmount(
                new int[] {HIGHEST_HEIGHT, HIGHEST_HEIGHT, HIGHEST_HEIGHT, HIGHEST_HEIGHT}));
    }

    @Test
    public void zeroResult_stairs() {
        assertEquals(0, calculateWaterAmount(new int[] {2, 4, 6, 8}));
    }

    @Test
    public void zeroResult_triangle() {
        assertEquals(0, calculateWaterAmount(new int[] {2, 16_536, HIGHEST_HEIGHT, 16_536, 2}));
    }

    @Test
    public void maxAmount() {
        int[] landscape = new int[MAX_POSITIONS_NUMBER];
        landscape[0] = HIGHEST_HEIGHT;
        landscape[MAX_POSITIONS_NUMBER - 1] = HIGHEST_HEIGHT;
        long expected = (MAX_POSITIONS_NUMBER - 2) * HIGHEST_HEIGHT;
        assertEquals(expected, calculateWaterAmount(landscape));
    }

    @Test
    public void fromTaskDescription() {
        assertEquals(9, calculateWaterAmount(new int[] {5, 2, 3, 4, 5, 4, 0, 3, 1}));
    }

    @Test
    public void test1() {
        assertEquals(480, calculateWaterAmount(new int[] {256, 16, HIGHEST_HEIGHT, 16, 256}));
    }

    @Test
    public void test2() {
        assertEquals(1334, calculateWaterAmount(
                new int[] {8, 32, 4, 2, 64, 256, LOWEST_HEIGHT, 512, 2, 2, HIGHEST_HEIGHT}));
    }

    @Test
    public void test3() {
        assertEquals(540, calculateWaterAmount(new int[] {64, 4, 256, 16, 256, 256, 16, 256, LOWEST_HEIGHT}));
    }

}
