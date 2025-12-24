package com.pomodorotimer.pomodorotimer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for HashTable implementation.
 * Tests hash function, collision handling (chaining), and operations.
 */
@DisplayName("HashTable Tests")
class HashTableTest {
    
    private HashTable<String, Integer> hashTable;
    
    @BeforeEach
    void setUp() {
        hashTable = new HashTable<>();
    }
    
    @Test
    @DisplayName("Test empty hash table")
    void testEmptyHashTable() {
        assertTrue(hashTable.isEmpty());
        assertEquals(0, hashTable.size());
        assertNull(hashTable.get("nonexistent"));
    }
    
    @Test
    @DisplayName("Test put and get")
    void testPutAndGet() {
        hashTable.put("key1", 100);
        hashTable.put("key2", 200);
        hashTable.put("key3", 300);
        
        assertEquals(100, hashTable.get("key1"));
        assertEquals(200, hashTable.get("key2"));
        assertEquals(300, hashTable.get("key3"));
        assertEquals(3, hashTable.size());
    }
    
    @Test
    @DisplayName("Test update existing key")
    void testUpdateExistingKey() {
        hashTable.put("key1", 100);
        hashTable.put("key1", 200);
        
        assertEquals(200, hashTable.get("key1"));
        assertEquals(1, hashTable.size());
    }
    
    @Test
    @DisplayName("Test remove")
    void testRemove() {
        hashTable.put("key1", 100);
        hashTable.put("key2", 200);
        
        assertEquals(100, hashTable.remove("key1"));
        assertNull(hashTable.get("key1"));
        assertEquals(1, hashTable.size());
    }
    
    @Test
    @DisplayName("Test containsKey")
    void testContainsKey() {
        hashTable.put("key1", 100);
        
        assertTrue(hashTable.containsKey("key1"));
        assertFalse(hashTable.containsKey("nonexistent"));
    }
    
    @Test
    @DisplayName("Test collision handling")
    void testCollisionHandling() {
        // Create hash table with small capacity to force collisions
        HashTable<String, Integer> smallTable = new HashTable<>(2);
        
        smallTable.put("a", 1);
        smallTable.put("b", 2);
        smallTable.put("c", 3);
        smallTable.put("d", 4);
        
        assertEquals(1, smallTable.get("a"));
        assertEquals(2, smallTable.get("b"));
        assertEquals(3, smallTable.get("c"));
        assertEquals(4, smallTable.get("d"));
    }
    
    @Test
    @DisplayName("Test clear")
    void testClear() {
        hashTable.put("key1", 100);
        hashTable.put("key2", 200);
        
        hashTable.clear();
        assertTrue(hashTable.isEmpty());
        assertEquals(0, hashTable.size());
        assertNull(hashTable.get("key1"));
    }
    
    @Test
    @DisplayName("Test key-value database use-case")
    void testKeyValueDatabase() {
        // Simulate simple key-value database
        hashTable.put("user1", 1001);
        hashTable.put("user2", 1002);
        hashTable.put("user3", 1003);
        
        assertEquals(1001, hashTable.get("user1"));
        assertEquals(1002, hashTable.get("user2"));
        assertEquals(1003, hashTable.get("user3"));
        
        hashTable.put("user1", 2001); // Update
        assertEquals(2001, hashTable.get("user1"));
    }
    
    @Test
    @DisplayName("Test resize functionality")
    void testResize() {
        // Add many elements to trigger resize
        for (int i = 0; i < 20; i++) {
            hashTable.put("key" + i, i);
        }
        
        assertEquals(20, hashTable.size());
        for (int i = 0; i < 20; i++) {
            assertEquals(i, hashTable.get("key" + i));
        }
    }
}


