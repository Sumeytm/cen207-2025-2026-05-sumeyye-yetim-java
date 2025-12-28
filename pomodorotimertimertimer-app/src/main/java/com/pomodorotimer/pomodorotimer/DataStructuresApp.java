package com.pomodorotimer.pomodorotimer;

import java.util.Scanner;

/**
 * Main console application for Data Structures project.
 * Provides menu-driven interface to test all 12 data structures and algorithms.
 * 
 * <p>This application demonstrates:
 * <ul>
 *   <li>Double Linked List - Browser history system</li>
 *   <li>XOR Linked List - Memory-efficient playlist system</li>
 *   <li>Sparse Matrix - Map-based grid application</li>
 *   <li>Stack - Task scheduler with undo operations</li>
 *   <li>Queue - Task queue (FIFO)</li>
 *   <li>Heap/Heap Sort - Priority task manager</li>
 *   <li>BFS/DFS - Maze/graph exploration</li>
 *   <li>Hash Tables - Key-value database</li>
 *   <li>Strongly Connected Components - Network cluster analysis</li>
 *   <li>KMP Algorithm - Text pattern search</li>
 *   <li>Huffman Coding - Text compression/decompression</li>
 *   <li>B+ Tree - File indexing system</li>
 *   <li>File Operations - Fast file search</li>
 * </ul>
 * 
 * @author Data Structures Project Team
 * @version 1.0
 */
public class DataStructuresApp {
    
    private static Scanner scanner = new Scanner(System.in);
    
    /**
     * Main method to run the console application.
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        System.out.println("==========================================");
        System.out.println("  Data Structures Console Application");
        System.out.println("  CEN207-CE205 Final Project");
        System.out.println("==========================================");
        System.out.println();
        
        boolean running = true;
        
        while (running) {
            printMenu();
            int choice = getChoice();
            
            switch (choice) {
                case 1:
                    runDoubleLinkedListDemo();
                    break;
                case 2:
                    runXORLinkedListDemo();
                    break;
                case 3:
                    runSparseMatrixDemo();
                    break;
                case 4:
                    runStackDemo();
                    break;
                case 5:
                    runQueueDemo();
                    break;
                case 6:
                    runHeapDemo();
                    break;
                case 7:
                    runBFSDFSDemo();
                    break;
                case 8:
                    runHashTableDemo();
                    break;
                case 9:
                    runSCCDemo();
                    break;
                case 10:
                    runKMPDemo();
                    break;
                case 11:
                    runHuffmanDemo();
                    break;
                case 12:
                    runBPlusTreeDemo();
                    break;
                case 13:
                    runFileOperationsDemo();
                    break;
                case 0:
                    running = false;
                    System.out.println("Exiting application. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            
            if (running) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }
        
        scanner.close();
    }
    
    /**
     * Prints the main menu.
     */
    private static void printMenu() {
        System.out.println("\n=== Main Menu ===");
        System.out.println("1.  Double Linked List (Browser History)");
        System.out.println("2.  XOR Linked List (Playlist System)");
        System.out.println("3.  Sparse Matrix (Map Grid)");
        System.out.println("4.  Stack (Task Scheduler - Undo)");
        System.out.println("5.  Queue (Task Queue - FIFO)");
        System.out.println("6.  Heap/Heap Sort (Priority Manager)");
        System.out.println("7.  BFS/DFS (Graph Exploration)");
        System.out.println("8.  Hash Table (Key-Value Database)");
        System.out.println("9.  Strongly Connected Components");
        System.out.println("10. KMP Algorithm (Pattern Search)");
        System.out.println("11. Huffman Coding (Compression)");
        System.out.println("12. B+ Tree (File Indexing)");
        System.out.println("13. File Operations (Fast Search)");
        System.out.println("0.  Exit");
        System.out.print("\nEnter your choice: ");
    }
    
    /**
     * Gets user choice from input.
     * 
     * @return user's choice
     */
    private static int getChoice() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    /**
     * Demonstrates Double Linked List with browser history use-case.
     */
    private static void runDoubleLinkedListDemo() {
        System.out.println("\n=== Double Linked List - Browser History ===");
        DoubleLinkedList<String> history = new DoubleLinkedList<>();
        
        System.out.println("Adding pages to browser history...");
        history.addFirst("google.com");
        history.addLast("github.com");
        history.addLast("stackoverflow.com");
        history.addLast("reddit.com");
        
        System.out.println("\nForward traversal:");
        for (String page : history) {
            System.out.println("  -> " + page);
        }
        
        System.out.println("\nBackward traversal:");
        DoubleLinkedList<String>.Node current = history.getTail();
        while (current != null) {
            System.out.println("  <- " + current.getData());
            current = current.getPrev();
        }
        
        System.out.println("\nSize: " + history.size());
        System.out.println("First page: " + history.getFirst());
        System.out.println("Last page: " + history.getLast());
    }
    
