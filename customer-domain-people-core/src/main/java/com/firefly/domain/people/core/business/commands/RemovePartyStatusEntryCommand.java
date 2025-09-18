package com.firefly.domain.people.core.business.commands;

import com.firefly.common.domain.cqrs.command.Command;

import java.util.UUID;

public record RemovePartyStatusEntryCommand(
        UUID partyId,
        UUID partyStatusId
) implements Command<Void> {}