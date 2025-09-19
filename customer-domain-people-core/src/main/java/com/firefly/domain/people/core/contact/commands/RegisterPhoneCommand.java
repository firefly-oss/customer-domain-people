package com.firefly.domain.people.core.contact.commands;

import com.firefly.common.domain.cqrs.command.Command;
import com.firefly.core.customer.sdk.model.PhoneContactDTO;

import java.util.UUID;

public class RegisterPhoneCommand extends PhoneContactDTO implements Command<UUID> {
    
    /**
     * Sets the party ID for this command and returns the command instance for method chaining.
     * 
     * @param partyId the party ID to assign to this phone command
     * @return this command instance for method chaining
     */
    public RegisterPhoneCommand withPartyId(UUID partyId) {
        this.setPartyId(partyId);
        return this;
    }
}
