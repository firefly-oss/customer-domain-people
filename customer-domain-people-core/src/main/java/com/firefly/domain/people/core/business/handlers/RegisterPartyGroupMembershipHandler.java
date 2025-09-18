package com.firefly.domain.people.core.business.handlers;

import com.firefly.common.domain.cqrs.annotations.CommandHandlerComponent;
import com.firefly.common.domain.cqrs.command.CommandHandler;
import com.firefly.core.customer.sdk.api.PartyGroupMembershipsApi;
import com.firefly.core.customer.sdk.api.PartyRelationshipsApi;
import com.firefly.domain.people.core.business.commands.RegisterPartyGroupMembershipCommand;
import com.firefly.domain.people.core.business.commands.RegisterPartyRelationshipCommand;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.UUID;

@CommandHandlerComponent
public class RegisterPartyGroupMembershipHandler extends CommandHandler<RegisterPartyGroupMembershipCommand, UUID> {

    private final PartyGroupMembershipsApi partyGroupMembershipsApi;

    public RegisterPartyGroupMembershipHandler(PartyGroupMembershipsApi partyGroupMembershipsApi) {
        this.partyGroupMembershipsApi = partyGroupMembershipsApi;
    }

    @Override
    protected Mono<UUID> doHandle(RegisterPartyGroupMembershipCommand cmd) {
        return partyGroupMembershipsApi
                .createPartyGroupMembership(cmd.getPartyId(), cmd, UUID.randomUUID().toString())
                .mapNotNull(partyProviderDTO ->
                        Objects.requireNonNull(Objects.requireNonNull(partyProviderDTO).getPartyGroupMembershipId()));
    }
}