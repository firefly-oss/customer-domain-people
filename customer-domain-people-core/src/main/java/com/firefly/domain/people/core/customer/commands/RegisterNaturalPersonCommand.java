package com.firefly.domain.people.core.customer.commands;

import com.firefly.common.domain.cqrs.command.Command;
import com.firefly.core.customer.sdk.model.NaturalPersonDTO;

import java.util.UUID;

public class RegisterNaturalPersonCommand extends NaturalPersonDTO implements Command<UUID> {
    
    /**
     * Sets the party ID for this command and returns the command instance for method chaining.
     * 
     * @param partyId the party ID to assign to this natural person command
     * @return this command instance for method chaining
     */
    public RegisterNaturalPersonCommand withPartyId(UUID partyId) {
        this.setPartyId(partyId);
        return this;
    }
}
