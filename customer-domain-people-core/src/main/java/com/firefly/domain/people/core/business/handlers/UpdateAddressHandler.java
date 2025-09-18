package com.firefly.domain.people.core.business.handlers;

import com.firefly.common.domain.cqrs.annotations.CommandHandlerComponent;
import com.firefly.common.domain.cqrs.command.CommandHandler;
import com.firefly.core.customer.sdk.api.AddressesApi;
import com.firefly.core.customer.sdk.api.NaturalPersonsApi;
import com.firefly.domain.people.core.business.commands.UpdateAddressCommand;
import com.firefly.domain.people.core.business.commands.UpdateCustomerCommand;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.UUID;

@CommandHandlerComponent
public class UpdateAddressHandler extends CommandHandler<UpdateAddressCommand, UUID> {

    private final AddressesApi addressesApi;

    public UpdateAddressHandler(AddressesApi addressesApi) {
        this.addressesApi = addressesApi;
    }

    @Override
    protected Mono<UUID> doHandle(UpdateAddressCommand cmd) {
        return addressesApi.updateAddress(cmd.getPartyId(), cmd.getAddressId(), cmd, UUID.randomUUID().toString())
                .mapNotNull(addressDTO ->
                        Objects.requireNonNull(Objects.requireNonNull(addressDTO).getAddressId()));
    }
}