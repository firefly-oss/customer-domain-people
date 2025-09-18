package com.firefly.domain.people.core.business.handlers;

import com.firefly.common.domain.cqrs.annotations.CommandHandlerComponent;
import com.firefly.common.domain.cqrs.command.CommandHandler;
import com.firefly.core.customer.sdk.api.PartyEconomicActivitiesApi;
import com.firefly.domain.people.core.business.commands.RemoveEconomicActivityLinkCommand;
import reactor.core.publisher.Mono;

@CommandHandlerComponent
public class RemoveEconomicActivityHandler extends CommandHandler<RemoveEconomicActivityLinkCommand, Void> {

    private final PartyEconomicActivitiesApi partyEconomicActivitiesApi;

    public RemoveEconomicActivityHandler(PartyEconomicActivitiesApi partyEconomicActivitiesApi) {
        this.partyEconomicActivitiesApi = partyEconomicActivitiesApi;
    }

    @Override
    protected Mono<Void> doHandle(RemoveEconomicActivityLinkCommand cmd) {
        return partyEconomicActivitiesApi
                .deletePartyEconomicActivity(cmd.partyId(), cmd.economicActivityLinkId())
                .then();
    }
}