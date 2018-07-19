package com.spoloborota.water;

import java.util.Arrays;

public class WaterCalculator {

    static final int LOWEST_HEIGHT = 0;
    static final int HIGHEST_HEIGHT = 32_000;
    static final int MAX_POSITIONS_NUMBER = 32_000;

    static long calculateWaterAmount(int[] landscape) {
        if (isLandscapeCorrect(landscape)) {
            int leftMax = 0;
            int rightMax = 0;
            int left = 0;
            int right = landscape.length - 1;
            int volume = 0;

            while (left < right) {
                if (landscape[left] > leftMax) {
                    leftMax = landscape[left];
                }
                if (landscape[right] > rightMax) {
                    rightMax = landscape[right];
                }
                if (leftMax >= rightMax) {
                    volume += rightMax - landscape[right];
                    right--;
                } else {
                    volume += leftMax - landscape[left];
                    left++;
                }
            }
            return volume;
        } else {
            throw new LandscapeValidationException("Wrong data in landscape array: " + Arrays.toString(landscape));
        }
    }

    private static boolean isLandscapeCorrect(int[] landscape) {
        if (landscape.length > MAX_POSITIONS_NUMBER) {
            return false;
        } else {
            return Arrays.stream(landscape).parallel()
                    .allMatch((e) -> e >= LOWEST_HEIGHT && e <= HIGHEST_HEIGHT);
        }
    }

    static class LandscapeValidationException extends RuntimeException {
        LandscapeValidationException(String message) {
            super(message);
        }
    }
}
