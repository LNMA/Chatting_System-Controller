package com.louay.projects.controller.service.group;

import com.louay.projects.model.chains.accounts.group.Groups;
import com.louay.projects.model.chains.communications.Post;

import java.util.TreeSet;

public interface GetGroupPostController {
    TreeSet<Post> getGroupPost(Groups groups);
}
