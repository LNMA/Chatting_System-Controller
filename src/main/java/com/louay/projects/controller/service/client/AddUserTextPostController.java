package com.louay.projects.controller.service.client;


import com.louay.projects.model.chains.communications.account.AccountTextPost;

public interface AddUserTextPostController {
    Long insertUserPost(AccountTextPost accountTextPost);
}
