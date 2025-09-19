package com.firefly.domain.people.core.party.commands;

import com.firefly.common.domain.cqrs.command.Command;
import com.firefly.core.customer.sdk.model.PartyRelationshipDTO;

import java.util.UUID;

public class RegisterPartyRelationshipCommand extends PartyRelationshipDTO implements Command<UUID> {}
