package com.firefly.domain.people.core.customer.commands;

import com.firefly.common.domain.cqrs.command.Command;

import java.util.UUID;

public record RemoveNaturalPersonCommand(
        UUID partyId,
        UUID naturalPersonId
) implements Command<Void>{}