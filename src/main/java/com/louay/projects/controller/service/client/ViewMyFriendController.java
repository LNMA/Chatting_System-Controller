package com.louay.projects.controller.service.client;

import com.louay.projects.model.chains.accounts.Users;

import java.util.Set;

public interface ViewMyFriendController {

    Set<Users> execute(Users users);
}
