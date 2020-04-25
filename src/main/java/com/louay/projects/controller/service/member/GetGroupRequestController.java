package com.louay.projects.controller.service.member;

import com.louay.projects.model.chains.member.group.GroupRequest;

import java.util.Map;

public interface GetGroupRequestController {
    Map<Long, GroupRequest> getGroupRequest(GroupRequest request);

    boolean isRequestSent(GroupRequest request);

    Map<Long, GroupRequest> getGroupRequestAndInfoByUsername(GroupRequest request);

    Map<Long, GroupRequest> getGroupRequestAndInfoByIdGroup(GroupRequest request);
}
