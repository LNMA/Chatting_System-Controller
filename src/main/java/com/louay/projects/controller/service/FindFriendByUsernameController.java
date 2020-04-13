package com.louay.projects.controller.service;

import com.louay.projects.model.chains.users.Users;
import com.louay.projects.model.chains.util.PictureDirection;

import java.util.List;

public interface FindFriendByUsernameController {

    List<PictureDirection> execute(Users users);
}
