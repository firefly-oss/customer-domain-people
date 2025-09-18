package com.firefly.domain.people.core.business.commands;

import com.firefly.common.domain.cqrs.command.Command;
import com.firefly.core.customer.sdk.model.AddressDTO;
import com.firefly.core.customer.sdk.model.EmailContactDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateEmailCommand extends EmailContactDTO implements Command<UUID> {

    private UUID emailContactId;

    /**
     * Sets the party ID for this command and returns the command instance for method chaining.
     * 
     * @param partyId the party ID to assign to this address command
     * @return this command instance for method chaining
     */
    public UpdateEmailCommand withPartyId(UUID partyId) {
        this.setPartyId(partyId);
        return this;
    }


    /**
     * Sets the email contact ID for this command and returns the command instance for method chaining.
     *
     * @param emailId the email contact ID to assign to this command
     * @return this command instance for method chaining
     */
    public UpdateEmailCommand withEmailContactId(UUID emailId) {
        this.setEmailContactId(emailId);
        return this;
    }

}
