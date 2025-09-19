package com.firefly.domain.people.core.compliance.commands;

import com.firefly.common.domain.cqrs.command.Command;
import com.firefly.core.customer.sdk.model.PoliticallyExposedPersonDTO;

import java.time.LocalDateTime;
import java.util.UUID;

public class RegisterPepCommand extends PoliticallyExposedPersonDTO implements Command<UUID> {
    
    /**
     * Sets the party ID for this command and returns the command instance for method chaining.
     * 
     * @param partyId the party ID to assign to this PEP command
     * @return this command instance for method chaining
     */
    public RegisterPepCommand withPartyId(UUID partyId) {
        this.setPartyId(partyId);
        return this;
    }
}
