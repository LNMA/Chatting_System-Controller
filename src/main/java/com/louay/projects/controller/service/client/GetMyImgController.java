package com.louay.projects.controller.service.client;


import com.louay.projects.model.chains.communications.account.AccountPicture;

import java.util.Set;

public interface GetMyImgController {

    Set<AccountPicture> getUserPhoto(AccountPicture accountPicture);
}
