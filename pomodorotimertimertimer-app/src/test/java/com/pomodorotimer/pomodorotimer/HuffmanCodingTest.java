package com.pomodorotimer.pomodorotimer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

/**
 * Unit tests for HuffmanCoding implementation.
 * Tests file size reduction and lossless decompression.
 */
@DisplayName("Huffman Coding Tests")
class HuffmanCodingTest {
    
    @Test
    @DisplayName("Test encode and decode")
    void testEncodeDecode() {
        String text = "hello world";
        HuffmanCoding huffman = new HuffmanCoding(text);
        
        String encoded = huffman.encode(text);
        String decoded = huffman.decode(encoded);
        
        assertEquals(text, decoded);
    }
    
    @Test
    @DisplayName("Test lossless decompression")
    void testLosslessDecompression() {
        String text = "aabbcc";
        HuffmanCoding huffman = new HuffmanCoding(text);
        
        String encoded = huffman.encode(text);
        String decoded = huffman.decode(encoded);
        
        assertEquals(text, decoded);
    }
    
    @Test
    @DisplayName("Test compression ratio")
    void testCompressionRatio() {
        String text = "aaaaabbbbbccccc";
        HuffmanCoding huffman = new HuffmanCoding(text);
        
        String encoded = huffman.encode(text);
        double ratio = huffman.getCompressionRatio(text, encoded);
        
        assertTrue(ratio > 1.0); // Should compress
    }
    
    @Test
    @DisplayName("Test encoding map")
    void testEncodingMap() {
        String text = "abc";
        HuffmanCoding huffman = new HuffmanCoding(text);
        
        Map<Character, String> encodingMap = huffman.getEncodingMap();
        assertTrue(encodingMap.containsKey('a'));
        assertTrue(encodingMap.containsKey('b'));
        assertTrue(encodingMap.containsKey('c'));
    }
    
    @Test
    @DisplayName("Test empty text")
    void testEmptyText() {
        assertThrows(IllegalArgumentException.class, () -> new HuffmanCoding(""));
        assertThrows(IllegalArgumentException.class, () -> new HuffmanCoding(null));
    }
    
    @Test
    @DisplayName("Test single character")
    void testSingleCharacter() {
        String text = "aaaaa";
        HuffmanCoding huffman = new HuffmanCoding(text);
        
        String encoded = huffman.encode(text);
        String decoded = huffman.decode(encoded);
        
        assertEquals(text, decoded);
    }
    
    @Test
    @DisplayName("Test text compression use-case")
    void testTextCompression() {
        String text = "The quick brown fox jumps over the lazy dog";
        HuffmanCoding huffman = new HuffmanCoding(text);
        
        String encoded = huffman.encode(text);
        String decoded = huffman.decode(encoded);
        
        assertEquals(text, decoded);
        assertTrue(encoded.length() < text.length() * 8); // Should compress
    }
    
    @Test
    @DisplayName("Test encode with character not in encoding map")
    void testEncodeCharacterNotInMap() {
        String text = "abc";
        HuffmanCoding huffman = new HuffmanCoding(text);
        
        // Try to encode a character that wasn't in the original text
        assertThrows(IllegalArgumentException.class, () -> {
            huffman.encode("abcx"); // 'x' is not in the encoding map
        });
    }
    
    @Test
    @DisplayName("Test encode with multiple characters not in map")
    void testEncodeMultipleCharactersNotInMap() {
        String text = "hello";
        HuffmanCoding huffman = new HuffmanCoding(text);
        
        // Try to encode with characters not in original text
        assertThrows(IllegalArgumentException.class, () -> {
            huffman.encode("helloworld"); // 'w', 'o', 'r', 'l', 'd' not in map
        });
    }
    
    @Test
    @DisplayName("Test encode with special character not in map")
    void testEncodeSpecialCharacterNotInMap() {
        String text = "test";
        HuffmanCoding huffman = new HuffmanCoding(text);
        
        // Try to encode with special character
        assertThrows(IllegalArgumentException.class, () -> {
            huffman.encode("test@"); // '@' not in encoding map
        });
    }
}


