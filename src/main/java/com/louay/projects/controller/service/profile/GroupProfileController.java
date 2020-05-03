package com.louay.projects.controller.service.profile;

import com.louay.projects.model.chains.accounts.group.Groups;

import java.util.Set;

public interface GroupProfileController {
    public Set<Groups> getGroupInfo(Groups groups);

    public int updateGroupProfile(Groups groups, String fieldCode);

    int auxUpdateGroupImg(String idGroup, String fileName, byte[] bytes, String field);
}
