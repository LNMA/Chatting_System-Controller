package com.louay.projects.controller.service.message;

import com.louay.projects.model.chains.communications.account.AccountMessage;

import java.util.TreeSet;

public interface GetMessageContentController {
    TreeSet<AccountMessage> getMessages(AccountMessage accountMessage);
}
