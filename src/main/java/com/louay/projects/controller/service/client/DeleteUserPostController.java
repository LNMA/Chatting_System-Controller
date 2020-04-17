package com.louay.projects.controller.service.client;

import com.louay.projects.model.chains.communications.Post;
import com.louay.projects.model.chains.communications.PostClassName;

public interface DeleteUserPostController {

    int deletePost(Post post, PostClassName postClassName);

}
