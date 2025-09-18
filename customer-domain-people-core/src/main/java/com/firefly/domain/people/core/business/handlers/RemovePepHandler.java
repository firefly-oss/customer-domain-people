package com.firefly.domain.people.core.business.handlers;

import com.firefly.common.domain.cqrs.annotations.CommandHandlerComponent;
import com.firefly.common.domain.cqrs.command.CommandHandler;
import com.firefly.core.customer.sdk.api.PoliticallyExposedPersonsApi;
import com.firefly.domain.people.core.business.commands.RemovePepCommand;
import reactor.core.publisher.Mono;

import java.util.UUID;

@CommandHandlerComponent
public class RemovePepHandler extends CommandHandler<RemovePepCommand, Void> {

    private final PoliticallyExposedPersonsApi politicallyExposedPersonsApi;

    public RemovePepHandler(PoliticallyExposedPersonsApi politicallyExposedPersonsApi) {
        this.politicallyExposedPersonsApi = politicallyExposedPersonsApi;
    }

    @Override
    protected Mono<Void> doHandle(RemovePepCommand cmd) {
        return politicallyExposedPersonsApi
                .deletePoliticallyExposedPerson(cmd.partyId(), cmd.pepId())
                .then();
    }
}