package com.louay.projects.controller.service.client.impl;

import com.louay.projects.controller.service.client.GetMyImgController;
import com.louay.projects.model.chains.communications.AccountPicture;
import com.louay.projects.model.dao.SelectUsersDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.Set;

@Controller
@Configuration
@Component("getMyImg")
@ComponentScan(basePackages = { "com.louay.projects.model"})
@Scope("prototype")
public class GetMyImgControllerImpl implements GetMyImgController {

    @Autowired
    @Qualifier("usersDAO")
    private SelectUsersDAO selectUsersDAO;

    @Override
    public Set<AccountPicture> getUserPhoto(AccountPicture accountPicture){
        return (Set<AccountPicture>) this.selectUsersDAO.findPictureByUsername(accountPicture);
    }
}