    /**
     * Demonstrates XOR Linked List with playlist use-case.
     */
    private static void runXORLinkedListDemo() {
        System.out.println("\n=== XOR Linked List - Playlist System ===");
        XORLinkedList<String> playlist = new XORLinkedList<>();
        
        System.out.println("Adding songs to playlist...");
        playlist.insert("Song 1");
        playlist.insert("Song 2");
        playlist.insert("Song 3");
        playlist.insert("Song 4");
        
        System.out.println("\nPlaylist (forward):");
        playlist.traverseForward();
        
        System.out.println("\nPlaylist (backward):");
        playlist.traverseBackward();
        
        System.out.println("\nDeleting 'Song 2'...");
        playlist.delete("Song 2");
        System.out.println("Playlist after deletion:");
        playlist.traverseForward();
    }
    
    /**
     * Demonstrates Sparse Matrix with map grid use-case.
     */
    private static void runSparseMatrixDemo() {
        System.out.println("\n=== Sparse Matrix - Map Grid ===");
        SparseMatrix matrix = new SparseMatrix(10, 10);
        
        System.out.println("Setting values in sparse matrix...");
        matrix.set(0, 0, 1);
        matrix.set(2, 3, 5);
        matrix.set(5, 7, 9);
        matrix.set(9, 9, 3);
        
        System.out.println("\nMatrix values:");
        System.out.println("Value at (0,0): " + matrix.get(0, 0));
        System.out.println("Value at (2,3): " + matrix.get(2, 3));
        System.out.println("Value at (5,7): " + matrix.get(5, 7));
        System.out.println("Value at (9,9): " + matrix.get(9, 9));
        System.out.println("Value at (1,1): " + matrix.get(1, 1) + " (default: 0)");
        
        System.out.println("\nNon-zero elements: " + matrix.getNonZeroCount());
    }
    
    /**
     * Demonstrates Stack with task scheduler undo use-case.
     */
    private static void runStackDemo() {
        System.out.println("\n=== Stack - Task Scheduler (Undo Operations) ===");
        Stack<String> taskStack = new Stack<>();
        
        System.out.println("Pushing tasks (most recent can be undone first)...");
        taskStack.push("Task 1: Create document");
        taskStack.push("Task 2: Edit document");
        taskStack.push("Task 3: Save document");
        
        System.out.println("\nCurrent top task: " + taskStack.peek());
        System.out.println("Undoing last task...");
        System.out.println("Undone: " + taskStack.pop());
        System.out.println("New top task: " + taskStack.peek());
        
        System.out.println("\nStack size: " + taskStack.size());
        System.out.println("Is empty: " + taskStack.isEmpty());
    }
    
    /**
     * Demonstrates Queue with task queue use-case.
     */
    private static void runQueueDemo() {
        System.out.println("\n=== Queue - Task Queue (FIFO) ===");
        Queue<String> taskQueue = new Queue<>();
        
        System.out.println("Enqueueing tasks (first in, first out)...");
        taskQueue.enqueue("Task 1: Process order");
        taskQueue.enqueue("Task 2: Send email");
        taskQueue.enqueue("Task 3: Generate report");
        
        System.out.println("\nProcessing tasks in order:");
        while (!taskQueue.isEmpty()) {
            System.out.println("  Processing: " + taskQueue.dequeue());
        }
        
        System.out.println("\nQueue is now empty: " + taskQueue.isEmpty());
    }
    
    /**
     * Demonstrates Heap and Heap Sort with priority task manager use-case.
     */
    private static void runHeapDemo() {
        System.out.println("\n=== Heap/Heap Sort - Priority Task Manager ===");
        MinHeap priorityQueue = new MinHeap();
        
        System.out.println("Adding tasks with priorities (lower number = higher priority)...");
        priorityQueue.insert(5);
        priorityQueue.insert(2);
        priorityQueue.insert(8);
        priorityQueue.insert(1);
        priorityQueue.insert(3);
        
        System.out.println("\nProcessing tasks by priority:");
        while (!priorityQueue.isEmpty()) {
            System.out.println("  Processing priority: " + priorityQueue.extractMin());
        }
        
        System.out.println("\n=== Heap Sort Demo ===");
        int[] array = {64, 34, 25, 12, 22, 11, 90};
        System.out.println("Original array: " + java.util.Arrays.toString(array));
        HeapSort.sort(array);
        System.out.println("Sorted array: " + java.util.Arrays.toString(array));
    }
    
    /**
     * Demonstrates BFS and DFS with graph exploration use-case.
     */
    private static void runBFSDFSDemo() {
        System.out.println("\n=== BFS/DFS - Graph Exploration ===");
        Graph graph = new Graph(6);
        
        System.out.println("Creating graph...");
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 5);
        
        System.out.println("\nBFS Traversal starting from vertex 0:");
        java.util.List<Integer> bfsResult = graph.bfs(0);
        System.out.println("  " + bfsResult);
        
        System.out.println("\nDFS Traversal starting from vertex 0:");
        java.util.List<Integer> dfsResult = graph.dfs(0);
        System.out.println("  " + dfsResult);
        
