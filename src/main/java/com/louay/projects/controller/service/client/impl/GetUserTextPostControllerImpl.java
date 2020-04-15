package com.louay.projects.controller.service.client.impl;

import com.louay.projects.controller.service.client.GetUserTextPostController;
import com.louay.projects.model.chains.communications.AccountTextPost;
import com.louay.projects.model.dao.SelectUsersDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.LinkedHashSet;

@Controller
@Configuration
@Component("getUserTextPost")
@ComponentScan(basePackages = { "com.louay.projects.model"})
@Scope("prototype")
public class GetUserTextPostControllerImpl implements GetUserTextPostController {

    @Autowired
    @Qualifier("usersDAO")
    SelectUsersDAO selectUsersDAO;

    @Override
    public LinkedHashSet<AccountTextPost> getUserTextPost(AccountTextPost accountTextPost){
        LinkedHashSet<AccountTextPost> linkedHashSet = (LinkedHashSet<AccountTextPost>) selectUsersDAO.findUserTextPostByUsername(accountTextPost);
        if (linkedHashSet.size() < 1){
            accountTextPost.setPost("There is no post here.");
            linkedHashSet.add(accountTextPost);
        }
        return linkedHashSet;
    }
}
