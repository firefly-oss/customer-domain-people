package com.firefly.domain.people.core.business.handlers;

import com.firefly.common.domain.cqrs.annotations.CommandHandlerComponent;
import com.firefly.common.domain.cqrs.command.CommandHandler;
import com.firefly.core.customer.sdk.api.PhoneContactsApi;
import com.firefly.domain.people.core.business.commands.UpdatePhoneCommand;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.UUID;

@CommandHandlerComponent
public class UpdatePhoneHandler extends CommandHandler<UpdatePhoneCommand, UUID> {

    private final PhoneContactsApi phoneContactsApi;

    public UpdatePhoneHandler(PhoneContactsApi phoneContactsApi) {
        this.phoneContactsApi = phoneContactsApi;
    }

    @Override
    protected Mono<UUID> doHandle(UpdatePhoneCommand cmd) {
        return phoneContactsApi.updatePhoneContact(cmd.getPartyId(), cmd.getPhoneContactId(), cmd, UUID.randomUUID().toString())
                .mapNotNull(phoneContactDTO ->
                        Objects.requireNonNull(Objects.requireNonNull(phoneContactDTO).getPhoneContactId()));
    }
}