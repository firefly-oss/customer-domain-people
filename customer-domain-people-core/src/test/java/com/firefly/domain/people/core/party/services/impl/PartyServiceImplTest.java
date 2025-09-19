package com.firefly.domain.people.core.party.services.impl;

import com.firefly.domain.people.core.party.commands.RegisterPartyRelationshipCommand;
import com.firefly.domain.people.core.party.commands.RemovePartyRelationshipCommand;
import com.firefly.domain.people.core.party.commands.UpdatePartyRelationshipCommand;
import com.firefly.domain.people.core.party.workflows.RegisterPartyRelationshipSaga;
import com.firefly.domain.people.core.party.workflows.UpdatePartyRelationshipSaga;
import com.firefly.domain.people.core.party.workflows.RemovePartyRelationshipSaga;
import com.firefly.transactional.core.SagaResult;
import com.firefly.transactional.engine.SagaEngine;
import com.firefly.transactional.engine.StepInputs;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("PartyServiceImpl Tests")
class PartyServiceImplTest {

    @Mock
    private SagaEngine sagaEngine;

    @Mock
    private SagaResult sagaResult;

    private PartyServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new PartyServiceImpl(sagaEngine);
    }

    @Test
    @DisplayName("Should register party relationship successfully")
    void testRegisterPartyRelationship_ShouldExecuteSaga() {
        // Given
        RegisterPartyRelationshipCommand command = mock(RegisterPartyRelationshipCommand.class);
        when(sagaEngine.execute(eq(RegisterPartyRelationshipSaga.class), any(StepInputs.class)))
                .thenReturn(Mono.just(sagaResult));

        // When
        Mono<SagaResult> result = service.registerPartyRelationship(command);

        // Then
        StepVerifier.create(result)
                .expectNext(sagaResult)
                .verifyComplete();

        verify(sagaEngine).execute(eq(RegisterPartyRelationshipSaga.class), any(StepInputs.class));
    }

    @Test
    @DisplayName("Should update party relationship successfully")
    void testUpdatePartyRelationship_ShouldExecuteSaga() {
        // Given
        UUID relationId = UUID.randomUUID();
        UpdatePartyRelationshipCommand command = mock(UpdatePartyRelationshipCommand.class);
        when(command.withPartyRelationshipId(relationId)).thenReturn(command);
        when(sagaEngine.execute(eq(UpdatePartyRelationshipSaga.class), any(StepInputs.class)))
                .thenReturn(Mono.just(sagaResult));

        // When
        Mono<SagaResult> result = service.updatePartyRelationship(command, relationId);

        // Then
        StepVerifier.create(result)
                .expectNext(sagaResult)
                .verifyComplete();

        verify(command).withPartyRelationshipId(relationId);
        verify(sagaEngine).execute(eq(UpdatePartyRelationshipSaga.class), any(StepInputs.class));
    }

    @Test
    @DisplayName("Should remove party relationship successfully")
    void testRemovePartyRelationship_ShouldExecuteSaga() {
        // Given
        UUID relationId = UUID.randomUUID();
        when(sagaEngine.execute(eq(RemovePartyRelationshipSaga.class), any(StepInputs.class)))
                .thenReturn(Mono.just(sagaResult));

        // When
        Mono<SagaResult> result = service.removePartyRelationship(relationId);

        // Then
        StepVerifier.create(result)
                .expectNext(sagaResult)
                .verifyComplete();

        verify(sagaEngine).execute(eq(RemovePartyRelationshipSaga.class), any(StepInputs.class));
    }

    @Test
    @DisplayName("Should handle saga execution errors in registerPartyRelationship")
    void testRegisterPartyRelationship_ShouldHandleErrors() {
        // Given
        RegisterPartyRelationshipCommand command = mock(RegisterPartyRelationshipCommand.class);
        RuntimeException error = new RuntimeException("Saga execution failed");
        when(sagaEngine.execute(eq(RegisterPartyRelationshipSaga.class), any(StepInputs.class)))
                .thenReturn(Mono.error(error));

        // When
        Mono<SagaResult> result = service.registerPartyRelationship(command);

        // Then
        StepVerifier.create(result)
                .expectError(RuntimeException.class)
                .verify();

        verify(sagaEngine).execute(eq(RegisterPartyRelationshipSaga.class), any(StepInputs.class));
    }

    @Test
    @DisplayName("Should handle saga execution errors in updatePartyRelationship")
    void testUpdatePartyRelationship_ShouldHandleErrors() {
        // Given
        UUID relationId = UUID.randomUUID();
        UpdatePartyRelationshipCommand command = mock(UpdatePartyRelationshipCommand.class);
        when(command.withPartyRelationshipId(relationId)).thenReturn(command);
        RuntimeException error = new RuntimeException("Saga execution failed");
        when(sagaEngine.execute(eq(UpdatePartyRelationshipSaga.class), any(StepInputs.class)))
                .thenReturn(Mono.error(error));

        // When
        Mono<SagaResult> result = service.updatePartyRelationship(command, relationId);

        // Then
        StepVerifier.create(result)
                .expectError(RuntimeException.class)
                .verify();

        verify(command).withPartyRelationshipId(relationId);
        verify(sagaEngine).execute(eq(UpdatePartyRelationshipSaga.class), any(StepInputs.class));
    }

    @Test
    @DisplayName("Should handle saga execution errors in removePartyRelationship")
    void testRemovePartyRelationship_ShouldHandleErrors() {
        // Given
        UUID relationId = UUID.randomUUID();
        RuntimeException error = new RuntimeException("Saga execution failed");
        when(sagaEngine.execute(eq(RemovePartyRelationshipSaga.class), any(StepInputs.class)))
                .thenReturn(Mono.error(error));

        // When
        Mono<SagaResult> result = service.removePartyRelationship(relationId);

        // Then
        StepVerifier.create(result)
                .expectError(RuntimeException.class)
                .verify();

        verify(sagaEngine).execute(eq(RemovePartyRelationshipSaga.class), any(StepInputs.class));
    }

    @Test
    @DisplayName("Constructor should set saga engine dependency")
    void testConstructor_ShouldSetSagaEngine() {
        // When
        PartyServiceImpl newService = new PartyServiceImpl(sagaEngine);

        // Then
        assertNotNull(newService);
        // We can't directly access the private field, but we can verify it works by calling a method
        RegisterPartyRelationshipCommand command = mock(RegisterPartyRelationshipCommand.class);
        when(sagaEngine.execute(eq(RegisterPartyRelationshipSaga.class), any(StepInputs.class)))
                .thenReturn(Mono.just(sagaResult));

        Mono<SagaResult> result = newService.registerPartyRelationship(command);

        StepVerifier.create(result)
                .expectNext(sagaResult)
                .verifyComplete();
    }

    @Test
    @DisplayName("Should handle null command in registerPartyRelationship")
    void testRegisterPartyRelationship_WithNullCommand_ShouldExecuteSaga() {
        // Given
        RegisterPartyRelationshipCommand command = null;
        when(sagaEngine.execute(eq(RegisterPartyRelationshipSaga.class), any(StepInputs.class)))
                .thenReturn(Mono.just(sagaResult));

        // When
        Mono<SagaResult> result = service.registerPartyRelationship(command);

        // Then
        StepVerifier.create(result)
                .expectNext(sagaResult)
                .verifyComplete();

        verify(sagaEngine).execute(eq(RegisterPartyRelationshipSaga.class), any(StepInputs.class));
    }

    @Test
    @DisplayName("Should handle null command in updatePartyRelationship")
    void testUpdatePartyRelationship_WithNullCommand_ShouldThrowException() {
        // Given
        UUID relationId = UUID.randomUUID();
        UpdatePartyRelationshipCommand command = null;

        // When & Then
        assertThrows(NullPointerException.class, () -> {
            service.updatePartyRelationship(command, relationId);
        });
    }

    @Test
    @DisplayName("Should handle null relationId in updatePartyRelationship")
    void testUpdatePartyRelationship_WithNullRelationId_ShouldExecuteSaga() {
        // Given
        UUID relationId = null;
        UpdatePartyRelationshipCommand command = mock(UpdatePartyRelationshipCommand.class);
        when(command.withPartyRelationshipId(relationId)).thenReturn(command);
        when(sagaEngine.execute(eq(UpdatePartyRelationshipSaga.class), any(StepInputs.class)))
                .thenReturn(Mono.just(sagaResult));

        // When
        Mono<SagaResult> result = service.updatePartyRelationship(command, relationId);

        // Then
        StepVerifier.create(result)
                .expectNext(sagaResult)
                .verifyComplete();

        verify(command).withPartyRelationshipId(relationId);
        verify(sagaEngine).execute(eq(UpdatePartyRelationshipSaga.class), any(StepInputs.class));
    }

    @Test
    @DisplayName("Should handle null relationId in removePartyRelationship")
    void testRemovePartyRelationship_WithNullRelationId_ShouldExecuteSaga() {
        // Given
        UUID relationId = null;
        when(sagaEngine.execute(eq(RemovePartyRelationshipSaga.class), any(StepInputs.class)))
                .thenReturn(Mono.just(sagaResult));

        // When
        Mono<SagaResult> result = service.removePartyRelationship(relationId);

        // Then
        StepVerifier.create(result)
                .expectNext(sagaResult)
                .verifyComplete();

        verify(sagaEngine).execute(eq(RemovePartyRelationshipSaga.class), any(StepInputs.class));
    }
}