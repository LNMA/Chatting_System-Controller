package com.louay.projects.controller.service.post;


import com.louay.projects.model.chains.communications.Post;
import com.louay.projects.model.chains.communications.constant.PostClassName;

import java.util.Set;

public interface GetUserEditPostController {
    Set<? extends Post> getUserPost(Post post, PostClassName postClassName);
}
