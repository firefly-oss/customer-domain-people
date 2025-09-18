package com.firefly.domain.people.core.business.workflows;

import com.firefly.common.domain.cqrs.command.CommandBus;
import com.firefly.domain.people.core.business.commands.*;
import com.firefly.transactional.annotations.Saga;
import com.firefly.transactional.annotations.SagaStep;
import com.firefly.transactional.annotations.StepEvent;
import com.firefly.transactional.core.SagaContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.UUID;

import static com.firefly.domain.people.core.business.workflows.constants.GlobalConstants.CTX_CUSTOMER_TYPE;
import static com.firefly.domain.people.core.business.workflows.constants.GlobalConstants.CTX_PARTY_ID;
import static com.firefly.domain.people.core.business.workflows.constants.RegisterCustomerConstants.*;


/**
 * Saga orchestrator for customer registration processes.
 * 
 * This orchestrator manages the distributed transaction for registering customers,
 * coordinating multiple steps including party creation, person details registration,
 * contact information setup, and relationship establishment. Each step is designed
 * to be compensatable to ensure data consistency in case of failures.
 * 
 * The orchestrator handles both natural persons and legal entities, with conditional
 * logic to process only relevant information based on the customer type.
 */
@Saga(name = SAGA_REGISTER_CUSTOMER_NAME)
@Service
public class RegisterCustomerSaga {

    private final CommandBus commandBus;

