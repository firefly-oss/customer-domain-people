package com.firefly.domain.people.core.business.commands;

import com.firefly.common.domain.cqrs.command.Command;
import java.util.UUID;

public record RemovePhoneCommand(
        UUID partyId,
        UUID phoneId
) implements Command<Void> {}