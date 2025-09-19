package com.firefly.domain.people.core.compliance.commands;

import com.firefly.common.domain.cqrs.command.Command;
import java.util.UUID;

public record RemoveConsentCommand(
    UUID partyId,
    UUID consentId
) implements Command<Void> {}