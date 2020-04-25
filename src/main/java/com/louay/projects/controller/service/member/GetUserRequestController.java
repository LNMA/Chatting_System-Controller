package com.louay.projects.controller.service.member;

import com.louay.projects.model.chains.member.Request;
import com.louay.projects.model.chains.member.account.FriendRequest;

import java.util.Map;
import java.util.TreeMap;

public interface GetUserRequestController {
    Map<Long, FriendRequest> getUserRequestBySenderAndReceiver(FriendRequest request);

    boolean isRequestSendOrReceive(FriendRequest request);

    TreeMap<Long , Request> getSentRequestAndPicByReceiver(FriendRequest request);

    Map<Long, FriendRequest> getSentRequestAndPicBySender(FriendRequest request);
}
