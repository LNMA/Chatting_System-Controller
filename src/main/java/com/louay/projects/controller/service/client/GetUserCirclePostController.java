package com.louay.projects.controller.service.client;

import com.louay.projects.model.chains.communications.Post;
import com.louay.projects.model.chains.users.Users;

import java.util.TreeSet;

public interface GetUserCirclePostController {

    TreeSet<Post> getUserCirclesPost(Users users);
}
