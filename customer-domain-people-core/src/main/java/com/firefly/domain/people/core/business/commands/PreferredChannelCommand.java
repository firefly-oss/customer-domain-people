package com.firefly.domain.people.core.business.commands;

public record PreferredChannelCommand(
    Long partyId,
    Long emailId,
    Long phoneId
) {
    public PreferredChannelCommand withPartyId(Long partyId) {
        return new PreferredChannelCommand(
                partyId,
                this.emailId,
                this.phoneId
        );
    }
}
