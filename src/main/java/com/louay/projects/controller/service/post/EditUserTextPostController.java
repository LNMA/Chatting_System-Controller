package com.louay.projects.controller.service.post;

import com.louay.projects.model.chains.communications.Post;
import com.louay.projects.model.chains.communications.constant.PostClassName;

public interface EditUserTextPostController {

    int editTextPost(PostClassName postClassName, Post post);

}
