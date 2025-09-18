package com.firefly.domain.people.core.business.commands;

import com.firefly.common.domain.cqrs.command.Command;
import com.firefly.core.customer.sdk.model.PartyRelationshipDTO;

import java.time.LocalDateTime;
import java.util.UUID;

public class RegisterPartyRelationshipCommand extends PartyRelationshipDTO implements Command<UUID> {}
