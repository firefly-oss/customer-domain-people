package com.firefly.domain.people.web.controller;

import com.firefly.domain.people.core.contact.commands.*;
import com.firefly.domain.people.core.contact.services.ContactService;
import com.firefly.domain.people.core.customer.commands.RegisterCustomerCommand;
import com.firefly.domain.people.core.customer.commands.UpdateCustomerCommand;
import com.firefly.domain.people.core.customer.services.RegisterCustomerService;
import com.firefly.domain.people.core.status.commands.UpdateStatusCommand;
import com.firefly.domain.people.core.status.services.StatusService;
import com.firefly.transactional.core.SagaResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("CustomersController Tests")
class CustomersControllerTest {

    @Mock
    private RegisterCustomerService registerCustomerService;

    @Mock
    private ContactService contactService;

    @Mock
    private StatusService statusService;

    @Mock
    private SagaResult sagaResult;

    private CustomersController controller;

    @BeforeEach
    void setUp() {
        controller = new CustomersController(registerCustomerService, contactService, statusService);
    }

    @Test
    @DisplayName("Should register customer successfully")
    void testRegisterCustomer_ShouldReturnNoContentResponse() {
        // Given
        RegisterCustomerCommand command = mock(RegisterCustomerCommand.class);
        when(registerCustomerService.registerCustomer(command))
                .thenReturn(Mono.just(sagaResult));

        // When
        Mono<ResponseEntity<Object>> result = controller.registerCustomer(command);

        // Then
        StepVerifier.create(result)
                .assertNext(response -> assertEquals(204, response.getStatusCodeValue()))
                .verifyComplete();

        verify(registerCustomerService).registerCustomer(command);
    }

    @Test
    @DisplayName("Should handle register customer errors")
    void testRegisterCustomer_ShouldHandleErrors() {
        // Given
        RegisterCustomerCommand command = mock(RegisterCustomerCommand.class);
        RuntimeException error = new RuntimeException("Service error");
        when(registerCustomerService.registerCustomer(command))
                .thenReturn(Mono.error(error));

        // When
        Mono<ResponseEntity<Object>> result = controller.registerCustomer(command);

        // Then
        StepVerifier.create(result)
                .expectError(RuntimeException.class)
                .verify();

        verify(registerCustomerService).registerCustomer(command);
    }

    @Test
    @DisplayName("Should update customer successfully")
    void testUpdateCustomer_ShouldReturnOkResponse() {
        // Given
        UpdateCustomerCommand command = mock(UpdateCustomerCommand.class);
        when(registerCustomerService.updateCustomer(command))
                .thenReturn(Mono.just(sagaResult));

        // When
        Mono<ResponseEntity<Object>> result = controller.updateCustomer(command);

        // Then
        StepVerifier.create(result)
                .assertNext(response -> assertEquals(200, response.getStatusCodeValue()))
                .verifyComplete();

        verify(registerCustomerService).updateCustomer(command);
    }

    @Test
    @DisplayName("Should add customer address successfully")
    void testAddCustomerAddress_ShouldReturnOkResponse() {
        // Given
        UUID partyId = UUID.randomUUID();
        RegisterAddressCommand command = mock(RegisterAddressCommand.class);
        when(contactService.addAddress(partyId, command))
                .thenReturn(Mono.just(sagaResult));

        // When
        Mono<ResponseEntity<Object>> result = controller.addCustomerAddress(partyId, command);

        // Then
        StepVerifier.create(result)
                .assertNext(response -> assertEquals(200, response.getStatusCodeValue()))
                .verifyComplete();

        verify(contactService).addAddress(partyId, command);
    }

    @Test
    @DisplayName("Should update customer address successfully")
    void testUpdateCustomerAddress_ShouldReturnOkResponse() {
        // Given
        UUID partyId = UUID.randomUUID();
        UUID addressId = UUID.randomUUID();
        UpdateAddressCommand command = mock(UpdateAddressCommand.class);
        when(contactService.updateAddress(partyId, addressId, command))
                .thenReturn(Mono.empty());

        // When
        Mono<ResponseEntity<Object>> result = controller.updateCustomerAddress(partyId, addressId, command);

        // Then
        StepVerifier.create(result)
                .assertNext(response -> assertEquals(200, response.getStatusCodeValue()))
                .verifyComplete();

        verify(contactService).updateAddress(partyId, addressId, command);
    }

    @Test
    @DisplayName("Should remove customer address successfully")
    void testRemoveCustomerAddress_ShouldReturnOkResponse() {
        // Given
        UUID partyId = UUID.randomUUID();
        UUID addressId = UUID.randomUUID();
        when(contactService.removeAddress(partyId, addressId))
                .thenReturn(Mono.just(sagaResult));

        // When
        Mono<ResponseEntity<Object>> result = controller.removeCustomerAddress(partyId, addressId);

        // Then
        StepVerifier.create(result)
                .assertNext(response -> assertEquals(200, response.getStatusCodeValue()))
                .verifyComplete();

        verify(contactService).removeAddress(partyId, addressId);
    }

