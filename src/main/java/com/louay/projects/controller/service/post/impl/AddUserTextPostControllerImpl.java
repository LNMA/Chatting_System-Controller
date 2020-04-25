package com.louay.projects.controller.service.post.impl;

import com.louay.projects.controller.service.post.AddUserTextPostController;
import com.louay.projects.model.chains.communications.account.AccountTextPost;
import com.louay.projects.model.dao.InsertUserPostDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Controller
@Configuration
@Component("addUserTextPost")
@ComponentScan(basePackages = { "com.louay.projects.model"})
@Scope("prototype")
public class AddUserTextPostControllerImpl implements AddUserTextPostController {

    @Autowired
    @Qualifier("usersDAO")
    private InsertUserPostDAO insertUserPostDAO;

    @Override
    public Long insertUserPost(AccountTextPost accountTextPost){
        if (accountTextPost == null || accountTextPost.getUser().getUsername() == null || accountTextPost.getPost() == null){
            throw new RuntimeException("username or post not found");
        }

        return this.insertUserPostDAO.insertAccountTextPost(accountTextPost);
    }
}
