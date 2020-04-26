package com.louay.projects.controller.service.group;

import com.louay.projects.model.chains.accounts.group.Groups;
import com.louay.projects.model.chains.communications.Post;
import com.louay.projects.model.chains.communications.group.GroupImgPost;

import java.util.TreeSet;

public interface GetGroupPostController {
    TreeSet<Post> getGroupPost(Groups groups);

    TreeSet<Post> getGroupImgPost(GroupImgPost groupImgPost);
}
