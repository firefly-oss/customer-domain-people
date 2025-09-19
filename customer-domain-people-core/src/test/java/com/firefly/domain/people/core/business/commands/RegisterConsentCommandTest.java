package com.firefly.domain.people.core.business.commands;

import com.firefly.domain.people.core.compliance.commands.RegisterConsentCommand;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

@DisplayName("RegisterConsentCommand Tests")
class RegisterConsentCommandTest {

    private RegisterConsentCommand command;
    private UUID testPartyId;

    @BeforeEach
    void setUp() {
        command = new RegisterConsentCommand();
        testPartyId = UUID.randomUUID();
    }

    @Test
    @DisplayName("Should set party ID and return command instance for method chaining")
    void testWithPartyId_ShouldSetPartyIdAndReturnCommand() {
        // When
        RegisterConsentCommand result = command.withPartyId(testPartyId);

        // Then
        assertSame(command, result, "Should return the same command instance for method chaining");
        assertEquals(testPartyId, command.getPartyId(), "Should set the party ID correctly");
    }

    @Test
    @DisplayName("Should handle null party ID")
    void testWithPartyId_ShouldHandleNullPartyId() {
        // When
        RegisterConsentCommand result = command.withPartyId(null);

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
        RegisterConsentCommand result = command
                .withPartyId(firstPartyId)
                .withPartyId(secondPartyId);

        // Then
        assertSame(command, result, "Should return the same command instance");
        assertEquals(secondPartyId, command.getPartyId(), "Should have the last set party ID");
    }

    @Test
    @DisplayName("Should inherit from ConsentDTO")
    void testInheritance_ShouldExtendConsentDTO() {
        // Then
        assertTrue(command instanceof com.firefly.core.customer.sdk.model.ConsentDTO, 
                   "RegisterConsentCommand should extend ConsentDTO");
    }

    @Test
    @DisplayName("Should implement Command interface")
    void testInterface_ShouldImplementCommand() {
        // Then
        assertTrue(command instanceof com.firefly.common.domain.cqrs.command.Command, 
                   "RegisterConsentCommand should implement Command interface");
    }
}