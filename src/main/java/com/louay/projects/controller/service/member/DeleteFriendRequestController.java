package com.louay.projects.controller.service.member;

import com.louay.projects.model.chains.member.account.FriendRequest;

public interface DeleteFriendRequestController {
    int deleteUserRequestByUserAndFriend(FriendRequest request);
}
