package com.louay.projects.controller.service.member;

import com.louay.projects.model.chains.member.group.GroupInvite;
import com.louay.projects.model.chains.member.group.GroupRequest;

public interface DeleteGroupInviteRequestController {
    int deleteInvite(GroupInvite invite);

    int deleteRequest(GroupRequest request);
}
