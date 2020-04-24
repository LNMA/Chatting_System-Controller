package com.louay.projects.controller.service.group;

import com.louay.projects.model.chains.accounts.group.Groups;

public interface AddGroupController {
    boolean createGroup(Groups groups, String username);

    int addMasterMember(Groups groups, String username);

    int uploadDefaultImg(Groups groups);
}
