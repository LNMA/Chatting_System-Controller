package com.louay.projects.controller.service.message.impl;

import com.louay.projects.controller.service.message.GetNotSeenMessageController;
import com.louay.projects.model.chains.accounts.Client;
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
@Component("getNotSeenMessageCont")
@ComponentScan(basePackages = {"com.louay.projects.model"})
@Scope("prototype")
public class GetNotSeenMessageControllerImpl implements GetNotSeenMessageController {
    @Autowired
    @Qualifier("usersDAO")
    private SelectUsersDAO selectUsersDAO;

    @Override
    public Set<AccountMessage> getUsersAndNotSeenMessageBySenderAndReceiver(AccountMessage accountMessage){
        if (accountMessage == null || accountMessage.getSourceUser().getUsername() == null
                || accountMessage.getTargetUser().getUsername() == null ){
            throw new RuntimeException("source or target account may null.");
        }

        Client temp = accountMessage.getTargetUser();
        accountMessage.setTargetUser(accountMessage.getSourceUser());
        accountMessage.setSourceUser(temp);

        return (Set<AccountMessage>) this.selectUsersDAO.findUserMessageAndNumNotSeenBySenderAndReceiver(accountMessage);
    }

    @Override
    public Set<AccountMessage>  getUsersAndNotSeenMessageByReceiver(AccountMessage accountMessage){
        if (accountMessage == null || accountMessage.getTargetUser().getUsername() == null){
            throw new RuntimeException("target username must exist.");
        }

        return (Set<AccountMessage>) this.selectUsersDAO.findUserMessageAndNumNotSeenByReceiver(accountMessage);
    }

    @Override
    public Set<AccountMessage>  getUsersAndNotSeenMessageBySender(AccountMessage accountMessage){
        if (accountMessage == null || accountMessage.getSourceUser().getUsername() == null){
            throw new RuntimeException("source username must exist.");
        }

        return (Set<AccountMessage>) this.selectUsersDAO.findUserMessageAndNumNotSeenBySender(accountMessage);
    }

    @Override
    public int getNumberOfAllNotSeenMessageByReceiver(AccountMessage accountMessage){
        if (accountMessage == null || accountMessage.getTargetUser().getUsername() == null){
            throw new RuntimeException("target username must exist.");
        }
        Set<AccountMessage> messageSet =
                (Set<AccountMessage>) this.selectUsersDAO.findUserMessageAndNumNotSeenByReceiver(accountMessage);

        int result = 0;
        for (AccountMessage m:messageSet) {
            result += m.getNumOfNotSeen();
        }
        return result;
    }
}
