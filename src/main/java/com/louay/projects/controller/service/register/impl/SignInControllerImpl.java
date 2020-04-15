package com.louay.projects.controller.service.register.impl;

import com.louay.projects.controller.service.register.SignInController;
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
@Component("isSignUp")
@ComponentScan(basePackages = { "com.louay.projects.model"})
@Scope("prototype")
public class SignInControllerImpl implements SignInController {

    @Autowired
    @Qualifier("usersDAO")
    private SelectUsersDAO selectUsersDAO;
    private Set<Users> container;

    @Override
    public boolean isSignUp(Users users){
        if (users.getUsername() == null || users.getPassword() == null || users == null){
            throw new RuntimeException("We need password and username to accomplished this operation.");
        }
        this.container = (Set<Users>) selectUsersDAO.findUserByUsernameAndPassword(users);
       return !this.container.isEmpty();
    }

    @Override
    public Set<Users> getUsers(){
        return this.container;
    }
}
