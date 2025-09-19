package com.firefly.domain.people.core.contact.commands;

import com.firefly.common.domain.cqrs.command.Command;
import com.firefly.core.customer.sdk.model.AddressDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
public class RegisterAddressCommand extends AddressDTO implements Command<UUID> {

    private UUID addressId;

    /**
     * Sets the party ID for this command and returns the command instance for method chaining.
     * 
     * @param partyId the party ID to assign to this address command
     * @return this command instance for method chaining
     */
    public RegisterAddressCommand withPartyId(UUID partyId) {
        this.setPartyId(partyId);
        return this;
    }

}