    @Autowired
    public RegisterCustomerSaga(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @SagaStep(id = STEP_REGISTER_PARTY, compensate = COMPENSATE_REMOVE_PARTY)
    @StepEvent(type = EVENT_PARTY_REGISTERED)
    public Mono<UUID> registerParty(RegisterPartyCommand cmd, SagaContext ctx) {
        return commandBus.send(cmd)
                .doOnNext(partyId -> ctx.variables().put(CTX_PARTY_ID, partyId));
    }

    public Mono<Void> removeParty(UUID partyId) {
        return commandBus.send(new RemovePartyCommand(partyId));
    }

    @SagaStep(id = STEP_REGISTER_NATURAL_PERSON, compensate = COMPENSATE_REMOVE_NATURAL_PERSON, dependsOn = STEP_REGISTER_PARTY)
    @StepEvent(type = EVENT_NATURAL_PERSON_REGISTERED)
    public Mono<UUID> registerNaturalPerson(RegisterNaturalPersonCommand cmd, SagaContext ctx) {
        return commandBus.send(cmd.withPartyId((UUID) ctx.variables().get(CTX_PARTY_ID)));
    }

    public Mono<Void> removeNaturalPerson(UUID naturalPersonId, SagaContext ctx) {
        return commandBus.send(new RemoveNaturalPersonCommand((UUID) ctx.variables().get(CTX_PARTY_ID), naturalPersonId));
    }

    @SagaStep(id = STEP_REGISTER_STATUS_ENTRY, compensate = COMPENSATE_REMOVE_STATUS_ENTRY, dependsOn = STEP_REGISTER_PARTY)
    @StepEvent(type = EVENT_PARTY_STATUS_REGISTERED)
    public Mono<UUID> registerStatusEntry(RegisterPartyStatusEntryCommand cmd, SagaContext ctx) {
        return commandBus.send(cmd.withPartyId((UUID) ctx.variables().get(CTX_PARTY_ID)));
    }

    public Mono<Void> removeStatusEntry(UUID partyStatusId, SagaContext ctx) {
        return commandBus.send(new RemovePartyStatusEntryCommand((UUID) ctx.variables().get(CTX_PARTY_ID), partyStatusId));
    }

    @SagaStep(id = STEP_REGISTER_PEP, compensate = COMPENSATE_REMOVE_PEP, dependsOn = STEP_REGISTER_PARTY)
    @StepEvent(type = EVENT_PEP_REGISTERED)
    public Mono<UUID> registerPep(RegisterPepCommand cmd, SagaContext ctx) {
        return commandBus.send(cmd.withPartyId((UUID) ctx.variables().get(CTX_PARTY_ID)));
    }

    public Mono<Void> removePep(UUID pepId, SagaContext ctx) {
        return commandBus.send(new RemovePepCommand((UUID) ctx.variables().get(CTX_PARTY_ID), pepId));
    }

    @SagaStep(id = STEP_REGISTER_IDENTITY_DOCUMENT, compensate = COMPENSATE_REMOVE_IDENTITY_DOCUMENT, dependsOn = STEP_REGISTER_PARTY)
    @StepEvent(type = EVENT_IDENTITY_DOCUMENT_REGISTERED)
    public Mono<UUID> registerIdentityDocument(RegisterIdentityDocumentCommand cmd, SagaContext ctx) {
        return commandBus.send(cmd.withPartyId((UUID) ctx.variables().get(CTX_PARTY_ID)));
    }

    public Mono<Void> removeIdentityDocument(UUID identityDocumentId, SagaContext ctx) {
        return commandBus.send(new RemoveIdentityDocumentCommand((UUID) ctx.variables().get(CTX_PARTY_ID), identityDocumentId));
    }

    @SagaStep(id = STEP_REGISTER_ADDRESS, compensate = COMPENSATE_REMOVE_ADDRESS, dependsOn = STEP_REGISTER_PARTY)
    @StepEvent(type = EVENT_ADDRESS_REGISTERED)
    public Mono<UUID> registerAddress(RegisterAddressCommand cmd, SagaContext ctx) {
        return commandBus.send(cmd.withPartyId((UUID) ctx.variables().get(CTX_PARTY_ID)));
    }

    public Mono<Void> removeAddress(UUID addressId, SagaContext ctx) {
        return commandBus.send(new RemoveAddressCommand((UUID) ctx.variables().get(CTX_PARTY_ID), addressId));
    }

    @SagaStep(id = STEP_REGISTER_EMAIL, compensate = COMPENSATE_REMOVE_EMAIL, dependsOn = STEP_REGISTER_PARTY)
    @StepEvent(type = EVENT_EMAIL_REGISTERED)
    public Mono<UUID> registerEmail(RegisterEmailCommand cmd, SagaContext ctx) {
        return commandBus.send(cmd.withPartyId((UUID) ctx.variables().get(CTX_PARTY_ID)));
    }

    public Mono<Void> removeEmail(UUID emailId, SagaContext ctx) {
        return commandBus.send(new RemoveEmailCommand((UUID) ctx.variables().get(CTX_PARTY_ID), emailId));
    }

    @SagaStep(id = STEP_REGISTER_PHONE, compensate = COMPENSATE_REMOVE_PHONE, dependsOn = STEP_REGISTER_PARTY)
    @StepEvent(type = EVENT_PHONE_REGISTERED)
    public Mono<UUID> registerPhone(RegisterPhoneCommand cmd, SagaContext ctx) {
        return commandBus.send(cmd.withPartyId((UUID) ctx.variables().get(CTX_PARTY_ID)));
    }

    public Mono<Void> removePhone(UUID phoneId, SagaContext ctx) {
        return commandBus.send(new RemovePhoneCommand((UUID) ctx.variables().get(CTX_PARTY_ID), phoneId));
    }

    @SagaStep(id = STEP_REGISTER_ECONOMIC_ACTIVITY_LINK, compensate = COMPENSATE_REMOVE_ECONOMIC_ACTIVITY_LINK, dependsOn = STEP_REGISTER_PARTY)
    @StepEvent(type = EVENT_ECONOMIC_ACTIVITY_REGISTERED)
    public Mono<UUID> registerEconomicActivityLink(RegisterEconomicActivityLinkCommand cmd, SagaContext ctx) {
        return commandBus.send(cmd.withPartyId((UUID) ctx.variables().get(CTX_PARTY_ID)));
    }

    public Mono<Void> removeEconomicActivityLink(UUID economicActivityId, SagaContext ctx) {
        return commandBus.send(new RemoveEconomicActivityLinkCommand((UUID) ctx.variables().get(CTX_PARTY_ID), economicActivityId));
    }

    @SagaStep(id = STEP_REGISTER_CONSENT, compensate = COMPENSATE_REMOVE_CONSENT, dependsOn = STEP_REGISTER_PARTY)
    @StepEvent(type = EVENT_CONSENT_REGISTERED)
    public Mono<UUID> registerConsent(RegisterConsentCommand cmd, SagaContext ctx) {
        return commandBus.send(cmd.withPartyId((UUID) ctx.variables().get(CTX_PARTY_ID)));
    }

    public Mono<Void> removeConsent(UUID consentId, SagaContext ctx) {
        return commandBus.send(new RemoveConsentCommand((UUID) ctx.variables().get(CTX_PARTY_ID), consentId));
    }

    @SagaStep(id = STEP_REGISTER_PARTY_PROVIDER, compensate = COMPENSATE_REMOVE_PARTY_PROVIDER, dependsOn = STEP_REGISTER_PARTY)
    @StepEvent(type = EVENT_PARTY_PROVIDER_REGISTERED)
    public Mono<UUID> registerPartyProvider(RegisterPartyProviderCommand cmd, SagaContext ctx) {
        return commandBus.send(cmd.withPartyId((UUID) ctx.variables().get(CTX_PARTY_ID)));
    }

    public Mono<Void> removePartyProvider(UUID partyProviderId, SagaContext ctx) {
        return commandBus.send(new RemovePartyProviderCommand((UUID) ctx.variables().get(CTX_PARTY_ID), partyProviderId));
    }

    @SagaStep(id = STEP_REGISTER_PARTY_RELATIONSHIP, compensate = COMPENSATE_REMOVE_PARTY_RELATIONSHIP, dependsOn = STEP_REGISTER_PARTY)
    @StepEvent(type = EVENT_PARTY_RELATIONSHIP_REGISTERED)
    public Mono<UUID> registerPartyRelationship(RegisterPartyRelationshipCommand cmd, SagaContext ctx) {
        return commandBus.send(cmd);
    }

    public Mono<Void> removePartyRelationship(UUID partyRelationshipId, SagaContext ctx) {
        return commandBus.send(new RemovePartyRelationshipCommand((UUID) ctx.variables().get(CTX_PARTY_ID), partyRelationshipId));
    }

    @SagaStep(id = STEP_REGISTER_PARTY_GROUP_MEMBERSHIP, compensate = COMPENSATE_REMOVE_PARTY_GROUP_MEMBERSHIP, dependsOn = STEP_REGISTER_PARTY)
    @StepEvent(type = EVENT_PARTY_GROUP_MEMBERSHIP_REGISTERED)
    public Mono<UUID> registerPartyGroupMembership(RegisterPartyGroupMembershipCommand cmd, SagaContext ctx) {
        return commandBus.send(cmd.withPartyId((UUID) ctx.variables().get(CTX_PARTY_ID)));
    }

    public Mono<Void> removePartyGroupMembership(UUID partyGroupMembershipId, SagaContext ctx) {
        return commandBus.send(new RemovePartyGroupMembershipCommand((UUID) ctx.variables().get(CTX_PARTY_ID), partyGroupMembershipId));
    }

}
