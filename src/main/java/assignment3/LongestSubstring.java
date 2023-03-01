package assignment3;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

class LongestSubstring {

//    3. Longest Substring Without Repeating Characters
//    Given a string, find the length of the longest substring without repeating characters.
//    For example:
//            ● Given "abcabcbb", the answer is "abc", which the length is 3.
//            ● Given "bbbbb", the answer is "b", with the length of 1.
//            ● Given "pwwkew", the answer is "wke", with the length of 3.
//    Please implement a java program to deal with this.

    public static void main(String[] args) {
        String setA = "abcabcbb";
        String setB = "bbbbb";
        String setC = "pwwkew";
        System.out.println(maxLengthOfLongestSubstring(setA)); // 3
        System.out.println(maxLengthOfLongestSubstring(setB)); // 1
        System.out.println(maxLengthOfLongestSubstring(setC)); // 3
    }
    public static int maxLengthOfLongestSubstring(String input) {
        boolean isEmptyInput = input.equals("");
        if (isEmptyInput)
            return 0;

        int lengthOfInput = input.length();
        Set<Character> currentSet = new LinkedHashSet<>();
        int currentMaxLength = 0;
        int headPointer = 0;
        int currentPointer = 0;
        while (currentPointer < lengthOfInput) {
            char currentElement = input.charAt(currentPointer);
            boolean isElementInSet = currentSet.contains(currentElement);
            if (!isElementInSet) {
                currentSet.add(currentElement);
            } else {
                currentSet = new HashSet<>();
                currentSet.add(currentElement);
                headPointer = currentPointer;
            }

            currentPointer ++;

            int distance = currentPointer - headPointer;
            boolean isDistanceGreaterThanCurrentMasLength = distance > currentMaxLength;
            if (isDistanceGreaterThanCurrentMasLength)
                currentMaxLength = distance;
        }


        return currentMaxLength;
    }
}

