package com.pomodorotimer.pomodorotimer;

/**
 * File Operations implementation using Linear Probing for hash table.
 * Used for fast file search operations with collision handling.
 * 
 * <p>Use-case: Fast file search operations where we need to quickly
 * locate files by their keys using linear probing for collision resolution.
 * 
 * <p>Time Complexity:
 * <ul>
 *   <li>Insert: O(1) average, O(n) worst case</li>
 *   <li>Search: O(1) average, O(n) worst case</li>
 *   <li>Delete: O(1) average, O(n) worst case</li>
 * </ul>
 * 
 * <p>Space Complexity: O(n) where n is the capacity
 * 
 * @author Data Structures Project Team
 * @version 1.0
 */
public class FileOperations {
    
    /**
     * Inner class representing a file entry.
     */
    private static class FileEntry {
        String key;
        String value;
        boolean deleted;
        
        FileEntry(String key, String value) {
            this.key = key;
            this.value = value;
            this.deleted = false;
        }
    }
    
    private static final int DEFAULT_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;
    
    private FileEntry[] table;
    private int size;
    private int capacity;
    
    /**
     * Constructs a file operations hash table with default capacity.
     */
    public FileOperations() {
        this.capacity = DEFAULT_CAPACITY;
        this.table = new FileEntry[capacity];
        this.size = 0;
    }
    
    /**
     * Constructs a file operations hash table with specified capacity.
     * 
     * @param capacity initial capacity
     */
    public FileOperations(int capacity) {
        this.capacity = capacity;
        this.table = new FileEntry[capacity];
        this.size = 0;
    }
    
    private int hash(String key) {
        return Math.abs(key.hashCode()) % capacity;
    }
    
    /**
     * Inserts a key-value pair using linear probing.
     * 
     * @param key the key
     * @param value the value
     */
    public void put(String key, String value) {
        if ((double) size / capacity > LOAD_FACTOR) {
            resize();
        }
        
        int index = hash(key);
        int originalIndex = index;
        
        // Linear probing
        while (table[index] != null && !table[index].deleted) {
            if (table[index].key.equals(key)) {
                // Update existing
                table[index].value = value;
                return;
            }
            index = (index + 1) % capacity;
            if (index == originalIndex) {
                // Table is full
                resize();
                index = hash(key);
                originalIndex = index;
            }
        }
        
        table[index] = new FileEntry(key, value);
        size++;
    }
    
    /**
     * Retrieves a value by key using linear probing.
     * 
     * @param key the key
     * @return the value, or null if not found
     */
    public String get(String key) {
        int index = hash(key);
        int originalIndex = index;
        
        while (table[index] != null) {
            if (!table[index].deleted && table[index].key.equals(key)) {
                return table[index].value;
            }
            index = (index + 1) % capacity;
            if (index == originalIndex) {
                break;
            }
        }
        
        return null;
    }
    
    /**
     * Removes a key-value pair using linear probing.
     * 
     * @param key the key to remove
     * @return the value that was removed, or null if not found
     */
    public String remove(String key) {
        int index = hash(key);
        int originalIndex = index;
        
        while (table[index] != null) {
            if (!table[index].deleted && table[index].key.equals(key)) {
                String value = table[index].value;
                table[index].deleted = true;
                size--;
                return value;
            }
            index = (index + 1) % capacity;
            if (index == originalIndex) {
                break;
            }
        }
        
        return null;
    }
    
    /**
     * Checks if a key exists.
     * 
     * @param key the key to check
     * @return true if key exists, false otherwise
     */
    public boolean containsKey(String key) {
        return get(key) != null;
    }
    
    /**
     * Returns the number of entries.
     * 
     * @return the size
     */
    public int size() {
        return size;
    }
    
    /**
     * Checks if the table is empty.
     * 
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }
    
    /**
     * Removes all entries.
     */
    public void clear() {
        table = new FileEntry[capacity];
        size = 0;
    }
    
    private void resize() {
        int oldCapacity = capacity;
        FileEntry[] oldTable = table;
        capacity *= 2;
        table = new FileEntry[capacity];
        size = 0;
        for (int i = 0; i < oldCapacity; i++) {
            if (oldTable[i] != null && !oldTable[i].deleted) {
                put(oldTable[i].key, oldTable[i].value);
            }
        }
    }
    
    /**
     * Measures search time for a key (simplified).
     * 
     * @param key the key to search for
     * @return number of probes needed
     */
    public int getSearchTime(String key) {
        int index = hash(key);
        int originalIndex = index;
        int probes = 0;
        
        // Always check at least the first index
        do {
            probes++;
            if (table[index] != null && !table[index].deleted && table[index].key.equals(key)) {
                return probes;
            }
            index = (index + 1) % capacity;
            if (index == originalIndex) {
                break;
            }
        } while (table[index] != null);
        
        return probes;
    }
}

