package com.louay.projects.controller.service.impl;

import com.louay.projects.model.chains.communications.AccountPicture;
import com.louay.projects.model.chains.users.Users;
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
public class GetMyImgControllerImpl {

    @Autowired
    @Qualifier("usersDAO")
    private SelectUsersDAO selectUsersDAO;

    public Set<AccountPicture> getUserPhoto(AccountPicture accountPicture){
        Set <AccountPicture> container = (Set<AccountPicture>) selectUsersDAO.findPictureByUsername(accountPicture);
        return container;
    }
}
