package com.louay.projects.controller.service.group.impl;

import com.louay.projects.controller.service.group.GetUserGroupController;
import com.louay.projects.model.chains.accounts.Users;
import com.louay.projects.model.chains.member.group.GroupMembers;
import com.louay.projects.model.dao.SelectGroupDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.Map;

@Controller
@Configuration
@Component("getUserGroupCont")
@ComponentScan(basePackages = { "com.louay.projects.model"})
@Scope("prototype")
public class GetUserGroupControllerImpl implements GetUserGroupController {
    @Autowired
    @Qualifier("groupDAO")
    SelectGroupDAO selectGroupDAO;

    @Override
    public Map<Long, GroupMembers> getGroup(Users users){
        if (users == null || users.getUsername() == null ){
            throw  new RuntimeException("something null at GetUserGroupControllerImpl.class");
        }
        return this.selectGroupDAO.findGroupAndPicAndUserByUsername(users);
    }


}
