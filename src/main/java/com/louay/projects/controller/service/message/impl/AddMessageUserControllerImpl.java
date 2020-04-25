package com.louay.projects.controller.service.message.impl;

import com.louay.projects.controller.service.message.AddMessageUserController;
import com.louay.projects.model.chains.communications.account.AccountMessage;
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
@Component("addMessage")
@ComponentScan(basePackages = {"com.louay.projects.model"})
@Scope("prototype")
public class AddMessageUserControllerImpl implements AddMessageUserController {
    @Autowired
    @Qualifier("usersDAO")
    private InsertUserPostDAO insertUserPostDAO;

    @Override
    public Long addMessage(AccountMessage accountMessage){
        if (accountMessage == null || accountMessage.getSourceUser().getUsername() == null ||
                accountMessage.getTargetUser().getUsername() == null || accountMessage.getMessage() == null || accountMessage.getMessage().length() < 1){
            return (long)-1;
        }
        return this.insertUserPostDAO.insertAccountMassage(accountMessage);
    }
}
