# CEN207-CE205 Data Structures and Algorithms Final Project

## Project Summary

This project contains Java implementations of 12 data structures and algorithms developed as part of the Recep Tayyip Erdoğan University CEN207-CE205 course.

## Project Information

- **Course:** CEN207-CE205 Data Structures and Algorithms
- **Semester:** Fall 2025-2026
- **Project Type:** Final Project (Java Implementation)
- **Test Framework:** JUnit 5
- **Documentation:** Javadoc

## Developers

- **sumeyye** - sumeyye_yetim23@erdogan.edu.tr
- **selen** - selen_demirci23@erdogan.edu.tr
- **sudenur** - sudenur_demir22@erdogan.edu.tr
- **kaan** - RTEU

## Implementations

### 1. Double Linked List
- **File:** `DoubleLinkedList.java`
- **Test:** `DoubleLinkedListTest.java`
- **Description:** Bidirectional linked list implementation for browser history system

### 2. XOR Linked List
- **File:** `XORLinkedList.java`
- **Test:** `XORLinkedListTest.java`
- **Description:** Memory-efficient XOR linked list implementation for playlist system

### 3. Sparse Matrix
- **File:** `SparseMatrix.java`
- **Test:** `SparseMatrixTest.java`
- **Description:** Sparse matrix implementation (HashMap-based) for game grid applications

### 4. Stack
- **File:** `Stack.java`
- **Test:** `StackQueueTest.java`
- **Description:** LIFO (Last In First Out) stack structure for undo operations

### 5. Queue
- **File:** `Queue.java`
- **Test:** `StackQueueTest.java`
- **Description:** FIFO (First In First Out) queue structure for task scheduling

### 6. Min Heap
- **File:** `MinHeap.java`
- **Test:** `HeapTest.java`
- **Description:** Minimum heap data structure for priority task management

### 7. Heap Sort
- **File:** `HeapSort.java`
- **Test:** `HeapTest.java`
- **Description:** Heap sort algorithm for priority-based sorting

### 8. Graph (BFS/DFS)
- **File:** `Graph.java`
- **Test:** `GraphTest.java`
- **Description:** Graph data structure and BFS/DFS algorithms for maze/graph exploration

### 9. Hash Table
- **File:** `HashTable.java`
- **Test:** `HashTableTest.java`
- **Description:** Hash table with chaining collision resolution for key-value database

### 10. Strongly Connected Components (SCC)
- **File:** `StronglyConnectedComponents.java`
- **Test:** `StronglyConnectedComponentsTest.java`
- **Description:** Kosaraju algorithm for network cluster analysis

### 11. KMP Algorithm
- **File:** `KMPAlgorithm.java`
- **Test:** `KMPAlgorithmTest.java`
- **Description:** Knuth-Morris-Pratt pattern matching algorithm for efficient text search

### 12. Huffman Coding
- **File:** `HuffmanCoding.java`
- **Test:** `HuffmanCodingTest.java`
- **Description:** Huffman coding for text compression/decompression

### 13. B+ Tree
- **File:** `BPlusTree.java`
- **Test:** `BPlusTreeTest.java`
- **Description:** B+ tree structure for file indexing system

### 14. File Operations
- **File:** `FileOperations.java`
- **Test:** `FileOperationsTest.java`
- **Description:** File operations using hash table with linear probing for fast file search

## Project Structure

```
pomodorotimer-app/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── pomodorotimer/
│   │               └── pomodorotimer/
│   │                   ├── DoubleLinkedList.java
│   │                   ├── XORLinkedList.java
│   │                   ├── SparseMatrix.java
│   │                   ├── Stack.java
│   │                   ├── Queue.java
│   │                   ├── MinHeap.java
│   │                   ├── HeapSort.java
│   │                   ├── Graph.java
│   │                   ├── HashTable.java
│   │                   ├── StronglyConnectedComponents.java
│   │                   ├── KMPAlgorithm.java
│   │                   ├── HuffmanCoding.java
│   │                   ├── BPlusTree.java
│   │                   └── FileOperations.java
│   └── test/
│       └── java/
│           └── com/
│               └── pomodorotimer/
│                   └── pomodorotimer/
│                       ├── DoubleLinkedListTest.java
│                       ├── XORLinkedListTest.java
│                       ├── SparseMatrixTest.java
│                       ├── StackQueueTest.java
│                       ├── HeapTest.java
│                       ├── GraphTest.java
│                       ├── HashTableTest.java
│                       ├── StronglyConnectedComponentsTest.java
│                       ├── KMPAlgorithmTest.java
│                       ├── HuffmanCodingTest.java
│                       ├── BPlusTreeTest.java
│                       └── FileOperationsTest.java
├── pom.xml
└── README.md
```

## Requirements

- **Java:** JDK 1.8 or higher
- **Maven:** 3.6.0 or higher
- **IDE:** Eclipse or IntelliJ IDEA (recommended)

## Installation and Running

### 1. Clone the Project

```bash
git clone https://github.com/Sumeytm/Pomodoro-Timer.git
cd Pomodoro-Timer/pomodorotimertimertimer-app
```

### 2. Open in Eclipse

1. Open Eclipse
2. `File > Import > Existing Maven Projects`
3. Select the `pomodorotimertimertimer-app` folder
4. Click `Finish`

### 3. Run Tests

**In Eclipse:**
- Right-click on the project
- Select `Run As > JUnit Test`

**Using Maven:**
```bash
mvn test
```

### 4. Generate Javadoc

**In Eclipse:**
1. Right-click on the project
2. Select `Export > Java > Javadoc`
3. Click `Next > Next > Finish`
4. HTML files will be created in the `doc` folder

**Using Maven:**
```bash
mvn javadoc:javadoc
```

Javadoc files will be created in the `target/javadoc` folder.

## Test Results

All tests pass successfully:
- ✅ 12 test files
- ✅ Comprehensive tests for all algorithms
- ✅ Using JUnit 5

## Documentation

All classes and public methods are documented with Javadoc. After generating Javadoc, access it from `target/javadoc/index.html`.

### Generate Javadoc PDF

To generate Javadoc PDF:

```bash
mvn javadoc:javadoc
# Use wkhtmltopdf to convert HTML to PDF
```

GitHub Actions automatically generates Javadoc PDF and uploads it as an artifact.

## CI/CD Pipeline

The project has an automated CI/CD pipeline using GitHub Actions:

- ✅ Automatic test execution
- ✅ Code coverage check (minimum 80%, configurable to 100%)
- ✅ Javadoc generation (HTML + PDF)
- ✅ Build and package operations

The pipeline runs automatically on every push and pull request.

### Test Coverage

The project meets the test coverage requirements. Coverage reports:
- JaCoCo HTML report: `target/site/jacoco/index.html`
- JaCoCo XML report: `target/site/jacoco/jacoco.xml`

Check coverage:
```bash
mvn jacoco:check
```

This command will fail the build if coverage is below the configured minimum (currently 80%).

## License

This project is developed for educational purposes.

## Contact

You can use GitHub Issues for questions.
