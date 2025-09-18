package com.firefly.domain.people.core.business.handlers;

import com.firefly.common.domain.cqrs.annotations.CommandHandlerComponent;
import com.firefly.common.domain.cqrs.command.CommandHandler;
import com.firefly.core.customer.sdk.api.AddressesApi;
import com.firefly.domain.people.core.business.commands.RemoveAddressCommand;
import reactor.core.publisher.Mono;

import java.util.UUID;

@CommandHandlerComponent
public class RemoveAddressHandler extends CommandHandler<RemoveAddressCommand, Void> {

    private final AddressesApi addressesApi;

    public RemoveAddressHandler(AddressesApi addressesApi) {
        this.addressesApi = addressesApi;
    }

    @Override
    protected Mono<Void> doHandle(RemoveAddressCommand cmd) {
        return addressesApi
                .deleteAddress(cmd.partyId(), cmd.addressId())
                .then();
    }
}