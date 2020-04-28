package com.louay.projects.controller.service.profile;

import com.louay.projects.model.chains.accounts.Client;
import com.louay.projects.model.chains.accounts.Users;

import java.util.Set;

public interface UserProfileController {
    Set<Users> getUserInfo(Users users);

    int updateUserProfile(Users users, String fieldCode);

    public int auxUpdateUserPassword(Client client, String oldPassword, String newPassword,
                                     String reNewPassword, String fieldCode);

    int auxUpdateUserImg(String username, String fileName, byte[] bytes, String field);
}
