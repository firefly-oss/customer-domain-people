package com.firefly.domain.people.core.business.handlers;

import com.firefly.common.domain.cqrs.annotations.CommandHandlerComponent;
import com.firefly.common.domain.cqrs.command.CommandHandler;
import com.firefly.core.customer.sdk.api.EmailContactsApi;
import com.firefly.core.customer.sdk.model.EmailContactDTO;
import com.firefly.domain.people.core.business.commands.RegisterEmailCommand;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.UUID;

@CommandHandlerComponent
public class RegisterEmailHandler extends CommandHandler<RegisterEmailCommand, UUID> {

    private final EmailContactsApi emailContactsApi;

    public RegisterEmailHandler(EmailContactsApi emailContactsApi) {
        this.emailContactsApi = emailContactsApi;
    }

    @Override
    protected Mono<UUID> doHandle(RegisterEmailCommand cmd) {
        return emailContactsApi
                .createEmailContact(cmd.getPartyId(), cmd, UUID.randomUUID().toString())
                .mapNotNull(emailContactDTO ->
                        Objects.requireNonNull(Objects.requireNonNull(emailContactDTO).getEmailContactId()));
    }
}