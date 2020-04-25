package com.louay.projects.controller.service.group;

import com.louay.projects.model.chains.accounts.Users;
import com.louay.projects.model.chains.member.group.GroupMembers;

import java.util.Map;

public interface GetUserGroupController {
    Map<Long, GroupMembers> getGroup(Users users);
}
