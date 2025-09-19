package com.firefly.domain.people.core.business.services.impl;

import com.firefly.domain.people.core.business.commands.*;
import com.firefly.domain.people.core.contact.commands.*;
import com.firefly.domain.people.core.contact.workflows.*;
import com.firefly.domain.people.core.customer.commands.RegisterCustomerCommand;
import com.firefly.domain.people.core.customer.commands.UpdateCustomerCommand;
import com.firefly.domain.people.core.customer.services.impl.RegisterCustomerServiceImpl;
import com.firefly.domain.people.core.customer.workflows.RegisterCustomerSaga;
import com.firefly.domain.people.core.customer.workflows.UpdateCustomerNameSaga;
import com.firefly.domain.people.core.status.commands.UpdateStatusCommand;
import com.firefly.domain.people.core.status.workflows.UpdateStatusSaga;
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
@DisplayName("RegisterCustomerServiceImpl Tests")
class RegisterCustomerServiceImplTest {

    @Mock
    private SagaEngine sagaEngine;

    @Mock
    private SagaResult sagaResult;

    private RegisterCustomerServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new RegisterCustomerServiceImpl(sagaEngine);
    }

    @Test
    @DisplayName("Should register customer successfully")
    void testRegisterCustomer_ShouldExecuteSaga() {
        // Given
        RegisterCustomerCommand command = mock(RegisterCustomerCommand.class);
        when(sagaEngine.execute(eq(RegisterCustomerSaga.class), any(StepInputs.class)))
                .thenReturn(Mono.just(sagaResult));

        // When
        Mono<SagaResult> result = service.registerCustomer(command);

        // Then
        StepVerifier.create(result)
                .expectNext(sagaResult)
                .verifyComplete();

        verify(sagaEngine).execute(eq(RegisterCustomerSaga.class), any(StepInputs.class));
    }

    @Test
    @DisplayName("Should update customer successfully")
    void testUpdateCustomer_ShouldExecuteSaga() {
        // Given
        UpdateCustomerCommand command = mock(UpdateCustomerCommand.class);
        when(sagaEngine.execute(eq(UpdateCustomerNameSaga.class), any(StepInputs.class)))
                .thenReturn(Mono.just(sagaResult));

        // When
        Mono<SagaResult> result = service.updateCustomer(command);

        // Then
        StepVerifier.create(result)
                .expectNext(sagaResult)
                .verifyComplete();

        verify(sagaEngine).execute(eq(UpdateCustomerNameSaga.class), any(StepInputs.class));
    }

    @Test
    @DisplayName("Should add address successfully")
    void testAddAddress_ShouldExecuteSaga() {
        // Given
        UUID partyId = UUID.randomUUID();
        RegisterAddressCommand command = mock(RegisterAddressCommand.class);
        when(command.withPartyId(partyId)).thenReturn(command);
        when(sagaEngine.execute(eq(AddAddressSaga.class), any(StepInputs.class)))
                .thenReturn(Mono.just(sagaResult));

        // When
        Mono<SagaResult> result = service.addAddress(partyId, command);

        // Then
        StepVerifier.create(result)
                .expectNext(sagaResult)
                .verifyComplete();

        verify(command).withPartyId(partyId);
        verify(sagaEngine).execute(eq(AddAddressSaga.class), any(StepInputs.class));
    }

    @Test
    @DisplayName("Should update address successfully")
    void testUpdateAddress_ShouldExecuteSaga() {
        // Given
        UUID partyId = UUID.randomUUID();
        UUID addressId = UUID.randomUUID();
        UpdateAddressCommand command = mock(UpdateAddressCommand.class);
        when(command.withAddressId(addressId)).thenReturn(command);
        when(command.withPartyId(partyId)).thenReturn(command);
        when(sagaEngine.execute(eq(UpdateAddressSaga.class), any(StepInputs.class)))
                .thenReturn(Mono.just(sagaResult));

        // When
        Mono<Void> result = service.updateAddress(partyId, addressId, command);

        // Then
        StepVerifier.create(result)
                .verifyComplete();

        verify(command).withAddressId(addressId);
        verify(command).withPartyId(partyId);
        verify(sagaEngine).execute(eq(UpdateAddressSaga.class), any(StepInputs.class));
    }

    @Test
    @DisplayName("Should remove address successfully")
    void testRemoveAddress_ShouldExecuteSaga() {
        // Given
        UUID partyId = UUID.randomUUID();
        UUID addressId = UUID.randomUUID();
        when(sagaEngine.execute(eq(RemoveAddressSaga.class), any(StepInputs.class)))
                .thenReturn(Mono.just(sagaResult));

        // When
        Mono<SagaResult> result = service.removeAddress(partyId, addressId);

        // Then
        StepVerifier.create(result)
                .expectNext(sagaResult)
                .verifyComplete();

        verify(sagaEngine).execute(eq(RemoveAddressSaga.class), any(StepInputs.class));
    }

    @Test
    @DisplayName("Should add email successfully")
    void testAddEmail_ShouldExecuteSaga() {
        // Given
        UUID partyId = UUID.randomUUID();
        RegisterEmailCommand command = mock(RegisterEmailCommand.class);
        when(command.withPartyId(partyId)).thenReturn(command);
        when(sagaEngine.execute(eq(AddEmailSaga.class), any(StepInputs.class)))
                .thenReturn(Mono.just(sagaResult));

        // When
        Mono<SagaResult> result = service.addEmail(partyId, command);

        // Then
        StepVerifier.create(result)
                .expectNext(sagaResult)
                .verifyComplete();

        verify(command).withPartyId(partyId);
        verify(sagaEngine).execute(eq(AddEmailSaga.class), any(StepInputs.class));
    }

    @Test
    @DisplayName("Should update email successfully")
    void testUpdateEmail_ShouldExecuteSaga() {
        // Given
        UUID partyId = UUID.randomUUID();
        UUID emailId = UUID.randomUUID();
        UpdateEmailCommand command = mock(UpdateEmailCommand.class);
        when(command.withEmailContactId(emailId)).thenReturn(command);
        when(command.withPartyId(partyId)).thenReturn(command);
        when(sagaEngine.execute(eq(UpdateEmailSaga.class), any(StepInputs.class)))
                .thenReturn(Mono.just(sagaResult));

        // When
        Mono<Void> result = service.updateEmail(partyId, emailId, command);

        // Then
        StepVerifier.create(result)
                .verifyComplete();

        verify(command).withEmailContactId(emailId);
        verify(command).withPartyId(partyId);
        verify(sagaEngine).execute(eq(UpdateEmailSaga.class), any(StepInputs.class));
    }

    @Test
    @DisplayName("Should remove email successfully")
    void testRemoveEmail_ShouldExecuteSaga() {
        // Given
        UUID partyId = UUID.randomUUID();
        UUID emailId = UUID.randomUUID();
        when(sagaEngine.execute(eq(RemoveEmailSaga.class), any(StepInputs.class)))
                .thenReturn(Mono.just(sagaResult));

        // When
        Mono<SagaResult> result = service.removeEmail(partyId, emailId);

        // Then
        StepVerifier.create(result)
                .expectNext(sagaResult)
                .verifyComplete();

        verify(sagaEngine).execute(eq(RemoveEmailSaga.class), any(StepInputs.class));
    }

    @Test
    @DisplayName("Should add phone successfully")
    void testAddPhone_ShouldExecuteSaga() {
        // Given
        UUID partyId = UUID.randomUUID();
        RegisterPhoneCommand command = mock(RegisterPhoneCommand.class);
        when(command.withPartyId(partyId)).thenReturn(command);
        when(sagaEngine.execute(eq(AddPhoneSaga.class), any(StepInputs.class)))
                .thenReturn(Mono.just(sagaResult));

        // When
        Mono<SagaResult> result = service.addPhone(partyId, command);

        // Then
        StepVerifier.create(result)
                .expectNext(sagaResult)
                .verifyComplete();

        verify(command).withPartyId(partyId);
        verify(sagaEngine).execute(eq(AddPhoneSaga.class), any(StepInputs.class));
    }

    @Test
    @DisplayName("Should update phone successfully")
    void testUpdatePhone_ShouldExecuteSaga() {
        // Given
        UUID partyId = UUID.randomUUID();
        UUID phoneId = UUID.randomUUID();
        UpdatePhoneCommand command = mock(UpdatePhoneCommand.class);
        when(command.withPhoneContactId(phoneId)).thenReturn(command);
        when(command.withPartyId(partyId)).thenReturn(command);
        when(sagaEngine.execute(eq(UpdatePhoneSaga.class), any(StepInputs.class)))
                .thenReturn(Mono.just(sagaResult));

        // When
        Mono<Void> result = service.updatePhone(partyId, phoneId, command);

        // Then
        StepVerifier.create(result)
                .verifyComplete();

        verify(command).withPhoneContactId(phoneId);
        verify(command).withPartyId(partyId);
        verify(sagaEngine).execute(eq(UpdatePhoneSaga.class), any(StepInputs.class));
    }

    @Test
    @DisplayName("Should remove phone successfully")
    void testRemovePhone_ShouldExecuteSaga() {
        // Given
        UUID partyId = UUID.randomUUID();
        UUID phoneId = UUID.randomUUID();
        when(sagaEngine.execute(eq(RemovePhoneSaga.class), any(StepInputs.class)))
                .thenReturn(Mono.just(sagaResult));

        // When
        Mono<SagaResult> result = service.removePhone(partyId, phoneId);

        // Then
        StepVerifier.create(result)
                .expectNext(sagaResult)
                .verifyComplete();

        verify(sagaEngine).execute(eq(RemovePhoneSaga.class), any(StepInputs.class));
    }

    @Test
    @DisplayName("Should set preferred channel successfully")
    void testSetPreferredChannel_ShouldExecuteSaga() {
        // Given
        UUID partyId = UUID.randomUUID();
        UpdatePreferredChannelCommand command = mock(UpdatePreferredChannelCommand.class);
        when(command.withPartyId(partyId)).thenReturn(command);
        when(sagaEngine.execute(eq(SetPreferredChannelSaga.class), any(StepInputs.class)))
                .thenReturn(Mono.just(sagaResult));

        // When
        Mono<Void> result = service.setPreferredChannel(partyId, command);

        // Then
        StepVerifier.create(result)
                .verifyComplete();

        verify(command).withPartyId(partyId);
        verify(sagaEngine).execute(eq(SetPreferredChannelSaga.class), any(StepInputs.class));
    }

    @Test
    @DisplayName("Should update status successfully")
    void testUpdateStatus_ShouldExecuteSaga() {
        // Given
        UpdateStatusCommand command = mock(UpdateStatusCommand.class);
        when(sagaEngine.execute(eq(UpdateStatusSaga.class), any(StepInputs.class)))
                .thenReturn(Mono.just(sagaResult));

        // When
        Mono<SagaResult> result = service.updateStatus(command);

        // Then
        StepVerifier.create(result)
                .expectNext(sagaResult)
                .verifyComplete();

        verify(sagaEngine).execute(eq(UpdateStatusSaga.class), any(StepInputs.class));
    }

    @Test
    @DisplayName("Should handle saga execution errors")
    void testRegisterCustomer_ShouldHandleErrors() {
        // Given
        RegisterCustomerCommand command = mock(RegisterCustomerCommand.class);
        RuntimeException error = new RuntimeException("Saga execution failed");
        when(sagaEngine.execute(eq(RegisterCustomerSaga.class), any(StepInputs.class)))
                .thenReturn(Mono.error(error));

        // When
        Mono<SagaResult> result = service.registerCustomer(command);

        // Then
        StepVerifier.create(result)
                .expectError(RuntimeException.class)
                .verify();

        verify(sagaEngine).execute(eq(RegisterCustomerSaga.class), any(StepInputs.class));
    }

    @Test
    @DisplayName("Constructor should set saga engine dependency")
    void testConstructor_ShouldSetSagaEngine() {
        // When
        RegisterCustomerServiceImpl newService = new RegisterCustomerServiceImpl(sagaEngine);

        // Then
        assertNotNull(newService);
        // We can't directly access the private field, but we can verify it works by calling a method
        RegisterCustomerCommand command = mock(RegisterCustomerCommand.class);
        when(sagaEngine.execute(eq(RegisterCustomerSaga.class), any(StepInputs.class)))
                .thenReturn(Mono.just(sagaResult));

        Mono<SagaResult> result = newService.registerCustomer(command);

        StepVerifier.create(result)
                .expectNext(sagaResult)
                .verifyComplete();
    }
}