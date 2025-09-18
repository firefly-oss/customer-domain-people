package com.firefly.domain.people.core.business.handlers;

import com.firefly.common.domain.cqrs.annotations.CommandHandlerComponent;
import com.firefly.common.domain.cqrs.command.CommandHandler;
import com.firefly.core.customer.sdk.api.PhoneContactsApi;
import com.firefly.domain.people.core.business.commands.RemovePhoneCommand;
import reactor.core.publisher.Mono;

@CommandHandlerComponent
public class RemovePhoneHandler extends CommandHandler<RemovePhoneCommand, Void> {

    private final PhoneContactsApi phoneContactsApi;

    public RemovePhoneHandler(PhoneContactsApi phoneContactsApi) {
        this.phoneContactsApi = phoneContactsApi;
    }

    @Override
    protected Mono<Void> doHandle(RemovePhoneCommand cmd) {
        return phoneContactsApi
                .deletePhoneContact(cmd.partyId(), cmd.phoneId())
                .then();
    }
}