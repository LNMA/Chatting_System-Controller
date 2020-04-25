package com.louay.projects.controller.service.post;

import com.louay.projects.model.chains.communications.Post;
import com.louay.projects.model.chains.communications.constant.PostClassName;

public interface DeleteUserPostController {

    int deletePost(Post post, PostClassName postClassName);

}
