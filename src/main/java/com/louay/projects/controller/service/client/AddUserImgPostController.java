package com.louay.projects.controller.service.client;

public interface AddUserImgPostController {

    Long addImgPost(String username, String fileName, byte[] bytes);
}
