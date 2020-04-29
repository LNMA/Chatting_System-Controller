package com.louay.projects.controller.service.member;

import com.louay.projects.model.chains.member.Request;
import com.louay.projects.model.chains.member.account.FriendRequest;

import java.util.Map;

public interface GetUserRequestController {
    Map<Long, FriendRequest> getUserRequestBySenderAndReceiver(FriendRequest request);

    boolean isRequestSendOrReceive(FriendRequest request);

    Map<Long , Request> getSentRequestAndPicByReceiver(FriendRequest request);

    int numberOfReceiveRequest(FriendRequest request);

    Map<Long, Request> getSentRequestAndPicBySender(FriendRequest request);
}
