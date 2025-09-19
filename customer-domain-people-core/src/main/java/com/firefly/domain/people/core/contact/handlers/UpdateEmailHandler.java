package com.firefly.domain.people.core.contact.handlers;

import com.firefly.common.domain.cqrs.annotations.CommandHandlerComponent;
import com.firefly.common.domain.cqrs.command.CommandHandler;
import com.firefly.core.customer.sdk.api.AddressesApi;
import com.firefly.core.customer.sdk.api.EmailContactsApi;
import com.firefly.domain.people.core.contact.commands.UpdateAddressCommand;
import com.firefly.domain.people.core.contact.commands.UpdateEmailCommand;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.UUID;

@CommandHandlerComponent
public class UpdateEmailHandler extends CommandHandler<UpdateEmailCommand, UUID> {

    private final EmailContactsApi emailContactsApi;

    public UpdateEmailHandler(EmailContactsApi emailContactsApi) {
        this.emailContactsApi = emailContactsApi;
    }

    @Override
    protected Mono<UUID> doHandle(UpdateEmailCommand cmd) {
        return emailContactsApi.updateEmailContact(cmd.getPartyId(), cmd.getEmailContactId(), cmd, UUID.randomUUID().toString())
                .mapNotNull(emailContactDTO ->
                        Objects.requireNonNull(Objects.requireNonNull(emailContactDTO).getEmailContactId()));
    }
}