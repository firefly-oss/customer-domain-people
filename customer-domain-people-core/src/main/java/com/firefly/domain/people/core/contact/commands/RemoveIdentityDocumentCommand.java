package com.firefly.domain.people.core.contact.commands;

import com.firefly.common.domain.cqrs.command.Command;

import java.util.UUID;

public record RemoveIdentityDocumentCommand(
        UUID partyId,
        UUID identityDocumentId
) implements Command<Void> {}