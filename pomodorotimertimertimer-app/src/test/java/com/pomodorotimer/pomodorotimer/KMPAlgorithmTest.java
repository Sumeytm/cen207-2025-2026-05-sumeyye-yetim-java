package com.pomodorotimer.pomodorotimer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

/**
 * Unit tests for KMPAlgorithm implementation.
 * Tests pattern matching and performance on large texts.
 */
@DisplayName("KMP Algorithm Tests")
class KMPAlgorithmTest {
    
    private KMPAlgorithm kmp;
    
    @BeforeEach
    void setUp() {
        kmp = new KMPAlgorithm();
    }
    
    @Test
    @DisplayName("Test pattern found in text")
    void testPatternFound() {
        String text = "ABABDABACDABABCABCAB";
        String pattern = "ABABCAB";
        
        List<Integer> result = kmp.search(text, pattern);
        assertFalse(result.isEmpty());
        assertEquals(10, result.get(0)); // Pattern found at index 10
    }
    
    @Test
    @DisplayName("Test pattern not found")
    void testPatternNotFound() {
        String text = "ABCDEFG";
        String pattern = "XYZ";
        
        List<Integer> result = kmp.search(text, pattern);
        assertTrue(result.isEmpty());
    }
    
    @Test
    @DisplayName("Test multiple occurrences")
    void testMultipleOccurrences() {
        String text = "ABABABAB";
        String pattern = "ABAB";
        
        List<Integer> result = kmp.search(text, pattern);
        assertEquals(3, result.size());
        assertEquals(0, result.get(0));
        assertEquals(2, result.get(1));
        assertEquals(4, result.get(2));
    }
    
    @Test
    @DisplayName("Test contains method")
    void testContains() {
        assertTrue(kmp.contains("Hello World", "World"));
        assertFalse(kmp.contains("Hello World", "XYZ"));
    }
    
    @Test
    @DisplayName("Test findFirst method")
    void testFindFirst() {
        assertEquals(6, kmp.findFirst("Hello World", "World"));
        assertEquals(-1, kmp.findFirst("Hello World", "XYZ"));
    }
    
    @Test
    @DisplayName("Test empty pattern")
    void testEmptyPattern() {
        List<Integer> result = kmp.search("Hello", "");
        assertTrue(result.isEmpty());
    }
    
    @Test
    @DisplayName("Test null text")
    void testNullText() {
        List<Integer> result = kmp.search(null, "pattern");
        assertTrue(result.isEmpty());
    }
    
    @Test
    @DisplayName("Test null pattern")
    void testNullPattern() {
        List<Integer> result = kmp.search("Hello", null);
        assertTrue(result.isEmpty());
    }
    
    @Test
    @DisplayName("Test null text and pattern")
    void testNullTextAndPattern() {
        List<Integer> result = kmp.search(null, null);
        assertTrue(result.isEmpty());
    }
    
    @Test
    @DisplayName("Test pattern equals text")
    void testPatternEqualsText() {
        String text = "ABC";
        List<Integer> result = kmp.search(text, text);
        assertEquals(1, result.size());
        assertEquals(0, result.get(0));
    }
    
    @Test
    @DisplayName("Test text pattern search use-case")
    void testTextPatternSearch() {
        String text = "The quick brown fox jumps over the lazy dog";
        String pattern = "fox";
        
        assertTrue(kmp.contains(text, pattern));
        assertEquals(16, kmp.findFirst(text, pattern));
    }
    
    @Test
    @DisplayName("Test performance on repeated pattern")
    void testPerformanceRepeatedPattern() {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            text.append("ABC");
        }
        String pattern = "ABC";
        
        List<Integer> result = kmp.search(text.toString(), pattern);
        assertEquals(1000, result.size());
    }
}