    @Test
    @DisplayName("Should add customer email successfully")
    void testAddCustomerEmail_ShouldReturnOkResponse() {
        // Given
        UUID partyId = UUID.randomUUID();
        RegisterEmailCommand command = mock(RegisterEmailCommand.class);
        when(contactService.addEmail(partyId, command))
                .thenReturn(Mono.just(sagaResult));

        // When
        Mono<ResponseEntity<Object>> result = controller.addCustomerEmail(partyId, command);

        // Then
        StepVerifier.create(result)
                .assertNext(response -> assertEquals(200, response.getStatusCodeValue()))
                .verifyComplete();

        verify(contactService).addEmail(partyId, command);
    }

    @Test
    @DisplayName("Should update customer email successfully")
    void testUpdateCustomerEmail_ShouldReturnOkResponse() {
        // Given
        UUID partyId = UUID.randomUUID();
        UUID emailId = UUID.randomUUID();
        UpdateEmailCommand command = mock(UpdateEmailCommand.class);
        when(contactService.updateEmail(partyId, emailId, command))
                .thenReturn(Mono.empty());

        // When
        Mono<ResponseEntity<Object>> result = controller.updateCustomerEmail(partyId, emailId, command);

        // Then
        StepVerifier.create(result)
                .assertNext(response -> assertEquals(200, response.getStatusCodeValue()))
                .verifyComplete();

        verify(contactService).updateEmail(partyId, emailId, command);
    }

    @Test
    @DisplayName("Should remove customer email successfully")
    void testRemoveCustomerEmail_ShouldReturnOkResponse() {
        // Given
        UUID partyId = UUID.randomUUID();
        UUID emailId = UUID.randomUUID();
        when(contactService.removeEmail(partyId, emailId))
                .thenReturn(Mono.just(sagaResult));

        // When
        Mono<ResponseEntity<Object>> result = controller.removeCustomerEmail(partyId, emailId);

        // Then
        StepVerifier.create(result)
                .assertNext(response -> assertEquals(200, response.getStatusCodeValue()))
                .verifyComplete();

        verify(contactService).removeEmail(partyId, emailId);
    }

    @Test
    @DisplayName("Should add customer phone successfully")
    void testAddCustomerPhone_ShouldReturnOkResponse() {
        // Given
        UUID partyId = UUID.randomUUID();
        RegisterPhoneCommand command = mock(RegisterPhoneCommand.class);
        when(contactService.addPhone(partyId, command))
                .thenReturn(Mono.just(sagaResult));

        // When
        Mono<ResponseEntity<Object>> result = controller.addCustomerPhone(partyId, command);

        // Then
        StepVerifier.create(result)
                .assertNext(response -> assertEquals(200, response.getStatusCodeValue()))
                .verifyComplete();

        verify(contactService).addPhone(partyId, command);
    }

    @Test
    @DisplayName("Should update customer phone successfully")
    void testUpdateCustomerPhone_ShouldReturnOkResponse() {
        // Given
        UUID partyId = UUID.randomUUID();
        UUID phoneId = UUID.randomUUID();
        UpdatePhoneCommand command = mock(UpdatePhoneCommand.class);
        when(contactService.updatePhone(partyId, phoneId, command))
                .thenReturn(Mono.empty());

        // When
        Mono<ResponseEntity<Object>> result = controller.updateCustomerPhone(partyId, phoneId, command);

        // Then
        StepVerifier.create(result)
                .assertNext(response -> assertEquals(200, response.getStatusCodeValue()))
                .verifyComplete();

        verify(contactService).updatePhone(partyId, phoneId, command);
    }

    @Test
    @DisplayName("Should remove customer phone successfully")
    void testRemoveCustomerPhone_ShouldReturnOkResponse() {
        // Given
        UUID partyId = UUID.randomUUID();
        UUID phoneId = UUID.randomUUID();
        when(contactService.removePhone(partyId, phoneId))
                .thenReturn(Mono.just(sagaResult));

        // When
        Mono<ResponseEntity<Object>> result = controller.removeCustomerPhone(partyId, phoneId);

        // Then
        StepVerifier.create(result)
                .assertNext(response -> assertEquals(200, response.getStatusCodeValue()))
                .verifyComplete();

        verify(contactService).removePhone(partyId, phoneId);
    }

    @Test
    @DisplayName("Should set preferred channel successfully")
    void testSetPreferredChannel_ShouldReturnOkResponse() {
        // Given
        UUID partyId = UUID.randomUUID();
        UpdatePreferredChannelCommand command = mock(UpdatePreferredChannelCommand.class);
        when(contactService.setPreferredChannel(partyId, command))
                .thenReturn(Mono.empty());

        // When
        Mono<ResponseEntity<Object>> result = controller.setPreferredChannel(partyId, command);

        // Then
        StepVerifier.create(result)
                .assertNext(response -> assertEquals(200, response.getStatusCodeValue()))
                .verifyComplete();

        verify(contactService).setPreferredChannel(partyId, command);
    }

