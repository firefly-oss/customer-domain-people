package com.firefly.domain.people.core.business.commands;

import java.math.BigDecimal;
import java.time.LocalDate;

public record RegisterLegalEntityCommand(
        Long legalEntityId,
        Long partyId,
        String legalName,
        String tradeName,
        String registrationNumber,
        String taxIdNumber,
        Long legalFormId,
        LocalDate incorporationDate,
        String industryDescription,
        Long headcount,
        BigDecimal shareCapital,
        String websiteUrl,
        Long incorporationCountryId
) {}
