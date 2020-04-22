package com.louay.projects.controller.service.post;

import com.louay.projects.model.chains.accounts.Users;
import com.louay.projects.model.chains.communications.Post;

import java.util.TreeSet;

public interface GetUserCirclePostController {

    TreeSet<Post> getUserCirclesPost(Users users);

    TreeSet<Post> getUserImgPost(Users users);
}
