package com.louay.projects.controller.service.client;

import com.louay.projects.model.chains.communications.AccountTextPost;

import java.util.LinkedHashSet;

public interface GetUserTextPostController {
    LinkedHashSet<AccountTextPost> getUserTextPost(AccountTextPost accountTextPost);
}
