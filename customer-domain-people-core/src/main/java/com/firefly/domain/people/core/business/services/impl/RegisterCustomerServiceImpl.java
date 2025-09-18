package com.firefly.domain.people.core.business.services.impl;

import com.firefly.domain.people.core.business.commands.*;
import com.firefly.domain.people.core.business.commands.UpdateCustomerCommand;
import com.firefly.domain.people.core.business.services.RegisterCustomerService;
import com.firefly.domain.people.core.business.workflows.AddAddressSaga;
import com.firefly.domain.people.core.business.workflows.RegisterCustomerSaga;
import com.firefly.domain.people.core.business.workflows.RemoveAddressSaga;
import com.firefly.domain.people.core.business.workflows.UpdateAddressSaga;
import com.firefly.domain.people.core.business.workflows.UpdateCustomerNameSaga;
import com.firefly.transactional.core.SagaResult;
import com.firefly.transactional.engine.ExpandEach;
import com.firefly.transactional.engine.SagaEngine;
import com.firefly.transactional.engine.StepInputs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class RegisterCustomerServiceImpl implements RegisterCustomerService {

    private final SagaEngine engine;

    @Autowired
    public RegisterCustomerServiceImpl(SagaEngine engine) {
        this.engine = engine;
    }


    @Override
    public Mono<SagaResult> registerCustomer(RegisterCustomerCommand command) {
        StepInputs inputs = StepInputs.builder()
                .forStep(RegisterCustomerSaga::registerParty, command.party())
                .forStep(RegisterCustomerSaga::registerNaturalPerson, command.naturalPerson())
                .forStep(RegisterCustomerSaga::registerStatusEntry, ExpandEach.of(command.statusHistory()))
                .forStep(RegisterCustomerSaga::registerPep, command.pep())
                .forStep(RegisterCustomerSaga::registerIdentityDocument, ExpandEach.of(command.identityDocuments()))
                .forStep(RegisterCustomerSaga::registerAddress, ExpandEach.of(command.addresses()))
                .forStep(RegisterCustomerSaga::registerEmail, ExpandEach.of(command.emails()))
                .forStep(RegisterCustomerSaga::registerPhone, ExpandEach.of(command.phones()))
                .forStep(RegisterCustomerSaga::registerEconomicActivityLink, ExpandEach.of(command.economicActivities()))
                .forStep(RegisterCustomerSaga::registerConsent, ExpandEach.of(command.consents()))
                .forStep(RegisterCustomerSaga::registerPartyProvider, ExpandEach.of(command.providers()))
                .forStep(RegisterCustomerSaga::registerPartyRelationship, ExpandEach.of(command.relationships()))
                .forStep(RegisterCustomerSaga::registerPartyGroupMembership, ExpandEach.of(command.groupMemberships()))
                .build();

        return engine.execute(RegisterCustomerSaga.class, inputs);
    }

    @Override
    public Mono<SagaResult> updateCustomer(UpdateCustomerCommand command) {
        StepInputs inputs = StepInputs.builder()
                .forStep(UpdateCustomerNameSaga::updateName, command)
                .build();

        return engine.execute(UpdateCustomerNameSaga.class, inputs);

    }

    @Override
    public Mono<SagaResult> addAddress(UUID partyId, RegisterAddressCommand addressCommand) {
        StepInputs inputs = StepInputs.builder()
                .forStep(AddAddressSaga::registerAddress, addressCommand.withPartyId(partyId))
                .build();

        return engine.execute(AddAddressSaga.class, inputs);
    }

    @Override
    public Mono<Void> updateAddress(UUID partyId, UUID addressId, UpdateAddressCommand addressData) {
        StepInputs inputs = StepInputs.builder()
                .forStep(UpdateAddressSaga::updateAddress, addressData.withAddressId(addressId).withPartyId(partyId))
                .build();

        return engine.execute(UpdateAddressSaga.class, inputs)
                .then();
    }

    @Override
    public Mono<SagaResult> removeAddress(UUID partyId, UUID addressId) {
        StepInputs inputs = StepInputs.builder()
                .forStep(RemoveAddressSaga::removeAddress, new RemoveAddressCommand(partyId, addressId))
                .build();

        return engine.execute(RemoveAddressSaga.class, inputs);
    }

}
