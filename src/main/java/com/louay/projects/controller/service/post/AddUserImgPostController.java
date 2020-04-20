package com.louay.projects.controller.service.post;

public interface AddUserImgPostController {

    Long addImgPost(String username, String fileName, byte[] bytes);
}
