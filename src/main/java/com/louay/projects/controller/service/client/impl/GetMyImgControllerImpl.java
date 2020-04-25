package com.louay.projects.controller.service.client.impl;

import com.louay.projects.controller.service.client.GetMyImgController;
import com.louay.projects.model.chains.accounts.Users;
import com.louay.projects.model.chains.accounts.group.Groups;
import com.louay.projects.model.dao.SelectGroupDAO;
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

    @Autowired
    @Qualifier("groupDAO")
    private SelectGroupDAO selectGroupDAO;

    @Override
    public Set<Users> getUserPhoto(Users users){
        if (users == null || users.getUsername() == null){
            throw new RuntimeException("something null at GetMyImgControllerImpl.class");
        }
        return (Set<Users>) this.selectUsersDAO.findPictureByUsername(users);
    }

    @Override
    public Set<Groups> getGroupPhoto(Groups groups){
        if (groups == null || groups.getIdGroup() == null){
            throw new RuntimeException("something null at GetMyImgControllerImpl.class");
        }
        return (Set<Groups>) this.selectGroupDAO.findGroupPictureByIdGroup(groups);
    }
}
