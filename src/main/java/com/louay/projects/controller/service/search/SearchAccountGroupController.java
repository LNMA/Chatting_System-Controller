package com.louay.projects.controller.service.search;

import com.louay.projects.model.chains.accounts.Accounts;

import java.util.Set;

public interface SearchAccountGroupController {
    Set<Accounts> getSearchResult(String key);
}
