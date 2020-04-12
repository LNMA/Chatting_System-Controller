package com.louay.projects.controller.service.impl;

import com.louay.projects.controller.service.SignUpClientController;
import com.louay.projects.model.chains.users.Users;
import com.louay.projects.model.dao.CreateUsersDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Controller
@Configuration
@Component("signUpControl")
@ComponentScan(basePackages = { "com.louay.projects.model"})
@Scope("prototype")
public class SignUpClientControllerImpl implements SignUpClientController {

    @Autowired
    @Qualifier("usersDAO")
    private CreateUsersDAO createUsersDAO;


    @Override
    public boolean execute(Users users) {
        if (users == null || users.getUsername() == null || users.getPassword() == null || users.getAccountPermission() == null){
            throw new RuntimeException("Username and password and account permission at least must be exist.");
        }else {
            int accountResult = this.createUsersDAO.insertUser(users);
            int detailResult = this.createUsersDAO.insertClientDetail(users);
            return accountResult+detailResult > 1;
        }
    }

}
