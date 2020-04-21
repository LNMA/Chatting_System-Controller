package com.louay.projects.controller.service.message.impl;

import com.louay.projects.controller.service.message.GetNotSeenMessageController;
import com.louay.projects.model.chains.communications.account.AccountMessage;
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
@Component("getNotSeen")
@ComponentScan(basePackages = {"com.louay.projects.model"})
@Scope("prototype")
public class GetNotSeenMessageControllerImpl implements GetNotSeenMessageController {
    @Autowired
    @Qualifier("usersDAO")
    private SelectUsersDAO selectUsersDAO;

    @Override
    public Set<AccountMessage>  getUsersAndNotSeenMessage(AccountMessage accountMessage){
        if (accountMessage == null || accountMessage.getSourceUser().getUsername() == null){
            throw new RuntimeException("sender username must exist.");
        }
        return (Set<AccountMessage>) this.selectUsersDAO.findUserMessageAndNumNotSeenByReceiver(accountMessage);
    }
}
