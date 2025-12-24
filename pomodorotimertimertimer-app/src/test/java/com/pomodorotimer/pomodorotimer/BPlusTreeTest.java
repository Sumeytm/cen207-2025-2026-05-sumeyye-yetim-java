package com.pomodorotimer.pomodorotimer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

/**
 * Unit tests for BPlusTree implementation.
 * Tests balanced operations and search efficiency.
 */
@DisplayName("B+ Tree Tests")
class BPlusTreeTest {
    
    private BPlusTree tree;
    
    @BeforeEach
    void setUp() {
        tree = new BPlusTree();
    }
    
    @Test
    @DisplayName("Test insert and search")
    void testInsertAndSearch() {
        tree.insert(1, "value1");
        tree.insert(2, "value2");
        tree.insert(3, "value3");
        
        assertEquals("value1", tree.search(1));
        assertEquals("value2", tree.search(2));
        assertEquals("value3", tree.search(3));
    }
    
    @Test
    @DisplayName("Test search non-existent key")
    void testSearchNonExistent() {
        tree.insert(1, "value1");
        assertNull(tree.search(999));
    }
    
    @Test
    @DisplayName("Test update existing key")
    void testUpdateExistingKey() {
        tree.insert(1, "value1");
        tree.insert(1, "updated");
        
        assertEquals("updated", tree.search(1));
    }
    
    @Test
    @DisplayName("Test range query")
    void testRangeQuery() {
        tree.insert(1, "value1");
        tree.insert(2, "value2");
        tree.insert(3, "value3");
        tree.insert(4, "value4");
        tree.insert(5, "value5");
        
        List<String> result = tree.rangeQuery(2, 4);
        assertEquals(3, result.size());
        assertTrue(result.contains("value2"));
        assertTrue(result.contains("value3"));
        assertTrue(result.contains("value4"));
    }
    
    @Test
    @DisplayName("Test balanced tree")
    void testBalancedTree() {
        for (int i = 1; i <= 10; i++) {
            tree.insert(i, "value" + i);
        }
        
        assertTrue(tree.isBalanced());
    }
    
    @Test
    @DisplayName("Test file indexing use-case")
    void testFileIndexing() {
        // Simulate file indexing
        tree.insert(100, "file1.txt");
        tree.insert(200, "file2.txt");
        tree.insert(300, "file3.txt");
        
        assertEquals("file1.txt", tree.search(100));
        assertEquals("file2.txt", tree.search(200));
        assertEquals("file3.txt", tree.search(300));
        
        List<String> files = tree.rangeQuery(150, 250);
        assertTrue(files.contains("file2.txt"));
    }
    
    @Test
    @DisplayName("Test constructor with custom order")
    void testConstructorWithOrder() {
        BPlusTree customTree = new BPlusTree(5);
        customTree.insert(1, "value1");
        customTree.insert(2, "value2");
        
        assertEquals("value1", customTree.search(1));
        assertEquals("value2", customTree.search(2));
    }
    
    @Test
    @DisplayName("Test range query with empty result")
    void testRangeQueryEmptyResult() {
        tree.insert(1, "value1");
        tree.insert(5, "value5");
        
        List<String> result = tree.rangeQuery(10, 20);
        assertTrue(result.isEmpty());
    }
    
    @Test
    @DisplayName("Test range query returning all results")
    void testRangeQueryAllResults() {
        tree.insert(1, "value1");
        tree.insert(2, "value2");
        tree.insert(3, "value3");
        tree.insert(4, "value4");
        tree.insert(5, "value5");
        
        List<String> result = tree.rangeQuery(1, 5);
        assertEquals(5, result.size());
    }
}


