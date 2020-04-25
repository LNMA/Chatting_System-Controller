package com.louay.projects.controller.service.member;

import com.louay.projects.model.chains.accounts.group.Groups;
import com.louay.projects.model.chains.member.group.GroupMembers;

import java.util.Map;

public interface GetGroupMemberController {
    Map<Long, GroupMembers> getMemberByIdAndUsername(GroupMembers members);

    boolean isImMember(GroupMembers members);

    Map<Long, GroupMembers> getGroupMemberAndInfo(Groups groups);
}
