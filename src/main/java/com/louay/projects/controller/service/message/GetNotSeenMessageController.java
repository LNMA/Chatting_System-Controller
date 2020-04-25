package com.louay.projects.controller.service.message;

import com.louay.projects.model.chains.communications.account.AccountMessage;

import java.util.Set;

public interface GetNotSeenMessageController {
    Set<AccountMessage> getUsersAndNotSeenMessageBySenderAndReceiver(AccountMessage accountMessage);

    Set<AccountMessage>  getUsersAndNotSeenMessageByReceiver(AccountMessage accountMessage);

    Set<AccountMessage>  getUsersAndNotSeenMessageBySender(AccountMessage accountMessage);

    int getNumberOfAllNotSeenMessageByReceiver(AccountMessage accountMessage);
}
