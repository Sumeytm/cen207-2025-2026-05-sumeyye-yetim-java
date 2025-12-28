package com.pomodorotimer.pomodorotimer;

import java.util.ArrayList;
import java.util.List;

/**
 * Knuth-Morris-Pratt (KMP) algorithm for efficient pattern matching.
 * Used for efficient text pattern search.
 * 
 * <p>Use-case: Efficient text pattern search tool where we need to find
 * all occurrences of a pattern in a text efficiently.</p>
 * 
 * <p>Time Complexity: O(n + m) where n is text length and m is pattern length</p>
 * <p>Space Complexity: O(m) for the prefix table</p>
 * 
 * @author Data Structures Project Team
 * @version 1.0
 */
public class KMPAlgorithm {
    
    /**
     * Constructs a new KMPAlgorithm instance.
     */
    public KMPAlgorithm() {
        // Default constructor
    }
    
    /**
     * Finds all occurrences of pattern in text using KMP algorithm.
     * 
     * @param text the text to search in
     * @param pattern the pattern to search for
     * @return list of starting indices where pattern is found
     */
    public List<Integer> search(String text, String pattern) {
        List<Integer> result = new ArrayList<>();
        if (text == null || pattern == null || pattern.isEmpty()) {
            return result;
        }
        
        int[] lps = computeLPS(pattern);
        int i = 0; // index for text
        int j = 0; // index for pattern
        
        while (i < text.length()) {
            if (pattern.charAt(j) == text.charAt(i)) {
                i++;
                j++;
            }
            
            if (j == pattern.length()) {
                result.add(i - j);
                j = lps[j - 1];
            } else if (i < text.length() && pattern.charAt(j) != text.charAt(i)) {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }
        
        return result;
    }
    
    private int[] computeLPS(String pattern) {
        int[] lps = new int[pattern.length()];
        int len = 0;
        int i = 1;
        lps[0] = 0;
        
        while (i < pattern.length()) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        
        return lps;
    }
    
    /**
     * Checks if pattern exists in text.
     * 
     * @param text the text to search in
     * @param pattern the pattern to search for
     * @return true if pattern is found, false otherwise
     */
    public boolean contains(String text, String pattern) {
        return !search(text, pattern).isEmpty();
    }
    
    /**
     * Finds the first occurrence of pattern in text.
     * 
     * @param text the text to search in
     * @param pattern the pattern to search for
     * @return index of first occurrence, or -1 if not found
     */
    public int findFirst(String text, String pattern) {
        List<Integer> occurrences = search(text, pattern);
        return occurrences.isEmpty() ? -1 : occurrences.get(0);
    }
    
    /**
     * Finds all occurrences of pattern in text (alias for search method).
     * 
     * @param text the text to search in
     * @param pattern the pattern to search for
     * @return list of starting indices where pattern is found
     */
    public List<Integer> findAll(String text, String pattern) {
        return search(text, pattern);
    }
}

