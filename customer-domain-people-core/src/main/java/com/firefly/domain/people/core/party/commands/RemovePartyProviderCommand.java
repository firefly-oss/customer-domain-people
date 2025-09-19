package com.firefly.domain.people.core.party.commands;

import com.firefly.common.domain.cqrs.command.Command;
import java.util.UUID;

public record RemovePartyProviderCommand(
    UUID partyId,
    UUID partyProviderId
) implements Command<Void> {}