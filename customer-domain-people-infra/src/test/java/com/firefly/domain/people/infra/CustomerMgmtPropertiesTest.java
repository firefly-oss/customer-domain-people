package com.firefly.domain.people.infra;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("CustomerMgmtProperties Tests")
class CustomerMgmtPropertiesTest {

    private CustomerMgmtProperties properties;

    @BeforeEach
    void setUp() {
        properties = new CustomerMgmtProperties();
    }

    @Test
    @DisplayName("Should set and get base path correctly")
    void testBasePath_ShouldSetAndGetCorrectly() {
        // Given
        String expectedBasePath = "https://api.example.com/v1";

        // When
        properties.setBasePath(expectedBasePath);

        // Then
        assertEquals(expectedBasePath, properties.getBasePath());
    }

    @Test
    @DisplayName("Should handle null base path")
    void testBasePath_ShouldHandleNull() {
        // When
        properties.setBasePath(null);

        // Then
        assertNull(properties.getBasePath());
    }

    @Test
    @DisplayName("Should handle empty base path")
    void testBasePath_ShouldHandleEmpty() {
        // Given
        String emptyBasePath = "";

        // When
        properties.setBasePath(emptyBasePath);

        // Then
        assertEquals(emptyBasePath, properties.getBasePath());
    }

    @Test
    @DisplayName("Should have default null base path")
    void testBasePath_ShouldHaveDefaultNull() {
        // Then
        assertNull(properties.getBasePath());
    }

    @Test
    @DisplayName("Should update base path multiple times")
    void testBasePath_ShouldAllowMultipleUpdates() {
        // Given
        String firstPath = "https://first.example.com";
        String secondPath = "https://second.example.com";

        // When
        properties.setBasePath(firstPath);
        assertEquals(firstPath, properties.getBasePath());

        properties.setBasePath(secondPath);

        // Then
        assertEquals(secondPath, properties.getBasePath());
    }

    @Test
    @DisplayName("Should test equals method with same values")
    void testEquals_ShouldReturnTrueForSameValues() {
        // Given
        CustomerMgmtProperties properties1 = new CustomerMgmtProperties();
        CustomerMgmtProperties properties2 = new CustomerMgmtProperties();
        
        String basePath = "https://same.example.com";
        properties1.setBasePath(basePath);
        properties2.setBasePath(basePath);

        // Then
        assertEquals(properties1, properties2);
        assertEquals(properties1.hashCode(), properties2.hashCode());
    }

    @Test
    @DisplayName("Should test equals method with different values")
    void testEquals_ShouldReturnFalseForDifferentValues() {
        // Given
        CustomerMgmtProperties properties1 = new CustomerMgmtProperties();
        CustomerMgmtProperties properties2 = new CustomerMgmtProperties();
        
        properties1.setBasePath("https://first.example.com");
        properties2.setBasePath("https://second.example.com");

        // Then
        assertNotEquals(properties1, properties2);
    }

    @Test
    @DisplayName("Should test toString method")
    void testToString_ShouldReturnNonNullString() {
        // Given
        properties.setBasePath("https://test.example.com");

        // When
        String result = properties.toString();

        // Then
        assertNotNull(result);
        assertTrue(result.contains("CustomerMgmtProperties"));
    }

    @Test
    @DisplayName("Should test with whitespace base path")
    void testBasePath_ShouldHandleWhitespace() {
        // Given
        String whitespacePath = "   https://api.example.com/v1   ";

        // When
        properties.setBasePath(whitespacePath);

        // Then
        assertEquals(whitespacePath, properties.getBasePath());
    }

    @Test
    @DisplayName("Should test with special characters in base path")
    void testBasePath_ShouldHandleSpecialCharacters() {
        // Given
        String specialPath = "https://api.example.com/v1?param=value&other=123";

        // When
        properties.setBasePath(specialPath);

        // Then
        assertEquals(specialPath, properties.getBasePath());
    }
}