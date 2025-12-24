package com.pomodorotimer.pomodorotimer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

/**
 * Unit tests for XORLinkedList implementation.
 * Tests insertion, deletion, and bidirectional navigation.
 */
@DisplayName("XORLinkedList Tests")
class XORLinkedListTest {
    
    private XORLinkedList<String> list;
    
    @BeforeEach
    void setUp() {
        list = new XORLinkedList<>();
    }
    
    @Test
    @DisplayName("Test empty list")
    void testEmptyList() {
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
        assertThrows(NoSuchElementException.class, () -> list.getFirst());
        assertThrows(NoSuchElementException.class, () -> list.getLast());
    }
    
    @Test
    @DisplayName("Test addFirst")
    void testAddFirst() {
        list.addFirst("Song1");
        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
        assertEquals("Song1", list.getFirst());
        assertEquals("Song1", list.getLast());
        
        list.addFirst("Song2");
        assertEquals(2, list.size());
        assertEquals("Song2", list.getFirst());
        assertEquals("Song1", list.getLast());
    }
    
    @Test
    @DisplayName("Test addLast")
    void testAddLast() {
        list.addLast("Song1");
        assertEquals(1, list.size());
        assertEquals("Song1", list.getFirst());
        assertEquals("Song1", list.getLast());
        
        list.addLast("Song2");
        assertEquals(2, list.size());
        assertEquals("Song1", list.getFirst());
        assertEquals("Song2", list.getLast());
    }
    
    @Test
    @DisplayName("Test removeFirst")
    void testRemoveFirst() {
        list.addLast("Song1");
        list.addLast("Song2");
        list.addLast("Song3");
        
        assertEquals("Song1", list.removeFirst());
        assertEquals(2, list.size());
        assertEquals("Song2", list.getFirst());
        
        assertEquals("Song2", list.removeFirst());
        assertEquals(1, list.size());
        assertEquals("Song3", list.getFirst());
        
        assertEquals("Song3", list.removeFirst());
        assertTrue(list.isEmpty());
        assertThrows(NoSuchElementException.class, () -> list.removeFirst());
    }
    
    @Test
    @DisplayName("Test single element")
    void testSingleElement() {
        list.addFirst("Single");
        assertEquals(1, list.size());
        assertEquals("Single", list.getFirst());
        assertEquals("Single", list.getLast());
        
        assertEquals("Single", list.removeFirst());
        assertTrue(list.isEmpty());
    }
    
    @Test
    @DisplayName("Test clear")
    void testClear() {
        list.addLast("Song1");
        list.addLast("Song2");
        list.addLast("Song3");
        
        list.clear();
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
    }
    
    @Test
    @DisplayName("Test playlist use-case")
    void testPlaylistUseCase() {
        // Simulate playlist operations
        list.addLast("Song1");
        list.addLast("Song2");
        list.addLast("Song3");
        
        assertEquals("Song1", list.getFirst());
        assertEquals("Song3", list.getLast());
        assertEquals(3, list.size());
    }
}


