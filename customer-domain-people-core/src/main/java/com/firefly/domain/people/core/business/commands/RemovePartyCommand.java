package com.firefly.domain.people.core.business.commands;

import com.firefly.common.domain.cqrs.command.Command;
import com.firefly.core.customer.sdk.model.PartyDTO;

import java.util.UUID;

public record RemovePartyCommand(
        UUID partyId
) implements Command<Void>{}
