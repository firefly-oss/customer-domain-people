package com.firefly.domain.people.core.business.handlers;

import com.firefly.common.domain.cqrs.annotations.CommandHandlerComponent;
import com.firefly.common.domain.cqrs.command.CommandHandler;
import com.firefly.core.customer.sdk.api.EmailContactsApi;
import com.firefly.domain.people.core.business.commands.RemoveEmailCommand;
import reactor.core.publisher.Mono;

@CommandHandlerComponent
public class RemoveEmailHandler extends CommandHandler<RemoveEmailCommand, Void> {

    private final EmailContactsApi emailContactsApi;

    public RemoveEmailHandler(EmailContactsApi emailContactsApi) {
        this.emailContactsApi = emailContactsApi;
    }

    @Override
    protected Mono<Void> doHandle(RemoveEmailCommand cmd) {
        return emailContactsApi
                .deleteEmailContact(cmd.partyId(), cmd.emailId())
                .then();
    }
}