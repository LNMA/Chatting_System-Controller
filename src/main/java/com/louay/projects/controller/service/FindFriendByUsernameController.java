package com.louay.projects.controller.service;

import com.louay.projects.model.chains.communications.AccountPicture;
import com.louay.projects.model.chains.users.Users;

import java.util.Set;

public interface FindFriendByUsernameController {

    Set<AccountPicture> execute(Users users);
}
