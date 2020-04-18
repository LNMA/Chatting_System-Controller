package com.louay.projects.controller.service.register;


import com.louay.projects.model.chains.accounts.Users;

import java.util.Set;

public interface SignInController {

    boolean isSignUp(Users users);

    Set<Users> getUsers();
}
