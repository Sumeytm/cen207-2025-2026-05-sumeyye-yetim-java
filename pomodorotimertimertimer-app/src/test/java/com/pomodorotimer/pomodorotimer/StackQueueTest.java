package com.pomodorotimer.pomodorotimer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

/**
 * Unit tests for Stack and Queue implementations.
 * Tests push/pop, enqueue/dequeue, and order verification.
 */
@DisplayName("Stack and Queue Tests")
class StackQueueTest {
    
    private Stack<String> stack;
    private Queue<String> queue;
    
    @BeforeEach
    void setUp() {
        stack = new Stack<>();
        queue = new Queue<>();
    }
    
    // Stack Tests
    @Test
    @DisplayName("Test empty stack")
    void testEmptyStack() {
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
        assertThrows(NoSuchElementException.class, () -> stack.pop());
        assertThrows(NoSuchElementException.class, () -> stack.peek());
    }
    
    @Test
    @DisplayName("Test stack push and pop")
    void testStackPushPop() {
        stack.push("Task1");
        stack.push("Task2");
        stack.push("Task3");
        
        assertEquals(3, stack.size());
        assertEquals("Task3", stack.pop());
        assertEquals("Task2", stack.pop());
        assertEquals("Task1", stack.pop());
        assertTrue(stack.isEmpty());
    }
    
    @Test
    @DisplayName("Test stack peek")
    void testStackPeek() {
        stack.push("Task1");
        assertEquals("Task1", stack.peek());
        assertEquals(1, stack.size());
        assertEquals("Task1", stack.peek()); // Peek doesn't remove
    }
    
    @Test
    @DisplayName("Test stack LIFO order")
    void testStackLIFO() {
        stack.push("First");
        stack.push("Second");
        stack.push("Third");
        
        // Should pop in reverse order (LIFO)
        assertEquals("Third", stack.pop());
        assertEquals("Second", stack.pop());
        assertEquals("First", stack.pop());
    }
    
    @Test
    @DisplayName("Test stack clear")
    void testStackClear() {
        stack.push("Task1");
        stack.push("Task2");
        stack.clear();
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
    }
    
    // Queue Tests
    @Test
    @DisplayName("Test empty queue")
    void testEmptyQueue() {
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
        assertThrows(NoSuchElementException.class, () -> queue.dequeue());
        assertThrows(NoSuchElementException.class, () -> queue.peek());
    }
    
    @Test
    @DisplayName("Test queue enqueue and dequeue")
    void testQueueEnqueueDequeue() {
        queue.enqueue("Task1");
        queue.enqueue("Task2");
        queue.enqueue("Task3");
        
        assertEquals(3, queue.size());
        assertEquals("Task1", queue.dequeue());
        assertEquals("Task2", queue.dequeue());
        assertEquals("Task3", queue.dequeue());
        assertTrue(queue.isEmpty());
    }
    
    @Test
    @DisplayName("Test queue peek")
    void testQueuePeek() {
        queue.enqueue("Task1");
        assertEquals("Task1", queue.peek());
        assertEquals(1, queue.size());
        assertEquals("Task1", queue.peek()); // Peek doesn't remove
    }
    
    @Test
    @DisplayName("Test queue FIFO order")
    void testQueueFIFO() {
        queue.enqueue("First");
        queue.enqueue("Second");
        queue.enqueue("Third");
        
        // Should dequeue in same order (FIFO)
        assertEquals("First", queue.dequeue());
        assertEquals("Second", queue.dequeue());
        assertEquals("Third", queue.dequeue());
    }
    
    @Test
    @DisplayName("Test queue clear")
    void testQueueClear() {
        queue.enqueue("Task1");
        queue.enqueue("Task2");
        queue.clear();
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
    }
    
    @Test
    @DisplayName("Test task scheduler use-case - Stack for undo")
    void testTaskSchedulerUndo() {
        // Simulate undo operations using stack
        stack.push("Action1");
        stack.push("Action2");
        stack.push("Action3");
        
        // Undo in reverse order
        assertEquals("Action3", stack.pop());
        assertEquals("Action2", stack.pop());
        assertEquals("Action1", stack.pop());
    }
    
    @Test
    @DisplayName("Test task queue use-case - Queue for task processing")
    void testTaskQueueProcessing() {
        // Simulate task queue processing
        queue.enqueue("Task1");
        queue.enqueue("Task2");
        queue.enqueue("Task3");
        
        // Process in order
        assertEquals("Task1", queue.dequeue());
        assertEquals("Task2", queue.dequeue());
        assertEquals("Task3", queue.dequeue());
    }
}


