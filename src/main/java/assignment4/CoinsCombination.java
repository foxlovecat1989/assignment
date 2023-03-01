package assignment4;

import java.util.*;

class CoinsCombination {

//    4. Return The Coins Combination With The Minimum Number Of Coins
//    Given a value V, if we want to make change for V cents,
//    and we have an infinite supply of each of C = { C1, C2, .. , Cm} valued coins,
//    what is the minimum number of coins to make the change?

//    For example:
//    ● Given coins[] = {25, 10, 5}, V = 30, the answer is 2 coins, which is combined with
//      one coin of 25 cents and one of 5 cents
//    ● Given coins[] = {9, 6, 5, 1}, V = 11, the answer is 2 coins, which is combined with
//    one coin of 6 cents and one of 5 cents
//    Please implement a java program to solve this problem.

    public static void main(String[] args) {
        new CoinsCombination().process();
    }
    public void process() {
        int[] setA = {25, 10, 5};
        int expectedValueA = 30;
        System.out.println("最小需求數: " + minCoins(setA, expectedValueA));

        int[] setB = {9, 6, 5, 1};
        int expectedValueB = 11;
        System.out.println("最小需求數: " + minCoins(setB, expectedValueB));

        try {
            int[] setC = {};
            int expectedValueC = 10;
            System.out.println("最小需求數: " + minCoins(setC, expectedValueC));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            int[] setD = {3, 10, 12, 13};
            int expectedValueD = -1;
            System.out.println("最小需求數: " + minCoins(setD, expectedValueD));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            int[] setD = {3, 8, 21, 2};
            int expectedValueD = 40;
            System.out.println("最小需求數: " + minCoins(setD, expectedValueD));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            int[] setE = {5, 7, 2, 1};
            int expectedValueE = 10;
            System.out.println("最小需求數: " + minCoins(setE, expectedValueE));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public int minCoins(int[] coins, int expectedValue) {
        checkIsInputValid(coins, expectedValue);
        int[] record = new int[expectedValue + 1];
        Arrays.fill(record, -1);

        return getMinCombination(coins, expectedValue, record);
    }

    private int getMinCombination(int[] elements, int expectedValue, int[] record) {
        if (expectedValue == 0)
            return 0;

        if (record[expectedValue] != -1)
            return record[expectedValue];

        int minCoins = Integer.MAX_VALUE;

        for (int element : elements) {
            if (expectedValue >= element) {
                int[] remainingElements = getRemainingCoins(elements, element);
                int subProblemElements =
                        getMinCombination(remainingElements, expectedValue - element, record);
                if (subProblemElements != Integer.MAX_VALUE) {
                    minCoins = Math.min(minCoins, subProblemElements + 1);
                }
            }
        }

        record[expectedValue] = minCoins;

        return record[expectedValue];
    }

    private static int[] getRemainingCoins(int[] elements, int element) {
        return Arrays.stream(elements)
                .filter(c -> element != c)
                .toArray();
    }

    private void checkIsInputValid(int[] set, int expectedValue) {
        boolean isEmptySet = Optional.ofNullable(set).isEmpty() || set.length == 0;
        if (isEmptySet)
            throw new IllegalArgumentException("輸入的集合不可為空");
        if (expectedValue < 0)
            throw new IllegalArgumentException("期望數字不可為負數");
        int amount = Arrays.stream(set).sum();
        boolean isAmountLessThanExpectedValue = amount - expectedValue < 0;
        if (isAmountLessThanExpectedValue)
            throw new IllegalArgumentException("期望數字不可能達到");
    }
}