        System.out.println("\nShortest path from 0 to 5 (BFS):");
        java.util.List<Integer> path = graph.shortestPath(0, 5);
        System.out.println("  " + path);
    }
    
    /**
     * Demonstrates Hash Table with key-value database use-case.
     */
    private static void runHashTableDemo() {
        System.out.println("\n=== Hash Table - Key-Value Database ===");
        HashTable<String, String> database = new HashTable<>();
        
        System.out.println("Storing key-value pairs...");
        database.put("user1", "John Doe");
        database.put("user2", "Jane Smith");
        database.put("user3", "Bob Johnson");
        
        System.out.println("\nRetrieving values:");
        System.out.println("  user1 -> " + database.get("user1"));
        System.out.println("  user2 -> " + database.get("user2"));
        System.out.println("  user3 -> " + database.get("user3"));
        
        System.out.println("\nRemoving user2...");
        database.remove("user2");
        System.out.println("  user2 exists: " + database.containsKey("user2"));
        System.out.println("  Size: " + database.size());
    }
    
    /**
     * Demonstrates Strongly Connected Components with network cluster analysis.
     */
    private static void runSCCDemo() {
        System.out.println("\n=== Strongly Connected Components - Network Cluster Analysis ===");
        StronglyConnectedComponents scc = new StronglyConnectedComponents(5);
        
        System.out.println("Creating directed graph...");
        scc.addEdge(0, 1);
        scc.addEdge(1, 2);
        scc.addEdge(2, 0);
        scc.addEdge(1, 3);
        scc.addEdge(3, 4);
        
        System.out.println("\nFinding strongly connected components:");
        java.util.List<java.util.List<Integer>> components = scc.findSCCs();
        System.out.println("  Number of components: " + components.size());
        for (int i = 0; i < components.size(); i++) {
            System.out.println("  Component " + (i + 1) + ": " + components.get(i));
        }
    }
    
    /**
     * Demonstrates KMP Algorithm with text pattern search use-case.
     */
    private static void runKMPDemo() {
        System.out.println("\n=== KMP Algorithm - Text Pattern Search ===");
        KMPAlgorithm kmp = new KMPAlgorithm();
        
        String text = "ABABDABACDABABCABCAB";
        String pattern = "ABABCABAB";
        
        System.out.println("Text: " + text);
        System.out.println("Pattern: " + pattern);
        
        int index = kmp.findFirst(text, pattern);
        if (index != -1) {
            System.out.println("Pattern found at index: " + index);
        } else {
            System.out.println("Pattern not found");
        }
        
        System.out.println("\nFinding all occurrences:");
        java.util.List<Integer> occurrences = kmp.findAll(text, pattern);
        System.out.println("  Occurrences at indices: " + occurrences);
    }
    
    /**
     * Demonstrates Huffman Coding with text compression use-case.
     */
    private static void runHuffmanDemo() {
        System.out.println("\n=== Huffman Coding - Text Compression ===");
        
        String text = "this is an example of a huffman tree";
        System.out.println("Original text: " + text);
        System.out.println("Original size: " + text.length() + " bytes");
        
        HuffmanCoding huffman = new HuffmanCoding(text);
        
        String encoded = huffman.encode(text);
        System.out.println("Encoded: " + encoded);
        System.out.println("Encoded size: " + (encoded.length() / 8) + " bytes (approx)");
        
        String decoded = huffman.decode(encoded);
        System.out.println("Decoded: " + decoded);
        System.out.println("Compression successful: " + text.equals(decoded));
    }
    
    /**
     * Demonstrates B+ Tree with file indexing use-case.
     */
    private static void runBPlusTreeDemo() {
        System.out.println("\n=== B+ Tree - File Indexing System ===");
        BPlusTree tree = new BPlusTree(3);
        
        System.out.println("Inserting file records...");
        tree.insert(10, "file1.txt");
        tree.insert(20, "file2.txt");
        tree.insert(5, "file3.txt");
        tree.insert(6, "file4.txt");
        tree.insert(12, "file5.txt");
        tree.insert(30, "file6.txt");
        
        System.out.println("\nSearching for files:");
        System.out.println("  Key 10 -> " + tree.search(10));
        System.out.println("  Key 5 -> " + tree.search(5));
        System.out.println("  Key 30 -> " + tree.search(30));
        
        System.out.println("\nTree structure:");
        tree.print();
    }
    
    /**
     * Demonstrates File Operations with fast file search use-case.
     */
    private static void runFileOperationsDemo() {
        System.out.println("\n=== File Operations - Fast File Search ===");
        FileOperations fileOps = new FileOperations();
        
        System.out.println("File operations demo:");
        System.out.println("  This module implements various file search techniques:");
        System.out.println("  - Linear Probing");
        System.out.println("  - Quadratic Probing");
        System.out.println("  - Double Hashing");
        System.out.println("  - Bucket-based storage");
        
        // Example usage would depend on FileOperations implementation
        System.out.println("\nNote: Actual file operations require file system access.");
        System.out.println("See FileOperations class for implementation details.");
    }
}

