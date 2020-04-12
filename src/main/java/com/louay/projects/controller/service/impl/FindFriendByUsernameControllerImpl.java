package com.louay.projects.controller.service.impl;

import com.louay.projects.controller.service.FindFriendByUsernameController;
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
@Component("findFriendByName")
@ComponentScan(basePackages = { "com.louay.projects.model"})
@Scope("prototype")
public class FindFriendByUsernameControllerImpl implements FindFriendByUsernameController {

    @Autowired
    @Qualifier("usersDAO")
    private SelectUsersDAO selectUsersDAO;


    @Override
    public Set<AccountPicture> execute(Users users) {
        if (users == null || users.getUsername() == null || "".equals(users.getUsername())){
            throw new RuntimeException("Username must be exist to perform this operation.");
        }
        Set<AccountPicture> container = (Set<AccountPicture>) selectUsersDAO.findFriendAndPictureByUsername(users);
        return container;
    }
}
