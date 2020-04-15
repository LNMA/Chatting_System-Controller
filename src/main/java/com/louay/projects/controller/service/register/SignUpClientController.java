package com.louay.projects.controller.service.register;

import com.louay.projects.model.chains.users.Users;

public interface SignUpClientController {
    boolean execute(Users users);
    int uploadDefaultImg(Users users);
}

