package com.louay.projects.controller.service.member;

import com.louay.projects.model.chains.member.account.UserFriend;

import java.util.Map;

public interface GetUserFriendController {
    Map<Long, UserFriend> getMyFriend(UserFriend userFriend);

    boolean isMyFriend(UserFriend userFriend);
}
