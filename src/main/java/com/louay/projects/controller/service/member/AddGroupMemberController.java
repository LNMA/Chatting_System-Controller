package com.louay.projects.controller.service.member;

import com.louay.projects.model.chains.member.group.GroupMembers;

public interface AddGroupMemberController {
    boolean addGroupMemberInvite(GroupMembers groupMembers);

    boolean addGroupMemberRequest(GroupMembers groupMembers);

    int addMember(GroupMembers groupMembers);
}
