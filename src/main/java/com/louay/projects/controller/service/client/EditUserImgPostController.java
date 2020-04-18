package com.louay.projects.controller.service.client;

import com.louay.projects.model.chains.communications.Post;
import com.louay.projects.model.chains.communications.constant.PostClassName;

public interface EditUserImgPostController {

    int editImgPost(PostClassName postClassName, Post post, String filename, byte [] bytes);
}
