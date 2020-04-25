package com.louay.projects.controller.service.search;

import com.louay.projects.model.chains.accounts.Accounts;

import java.util.Set;

public interface GetAccountDetailController {
    Set<? extends Accounts> execute(Accounts account);
}
