package com.pomodorotimer.pomodorotimer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

/**
 * Unit tests for MinHeap and HeapSort implementations.
 * Tests heap property maintenance and priority sorting.
 */
@DisplayName("Heap and HeapSort Tests")
class HeapTest {
    
    private MinHeap heap;
    
    @BeforeEach
    void setUp() {
        heap = new MinHeap();
    }
    
    @Test
    @DisplayName("Test empty heap")
    void testEmptyHeap() {
        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());
        assertThrows(NoSuchElementException.class, () -> heap.peek());
        assertThrows(NoSuchElementException.class, () -> heap.extractMin());
    }
    
    @Test
    @DisplayName("Test insert and extract min")
    void testInsertAndExtractMin() {
        heap.insert(5);
        heap.insert(3);
        heap.insert(7);
        heap.insert(1);
        heap.insert(9);
        
        assertEquals(1, heap.extractMin());
        assertEquals(3, heap.extractMin());
        assertEquals(5, heap.extractMin());
        assertEquals(7, heap.extractMin());
        assertEquals(9, heap.extractMin());
    }
    
    @Test
    @DisplayName("Test heap property maintenance")
    void testHeapProperty() {
        heap.insert(10);
        heap.insert(5);
        heap.insert(15);
        heap.insert(3);
        heap.insert(7);
        
        assertTrue(heap.isHeapPropertyMaintained());
        assertEquals(3, heap.peek());
    }
    
    @Test
    @DisplayName("Test heap from array")
    void testHeapFromArray() {
        int[] array = {10, 5, 15, 3, 7};
        MinHeap heapFromArray = new MinHeap(array);
        
        assertTrue(heapFromArray.isHeapPropertyMaintained());
        assertEquals(3, heapFromArray.extractMin());
    }
    
    @Test
    @DisplayName("Test heap sort")
    void testHeapSort() {
        int[] array = {64, 34, 25, 12, 22, 11, 90};
        int[] expected = {11, 12, 22, 25, 34, 64, 90};
        
        HeapSort.sort(array);
        assertArrayEquals(expected, array);
    }
    
    @Test
    @DisplayName("Test heap sort with already sorted array")
    void testHeapSortSorted() {
        int[] array = {1, 2, 3, 4, 5};
        int[] expected = {1, 2, 3, 4, 5};
        
        HeapSort.sort(array);
        assertArrayEquals(expected, array);
    }
    
    @Test
    @DisplayName("Test heap sort with reverse sorted array")
    void testHeapSortReverse() {
        int[] array = {5, 4, 3, 2, 1};
        int[] expected = {1, 2, 3, 4, 5};
        
        HeapSort.sort(array);
        assertArrayEquals(expected, array);
    }
    
    @Test
    @DisplayName("Test heap sort with single element")
    void testHeapSortSingle() {
        int[] array = {42};
        int[] expected = {42};
        
        HeapSort.sort(array);
        assertArrayEquals(expected, array);
    }
    
    @Test
    @DisplayName("Test priority task manager use-case")
    void testPriorityTaskManager() {
        // Simulate priority task manager
        heap.insert(5); // Low priority
        heap.insert(1); // High priority
        heap.insert(3); // Medium priority
        heap.insert(2); // High priority
        
        // Tasks should be processed by priority (lower number = higher priority)
        assertEquals(1, heap.extractMin());
        assertEquals(2, heap.extractMin());
        assertEquals(3, heap.extractMin());
        assertEquals(5, heap.extractMin());
    }
}


