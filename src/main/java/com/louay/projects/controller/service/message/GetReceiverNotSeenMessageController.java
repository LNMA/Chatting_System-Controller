package com.louay.projects.controller.service.message;

import com.louay.projects.model.chains.communications.account.AccountMessage;

import java.util.Set;

public interface GetReceiverNotSeenMessageController {
    Set<AccountMessage> getNotSeen(AccountMessage accountMessage);
}