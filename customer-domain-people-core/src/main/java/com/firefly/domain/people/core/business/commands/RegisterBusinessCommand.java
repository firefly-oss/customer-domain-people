package com.firefly.domain.people.core.business.commands;

import java.util.List;


public record RegisterBusinessCommand(
        RegisterPartyCommand party,
        RegisterLegalEntityCommand naturalPerson,
        List<RegisterPartyStatusEntryCommand> statusHistory,
        List<RegisterIdentityDocumentCommand> identityDocuments,
        List<RegisterAddressCommand> addresses,
        List<RegisterEmailCommand> emails,
        List<RegisterPhoneCommand> phones,
        List<RegisterEconomicActivityLinkCommand> economicActivities,
        List<RegisterPartyProviderCommand> providers,
        List<RegisterPartyRelationshipCommand> relationships,
        List<RegisterPartyGroupMembershipCommand> groupMemberships
) {}
