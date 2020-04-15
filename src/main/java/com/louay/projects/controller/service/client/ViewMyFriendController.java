package com.louay.projects.controller.service.client;

import com.louay.projects.model.chains.users.Users;
import com.louay.projects.model.chains.util.PictureBase64;

import java.util.List;

public interface ViewMyFriendController {

    List<PictureBase64> execute(Users users);
}
