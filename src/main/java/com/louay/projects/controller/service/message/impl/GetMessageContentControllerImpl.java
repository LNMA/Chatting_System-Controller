package com.louay.projects.controller.service.message.impl;

import com.louay.projects.controller.service.message.GetMessageContentController;
import com.louay.projects.model.chains.accounts.Client;
import com.louay.projects.model.chains.communications.account.AccountMessage;
import com.louay.projects.model.dao.SelectUsersDAO;
import com.louay.projects.model.dao.UpdateUserPostDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.Set;
import java.util.TreeSet;

@Controller
@Configuration
@Component("getMessage")
@ComponentScan(basePackages = {"com.louay.projects.model"})
@Scope("prototype")
public class GetMessageContentControllerImpl implements GetMessageContentController {
    @Autowired
    @Qualifier("usersDAO")
    private SelectUsersDAO selectUsersDAO;

    @Autowired
    @Qualifier("usersDAO")
    private UpdateUserPostDAO updateUserPostDAO;

    @Override
    public TreeSet<AccountMessage> getMessages(AccountMessage accountMessage){
        if (accountMessage == null || accountMessage.getSourceUser().getUsername() == null
                || accountMessage.getTargetUser().getUsername() == null ){
            throw new RuntimeException("source or target account may null.");
        }

        Set<AccountMessage> senderSet =
                (Set<AccountMessage>) this.selectUsersDAO.findUserMessageBySenderAndReceiver(accountMessage);

        Client temp = accountMessage.getTargetUser();
        accountMessage.setTargetUser(accountMessage.getSourceUser());
        accountMessage.setSourceUser(temp);

        accountMessage.setSeen(true);
        this.updateUserPostDAO.updateAccountMassageSeenBySenderAndReceiver(accountMessage);

        Set<AccountMessage> targetSet =
                (Set<AccountMessage>) this.selectUsersDAO.findUserMessageAndTargetPicBySenderAndReceiver(accountMessage);

        TreeSet<AccountMessage> messageTreeSet = new TreeSet<>(senderSet);
        messageTreeSet.addAll(targetSet);

        return messageTreeSet;
    }


}
