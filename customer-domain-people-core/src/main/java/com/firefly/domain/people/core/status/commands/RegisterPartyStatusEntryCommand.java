package com.firefly.domain.people.core.status.commands;

import com.firefly.common.domain.cqrs.command.Command;
import com.firefly.core.customer.sdk.model.PartyStatusDTO;

import java.util.UUID;

public class RegisterPartyStatusEntryCommand extends PartyStatusDTO implements Command<UUID> {
    
    /**
     * Sets the party ID for this command and returns the command instance for method chaining.
     * 
     * @param partyId the party ID to assign to this party status command
     * @return this command instance for method chaining
     */
    public RegisterPartyStatusEntryCommand withPartyId(UUID partyId) {
        this.setPartyId(partyId);
        return this;
    }
}
