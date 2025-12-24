package com.pomodorotimer.pomodorotimer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for SparseMatrix implementation.
 * Tests non-zero element insertion, memory usage, and value retrieval.
 */
@DisplayName("SparseMatrix Tests")
class SparseMatrixTest {
    
    private SparseMatrix matrix;
    
    @BeforeEach
    void setUp() {
        matrix = new SparseMatrix(10, 10);
    }
    
    @Test
    @DisplayName("Test empty matrix")
    void testEmptyMatrix() {
        assertEquals(0, matrix.getNonZeroCount());
        assertEquals(0, matrix.get(0, 0));
        assertEquals(0, matrix.get(5, 5));
    }
    
    @Test
    @DisplayName("Test set and get values")
    void testSetAndGet() {
        matrix.set(0, 0, 5);
        assertEquals(5, matrix.get(0, 0));
        assertEquals(1, matrix.getNonZeroCount());
        
        matrix.set(1, 1, 10);
        assertEquals(10, matrix.get(1, 1));
        assertEquals(2, matrix.getNonZeroCount());
        
        matrix.set(0, 0, 7);
        assertEquals(7, matrix.get(0, 0));
        assertEquals(2, matrix.getNonZeroCount());
    }
    
    @Test
    @DisplayName("Test zero values are not stored")
    void testZeroValues() {
        matrix.set(0, 0, 5);
        assertEquals(1, matrix.getNonZeroCount());
        
        matrix.set(0, 0, 0);
        assertEquals(0, matrix.getNonZeroCount());
        assertEquals(0, matrix.get(0, 0));
    }
    
    @Test
    @DisplayName("Test index out of bounds")
    void testIndexOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> matrix.set(-1, 0, 5));
        assertThrows(IndexOutOfBoundsException.class, () -> matrix.set(0, -1, 5));
        assertThrows(IndexOutOfBoundsException.class, () -> matrix.set(10, 0, 5));
        assertThrows(IndexOutOfBoundsException.class, () -> matrix.set(0, 10, 5));
    }
    
    @Test
    @DisplayName("Test memory usage")
    void testMemoryUsage() {
        matrix.set(0, 0, 1);
        matrix.set(1, 1, 2);
        matrix.set(2, 2, 3);
        
        assertEquals(3, matrix.getMemoryUsage());
        assertEquals(3, matrix.getNonZeroCount());
    }
    
    @Test
    @DisplayName("Test clear")
    void testClear() {
        matrix.set(0, 0, 1);
        matrix.set(1, 1, 2);
        matrix.set(2, 2, 3);
        
        matrix.clear();
        assertEquals(0, matrix.getNonZeroCount());
        assertEquals(0, matrix.get(0, 0));
    }
    
    @Test
    @DisplayName("Test game grid use-case")
    void testGameGridUseCase() {
        // Simulate game grid with few objects
        matrix.set(2, 3, 100); // Player
        matrix.set(5, 7, 200); // Enemy
        matrix.set(8, 1, 300); // Treasure
        
        assertEquals(100, matrix.get(2, 3));
        assertEquals(200, matrix.get(5, 7));
        assertEquals(300, matrix.get(8, 1));
        assertEquals(0, matrix.get(0, 0)); // Empty cell
        assertEquals(3, matrix.getNonZeroCount());
    }
    
    @Test
    @DisplayName("Test matrix dimensions")
    void testMatrixDimensions() {
        SparseMatrix small = new SparseMatrix(5, 5);
        assertEquals(5, small.getRows());
        assertEquals(5, small.getCols());
        
        SparseMatrix large = new SparseMatrix(1000, 1000);
        assertEquals(1000, large.getRows());
        assertEquals(1000, large.getCols());
    }
    
    @Test
    @DisplayName("Test invalid dimensions")
    void testInvalidDimensions() {
        assertThrows(IllegalArgumentException.class, () -> new SparseMatrix(-1, 5));
        assertThrows(IllegalArgumentException.class, () -> new SparseMatrix(5, -1));
    }
}


