package com.louay.projects.controller.service.member;

import com.louay.projects.model.chains.member.group.GroupInvite;

import java.util.Map;

public interface GetGroupInviteController {
    Map<Long, GroupInvite> getGroupInviteByIdAndUsername(GroupInvite invite);

    boolean isImInvited(GroupInvite invite);

    Map<Long, GroupInvite> getGroupInviteAndTargetInfoByUsername(GroupInvite invite);

    Map<Long, GroupInvite> getGroupInviteAndGroupPicByUsername(GroupInvite invite);

    Map<Long, GroupInvite> getGroupInviteAndTargetInfoByIdGroup(GroupInvite invite);
}
