package com.louay.projects.controller.service.message;

import com.louay.projects.model.chains.communications.account.AccountMessage;

public interface AddMessageUserController {
    Long addMessage(AccountMessage accountMessage);
}
