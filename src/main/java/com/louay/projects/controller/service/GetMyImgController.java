package com.louay.projects.controller.service;

import com.louay.projects.model.chains.communications.AccountPicture;

import java.util.Set;

public interface GetMyImgController {

    Set<AccountPicture> getUserPhoto(AccountPicture accountPicture);
}
