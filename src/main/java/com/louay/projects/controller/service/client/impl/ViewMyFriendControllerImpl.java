package com.louay.projects.controller.service.client.impl;

import com.louay.projects.controller.service.client.ViewMyFriendController;

import com.louay.projects.model.chains.accounts.Users;
import com.louay.projects.model.dao.SelectUsersDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.*;

@Controller
@Configuration
@Component("findFriendByName")
@ComponentScan(basePackages = {"com.louay.projects.model"})
@Scope("prototype")
public class ViewMyFriendControllerImpl implements ViewMyFriendController {

    @Autowired
    @Qualifier("usersDAO")
    private SelectUsersDAO selectUsersDAO;


    @Override
    public Set<Users> execute(Users users) {
        if (users == null || users.getUsername() == null || "".equals(users.getUsername())) {
            throw new RuntimeException("Username must be exist to perform this operation.");
        }
        return (Set<Users>) selectUsersDAO.findFriendAndPictureByUsername(users);
    }

}
