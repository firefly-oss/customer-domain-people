package com.firefly.domain.people.core.business.commands;

import com.firefly.common.domain.cqrs.command.Command;
import java.util.UUID;

public record RemoveEmailCommand(
    UUID partyId,
    UUID emailId
) implements Command<Void> {}