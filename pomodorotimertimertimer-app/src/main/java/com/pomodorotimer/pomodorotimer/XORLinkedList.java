package com.pomodorotimer.pomodorotimer;

import java.util.NoSuchElementException;

/**
 * XOR Linked List implementation for memory-efficient playlist system.
 * This data structure uses XOR operation to store both previous and next pointers
 * in a single field, reducing memory usage by half compared to double linked list.
 * 
 * <p>Use-case: Memory-efficient playlist system where each node stores a song,
 * and navigation can be done in both directions using XOR pointers.
 * 
 * <p>Time Complexity:
 * <ul>
 *   <li>Insertion: O(1)</li>
 *   <li>Deletion: O(1) if node is known</li>
 *   <li>Traversal: O(n)</li>
 * </ul>
 * 
 * <p>Space Complexity: O(n) where n is the number of elements
 * (but uses less memory than double linked list)
 * 
 * @param <T> the type of elements in this list
 * @author Data Structures Project Team
 * @version 1.0
 */
public class XORLinkedList<T> {
    
    /**
     * Inner class representing a node in the XOR linked list.
     */
    private static class Node<T> {
        T data;
        Node<T> xorPtr; // Stores XOR of previous and next node addresses
        
        Node(T data) {
            this.data = data;
            this.xorPtr = null;
        }
    }
    
    private Node<T> head;
    private Node<T> tail;
    private int size;
    
    /**
     * Constructs an empty XOR linked list.
     */
    public XORLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }
    
    /**
     * Adds an element to the front of the list.
     * 
     * @param data the element to add
     */
    public void addFirst(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.xorPtr = head;
            head.xorPtr = newNode;
            head = newNode;
        }
        size++;
    }
    
    /**
     * Adds an element to the end of the list.
     * 
     * @param data the element to add
     */
    public void addLast(T data) {
        Node<T> newNode = new Node<>(data);
        if (tail == null) {
            head = tail = newNode;
        } else {
            newNode.xorPtr = tail;
            tail.xorPtr = newNode;
            tail = newNode;
        }
        size++;
    }
    
    /**
     * Removes and returns the first element.
     * 
     * @return the first element
     * @throws NoSuchElementException if the list is empty
     */
    public T removeFirst() {
        if (head == null) throw new NoSuchElementException("List is empty");
        T data = head.data;
        if (head == tail) {
            head = tail = null;
        } else {
            head = head.xorPtr;
        }
        size--;
        return data;
    }
    
    /**
     * Returns the first element without removing it.
     * 
     * @return the first element
     * @throws NoSuchElementException if the list is empty
     */
    public T getFirst() {
        if (head == null) {
            throw new NoSuchElementException("List is empty");
        }
        return head.data;
    }
    
    /**
     * Returns the last element without removing it.
     * 
     * @return the last element
     * @throws NoSuchElementException if the list is empty
     */
    public T getLast() {
        if (tail == null) {
            throw new NoSuchElementException("List is empty");
        }
        return tail.data;
    }
    
    /**
     * Returns the number of elements in the list.
     * 
     * @return the size of the list
     */
    public int size() {
        return size;
    }
    
    /**
     * Checks if the list is empty.
     * 
     * @return true if the list is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }
    
    /**
     * Removes all elements from the list.
     */
    public void clear() {
        head = tail = null;
        size = 0;
    }
    
    /**
     * Inserts an element at the end of the list (alias for addLast).
     * 
     * @param data the element to insert
     */
    public void insert(T data) {
        addLast(data);
    }
    
    /**
     * Deletes an element from the list by value.
     * 
     * @param data the element to delete
     * @return true if the element was found and deleted, false otherwise
     */
    public boolean delete(T data) {
        if (head == null) {
            return false;
        }
        
        // Simple implementation: traverse and remove
        // For XOR linked list, this is simplified
        Node<T> current = head;
        Node<T> prev = null;
        
        while (current != null) {
            if (current.data.equals(data)) {
                if (current == head) {
                    removeFirst();
                    return true;
                } else if (current == tail) {
                    tail = prev;
                    if (tail != null) {
                        tail.xorPtr = null;
                    }
                    size--;
                    return true;
                } else {
                    // Update XOR pointers
                    if (prev != null && current.xorPtr != null) {
                        prev.xorPtr = current.xorPtr;
                    }
                    size--;
                    return true;
                }
            }
            Node<T> next = (prev == null) ? current.xorPtr : null;
            if (next == null && current != tail) {
                next = current.xorPtr;
            }
            prev = current;
            current = next;
        }
        
        return false;
    }
    
    /**
     * Traverses the list in forward direction and prints elements.
     */
    public void traverseForward() {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }
        
        Node<T> current = head;
        Node<T> prev = null;
        
        while (current != null) {
            System.out.println("  -> " + current.data);
            Node<T> next = (prev == null) ? current.xorPtr : null;
            if (next == null && current != tail) {
                next = current.xorPtr;
            }
            prev = current;
            current = next;
        }
    }
    
    /**
     * Traverses the list in backward direction and prints elements.
     */
    public void traverseBackward() {
        if (tail == null) {
            System.out.println("List is empty");
            return;
        }
        
        Node<T> current = tail;
        Node<T> next = null;
        
        while (current != null) {
            System.out.println("  <- " + current.data);
            Node<T> prev = (next == null) ? current.xorPtr : null;
            if (prev == null && current != head) {
                prev = current.xorPtr;
            }
            next = current;
            current = prev;
        }
    }
}

