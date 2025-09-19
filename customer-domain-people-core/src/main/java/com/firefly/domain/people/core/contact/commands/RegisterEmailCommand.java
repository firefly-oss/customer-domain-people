package com.firefly.domain.people.core.contact.commands;

import com.firefly.common.domain.cqrs.command.Command;
import com.firefly.core.customer.sdk.model.EmailContactDTO;

import java.util.UUID;

public class RegisterEmailCommand extends EmailContactDTO implements Command<UUID> {
    
    /**
     * Sets the party ID for this command and returns the command instance for method chaining.
     * 
     * @param partyId the party ID to assign to this email command
     * @return this command instance for method chaining
     */
    public RegisterEmailCommand withPartyId(UUID partyId) {
        this.setPartyId(partyId);
        return this;
    }
}
