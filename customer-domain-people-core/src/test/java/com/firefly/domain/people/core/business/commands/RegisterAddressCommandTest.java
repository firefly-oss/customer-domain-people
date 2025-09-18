package com.firefly.domain.people.core.business.commands;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

@DisplayName("RegisterAddressCommand Tests")
class RegisterAddressCommandTest {

    private RegisterAddressCommand command;
    private UUID testPartyId;
    private UUID testAddressId;

    @BeforeEach
    void setUp() {
        command = new RegisterAddressCommand();
        testPartyId = UUID.randomUUID();
        testAddressId = UUID.randomUUID();
    }

    @Test
    @DisplayName("Should set party ID and return command instance for method chaining")
    void testWithPartyId_ShouldSetPartyIdAndReturnCommand() {
        // When
        RegisterAddressCommand result = command.withPartyId(testPartyId);

        // Then
        assertSame(command, result, "Should return the same command instance for method chaining");
        assertEquals(testPartyId, command.getPartyId(), "Should set the party ID correctly");
    }

    @Test
    @DisplayName("Should handle null party ID")
    void testWithPartyId_ShouldHandleNullPartyId() {
        // When
        RegisterAddressCommand result = command.withPartyId(null);

        // Then
        assertSame(command, result, "Should return the same command instance");
        assertNull(command.getPartyId(), "Should handle null party ID");
    }

    @Test
    @DisplayName("Should allow method chaining with multiple operations")
    void testWithPartyId_ShouldAllowMethodChaining() {
        // Given
        UUID firstPartyId = UUID.randomUUID();
        UUID secondPartyId = UUID.randomUUID();

        // When
        RegisterAddressCommand result = command
                .withPartyId(firstPartyId)
                .withPartyId(secondPartyId);

        // Then
        assertSame(command, result, "Should return the same command instance");
        assertEquals(secondPartyId, command.getPartyId(), "Should have the last set party ID");
    }

    @Test
    @DisplayName("Should set and get address ID correctly")
    void testAddressId_ShouldSetAndGetCorrectly() {
        // When
        command.setAddressId(testAddressId);

        // Then
        assertEquals(testAddressId, command.getAddressId(), "Should set and get address ID correctly");
    }

    @Test
    @DisplayName("Should handle null address ID")
    void testAddressId_ShouldHandleNull() {
        // When
        command.setAddressId(null);

        // Then
        assertNull(command.getAddressId(), "Should handle null address ID");
    }

    @Test
    @DisplayName("Should inherit from AddressDTO")
    void testInheritance_ShouldExtendAddressDTO() {
        // Then
        assertTrue(command instanceof com.firefly.core.customer.sdk.model.AddressDTO, 
                   "RegisterAddressCommand should extend AddressDTO");
    }

    @Test
    @DisplayName("Should implement Command interface")
    void testInterface_ShouldImplementCommand() {
        // Then
        assertTrue(command instanceof com.firefly.common.domain.cqrs.command.Command, 
                   "RegisterAddressCommand should implement Command interface");
    }

    @Test
    @DisplayName("Should test equals method with same values")
    void testEquals_ShouldReturnTrueForSameValues() {
        // Given
        RegisterAddressCommand command1 = new RegisterAddressCommand();
        RegisterAddressCommand command2 = new RegisterAddressCommand();
        
        command1.setAddressId(testAddressId);
        command1.withPartyId(testPartyId);
        
        command2.setAddressId(testAddressId);
        command2.withPartyId(testPartyId);

        // Then
        assertEquals(command1, command2, "Commands with same values should be equal");
        assertEquals(command1.hashCode(), command2.hashCode(), "Hash codes should be equal for equal objects");
    }

    @Test
    @DisplayName("Should test equals method with different values")
    void testEquals_ShouldReturnFalseForDifferentValues() {
        // Given
        RegisterAddressCommand command1 = new RegisterAddressCommand();
        RegisterAddressCommand command2 = new RegisterAddressCommand();
        
        command1.setAddressId(testAddressId);
        command2.setAddressId(UUID.randomUUID());

        // Then
        assertNotEquals(command1, command2, "Commands with different values should not be equal");
    }

    @Test
    @DisplayName("Should test equals method with null")
    void testEquals_ShouldHandleNull() {
        // Then
        assertNotEquals(command, null, "Command should not equal null");
        assertNotEquals(null, command, "Command should not equal null");
    }

    @Test
    @DisplayName("Should test equals method with different class")
    void testEquals_ShouldHandleDifferentClass() {
        // Then
        assertNotEquals(command, "not a command", "Command should not equal different class");
    }

    @Test
    @DisplayName("Should test toString method")
    void testToString_ShouldReturnNonNullString() {
        // Given
        command.setAddressId(testAddressId);
        command.withPartyId(testPartyId);

        // When
        String result = command.toString();

        // Then
        assertNotNull(result, "ToString should not return null");
        assertTrue(result.contains("RegisterAddressCommand"), "ToString should contain class name");
    }
}