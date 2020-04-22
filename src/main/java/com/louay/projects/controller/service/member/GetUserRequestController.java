package com.louay.projects.controller.service.member;

import com.louay.projects.model.chains.member.account.FriendRequest;

import java.util.Map;

public interface GetUserRequestController {
    Map<Long, FriendRequest> getUserRequestBySenderAndReceiver(FriendRequest request);

    boolean isRequestSendOrReceive(FriendRequest request);

    Map<Long, FriendRequest> getSentRequestAndPicByReceiver(FriendRequest request);

    Map<Long, FriendRequest> getSentRequestAndPicBySender(FriendRequest request);
}