    @Test
    @DisplayName("Should mark customer dormant successfully")
    void testMarkCustomerDormant_ShouldReturnOkResponse() {
        // Given
        UUID partyId = UUID.randomUUID();
        when(statusService.updateStatus(any(UpdateStatusCommand.class)))
                .thenReturn(Mono.just(sagaResult));

        // When
        Mono<ResponseEntity<Object>> result = controller.markCustomerDormant(partyId);

        // Then
        StepVerifier.create(result)
                .assertNext(response -> assertEquals(200, response.getStatusCodeValue()))
                .verifyComplete();

        verify(statusService).updateStatus(any(UpdateStatusCommand.class));
    }

    @Test
    @DisplayName("Should reactivate customer successfully")
    void testReactivateCustomer_ShouldReturnOkResponse() {
        // Given
        UUID partyId = UUID.randomUUID();
        when(statusService.updateStatus(any(UpdateStatusCommand.class)))
                .thenReturn(Mono.just(sagaResult));

        // When
        Mono<ResponseEntity<Object>> result = controller.reactivateCustomer(partyId);

        // Then
        StepVerifier.create(result)
                .assertNext(response -> assertEquals(200, response.getStatusCodeValue()))
                .verifyComplete();

        verify(statusService).updateStatus(any(UpdateStatusCommand.class));
    }

    @Test
    @DisplayName("Should mark customer deceased successfully")
    void testMarkCustomerDeceased_ShouldReturnOkResponse() {
        // Given
        UUID partyId = UUID.randomUUID();
        when(statusService.updateStatus(any(UpdateStatusCommand.class)))
                .thenReturn(Mono.just(sagaResult));

        // When
        Mono<ResponseEntity<Object>> result = controller.markCustomerDeceased(partyId);

        // Then
        StepVerifier.create(result)
                .assertNext(response -> assertEquals(200, response.getStatusCodeValue()))
                .verifyComplete();

        verify(statusService).updateStatus(any(UpdateStatusCommand.class));
    }

    @Test
    @DisplayName("Should request customer closure successfully")
    void testRequestCustomerClosure_ShouldReturnOkResponse() {
        // Given
        UUID partyId = UUID.randomUUID();
        when(statusService.updateStatus(any(UpdateStatusCommand.class)))
                .thenReturn(Mono.just(sagaResult));

        // When
        Mono<ResponseEntity<Object>> result = controller.requestCustomerClosure(partyId);

        // Then
        StepVerifier.create(result)
                .assertNext(response -> assertEquals(200, response.getStatusCodeValue()))
                .verifyComplete();

        verify(statusService).updateStatus(any(UpdateStatusCommand.class));
    }

    @Test
    @DisplayName("Should confirm customer closure successfully")
    void testConfirmCustomerClosure_ShouldReturnOkResponse() {
        // Given
        UUID partyId = UUID.randomUUID();
        when(statusService.updateStatus(any(UpdateStatusCommand.class)))
                .thenReturn(Mono.just(sagaResult));

        // When
        Mono<ResponseEntity<Object>> result = controller.confirmCustomerClosure(partyId);

        // Then
        StepVerifier.create(result)
                .assertNext(response -> assertEquals(200, response.getStatusCodeValue()))
                .verifyComplete();

        verify(statusService).updateStatus(any(UpdateStatusCommand.class));
    }

    @Test
    @DisplayName("Should lock customer profile successfully")
    void testLockCustomerProfile_ShouldReturnOkResponse() {
        // Given
        UUID partyId = UUID.randomUUID();
        when(statusService.updateStatus(any(UpdateStatusCommand.class)))
                .thenReturn(Mono.just(sagaResult));

        // When
        Mono<ResponseEntity<Object>> result = controller.lockCustomerProfile(partyId);

        // Then
        StepVerifier.create(result)
                .assertNext(response -> assertEquals(200, response.getStatusCodeValue()))
                .verifyComplete();

        verify(statusService).updateStatus(any(UpdateStatusCommand.class));
    }

    @Test
    @DisplayName("Should unlock customer profile successfully")
    void testUnlockCustomerProfile_ShouldReturnOkResponse() {
        // Given
        UUID partyId = UUID.randomUUID();
        when(statusService.updateStatus(any(UpdateStatusCommand.class)))
                .thenReturn(Mono.just(sagaResult));

        // When
        Mono<ResponseEntity<Object>> result = controller.unlockCustomerProfile(partyId);

        // Then
        StepVerifier.create(result)
                .assertNext(response -> assertEquals(200, response.getStatusCodeValue()))
                .verifyComplete();

        verify(statusService).updateStatus(any(UpdateStatusCommand.class));
    }

    @Test
    @DisplayName("Constructor should set service dependencies")
    void testConstructor_ShouldSetService() {
        // When
        CustomersController newController = new CustomersController(registerCustomerService, contactService, statusService);

        // Then
        assertNotNull(newController);
        // Verify it works by calling a method
        UpdateCustomerCommand command = mock(UpdateCustomerCommand.class);
        when(registerCustomerService.updateCustomer(command))
                .thenReturn(Mono.just(sagaResult));

        Mono<ResponseEntity<Object>> result = newController.updateCustomer(command);

        StepVerifier.create(result)
                .assertNext(response -> assertEquals(200, response.getStatusCodeValue()))
                .verifyComplete();
    }
}