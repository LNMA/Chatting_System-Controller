package com.louay.projects.controller.service.group;

import com.louay.projects.model.chains.communications.group.GroupTextPost;

public interface AddGroupPostController {

    Long addGroupImgPost(String username, String idGroup, String fileName, byte[] bytes);

    Long addGroupTextPost(GroupTextPost groupTextPost);
}
