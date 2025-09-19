package com.firefly.domain.people.core.business.services.impl;

import com.firefly.domain.people.core.business.commands.RegisterBusinessCommand;
import com.firefly.domain.people.core.business.commands.UpdateBusinessCommand;
import com.firefly.domain.people.core.business.services.BusinessService;
import com.firefly.domain.people.core.business.workflows.UpdateBusinessSaga;
import com.firefly.domain.people.core.customer.workflows.RegisterCustomerSaga;
import com.firefly.transactional.core.SagaResult;
import com.firefly.transactional.engine.ExpandEach;
import com.firefly.transactional.engine.SagaEngine;
import com.firefly.transactional.engine.StepInputs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class BusinessServiceImpl implements BusinessService {

    private final SagaEngine engine;

    @Autowired
    public BusinessServiceImpl(SagaEngine engine) {
        this.engine = engine;
    }


    @Override
    public Mono<SagaResult> registerBusiness(RegisterBusinessCommand command) {
        StepInputs inputs = StepInputs.builder()
                .forStep(RegisterCustomerSaga::registerParty, command.party())
                .forStep(RegisterCustomerSaga::registerLegalEntity, command.legalEntity())
                .forStep(RegisterCustomerSaga::registerStatusEntry, ExpandEach.of(command.statusHistory()))
                .forStep(RegisterCustomerSaga::registerIdentityDocument, ExpandEach.of(command.identityDocuments()))
                .forStep(RegisterCustomerSaga::registerAddress, ExpandEach.of(command.addresses()))
                .forStep(RegisterCustomerSaga::registerEmail, ExpandEach.of(command.emails()))
                .forStep(RegisterCustomerSaga::registerPhone, ExpandEach.of(command.phones()))
                .forStep(RegisterCustomerSaga::registerEconomicActivityLink, ExpandEach.of(command.economicActivities()))
                .forStep(RegisterCustomerSaga::registerPartyProvider, ExpandEach.of(command.providers()))
                .forStep(RegisterCustomerSaga::registerPartyRelationship, ExpandEach.of(command.relationships()))
                .forStep(RegisterCustomerSaga::registerPartyGroupMembership, ExpandEach.of(command.groupMemberships()))
                .build();

        return engine.execute(RegisterCustomerSaga.class, inputs);
    }

    @Override
    public Mono<SagaResult> updateBusiness(UpdateBusinessCommand command) {
        StepInputs inputs = StepInputs.builder()
                .forStep(UpdateBusinessSaga::updateBusiness, command)
                .build();

        return engine.execute(UpdateBusinessSaga.class, inputs);
    }
}
