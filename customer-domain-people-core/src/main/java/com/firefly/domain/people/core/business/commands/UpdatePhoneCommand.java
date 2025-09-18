package com.firefly.domain.people.core.business.commands;

import com.firefly.common.domain.cqrs.command.Command;
import com.firefly.core.customer.sdk.model.EmailContactDTO;
import com.firefly.core.customer.sdk.model.PhoneContactDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
public class UpdatePhoneCommand extends PhoneContactDTO implements Command<UUID> {

    private UUID phoneContactId;

    /**
     * Sets the party ID for this command and returns the command instance for method chaining.
     * 
     * @param partyId the party ID to assign to this address command
     * @return this command instance for method chaining
     */
    public UpdatePhoneCommand withPartyId(UUID partyId) {
        this.setPartyId(partyId);
        return this;
    }

    /**
     * Sets the phone contact ID for this command and returns the command instance for method chaining.
     *
     * @param phoneContactId the phone contact ID to assign to this command
     * @return this command instance for method chaining
     */
    public UpdatePhoneCommand withPhoneContactId(UUID phoneContactId) {
        this.setPhoneContactId(phoneContactId);
        return this;
    }

    /**
     * Sets the primary status for this email and returns the command instance for method chaining.
     *
     * @param primary the primary status to assign to this email
     * @return this command instance for method chaining
     */
    public UpdatePhoneCommand withPrimary(Boolean primary) {
        this.setIsPrimary(primary);
        return this;
    }

}
