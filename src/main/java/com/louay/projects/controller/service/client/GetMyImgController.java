package com.louay.projects.controller.service.client;


import com.louay.projects.model.chains.accounts.Users;
import com.louay.projects.model.chains.accounts.group.Groups;

import java.util.Set;

public interface GetMyImgController {

    Set<Users> getUserPhoto(Users accountPicture);

    Set<Groups> getGroupPhoto(Groups groups);
}
