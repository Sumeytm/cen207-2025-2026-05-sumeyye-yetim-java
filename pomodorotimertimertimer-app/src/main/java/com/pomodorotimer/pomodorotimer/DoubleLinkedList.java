package com.pomodorotimer.pomodorotimer;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Double Linked List implementation for browser history system with backward/forward navigation.
 * This data structure allows efficient insertion, deletion, and bidirectional traversal.
 * 
 * <p>Use-case: Browser history system where users can navigate backward and forward through
 * visited pages. Each node represents a page in the history.
 * 
 * <p>Time Complexity:
 * <ul>
 *   <li>Insertion at front/back: O(1)</li>
 *   <li>Deletion: O(1) if node is known, O(n) if searching by value</li>
 *   <li>Search: O(n)</li>
 *   <li>Traversal: O(n)</li>
 * </ul>
 * 
 * <p>Space Complexity: O(n) where n is the number of elements
 * 
 * @param <T> the type of elements in this list
 * @author Data Structures Project Team
 * @version 1.0
 */
public class DoubleLinkedList<T> implements Iterable<T> {
    
    /**
     * Inner class representing a node in the double linked list.
     */
    public static class Node<T> {
        private T data;
        private Node<T> prev;
        private Node<T> next;
        
        Node(T data) {
            this.data = data;
            this.prev = null;
            this.next = null;
        }
        
        public T getData() {
            return data;
        }
        
        public Node<T> getPrev() {
            return prev;
        }
        
        public Node<T> getNext() {
            return next;
        }
    }
    
    private Node<T> head;
    private Node<T> tail;
    private int size;
    
    /**
     * Constructs an empty double linked list.
     */
    public DoubleLinkedList() {
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
            newNode.next = head;
            head.prev = newNode;
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
            tail.next = newNode;
            newNode.prev = tail;
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
        if (head == null) {
            throw new NoSuchElementException("List is empty");
        }
        T data = head.data;
        if (head == tail) {
            head = tail = null;
        } else {
            head = head.next;
            head.prev = null;
        }
        size--;
        return data;
    }
    
    /**
     * Removes and returns the last element.
     * 
     * @return the last element
     * @throws NoSuchElementException if the list is empty
     */
    public T removeLast() {
        if (tail == null) {
            throw new NoSuchElementException("List is empty");
        }
        T data = tail.data;
        if (head == tail) {
            head = tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
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
     * Returns an iterator over the elements in forward direction.
     * 
     * @return an iterator
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> current = head;
            
            @Override
            public boolean hasNext() {
                return current != null;
            }
            
            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                T data = current.data;
                current = current.next;
                return data;
            }
        };
    }
    
    /**
     * Returns an iterator over the elements in backward direction.
     * 
     * @return a backward iterator
     */
    public Iterator<T> backwardIterator() {
        return new Iterator<T>() {
            private Node<T> current = tail;
            
            @Override
            public boolean hasNext() {
                return current != null;
            }
            
            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                T data = current.data;
                current = current.prev;
                return data;
            }
        };
    }
    
    /**
     * Returns the tail node for backward traversal.
     * 
     * @return the tail node
     */
    public Node<T> getTail() {
        return tail;
    }
}

