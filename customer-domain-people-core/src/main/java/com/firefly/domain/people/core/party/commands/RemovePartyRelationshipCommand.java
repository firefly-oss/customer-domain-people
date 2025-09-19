package com.firefly.domain.people.core.party.commands;

import com.firefly.common.domain.cqrs.command.Command;
import java.util.UUID;

public record RemovePartyRelationshipCommand(
    UUID partyId,
    UUID partyRelationshipId
) implements Command<Void> {}