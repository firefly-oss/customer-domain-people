package com.firefly.domain.people.core.business.handlers;

import com.firefly.common.domain.cqrs.annotations.CommandHandlerComponent;
import com.firefly.common.domain.cqrs.command.CommandHandler;
import com.firefly.core.customer.sdk.api.IdentityDocumentsApi;
import com.firefly.domain.people.core.business.commands.RemoveIdentityDocumentCommand;
import reactor.core.publisher.Mono;

import java.util.UUID;

@CommandHandlerComponent
public class RemoveIdentityDocumentHandler extends CommandHandler<RemoveIdentityDocumentCommand, Void> {

    private final IdentityDocumentsApi identityDocumentsApi;

    public RemoveIdentityDocumentHandler(IdentityDocumentsApi identityDocumentsApi) {
        this.identityDocumentsApi = identityDocumentsApi;
    }

    @Override
    protected Mono<Void> doHandle(RemoveIdentityDocumentCommand cmd) {
        return identityDocumentsApi
                .deleteIdentityDocument(cmd.partyId(), cmd.identityDocumentId())
                .then();
    }
}