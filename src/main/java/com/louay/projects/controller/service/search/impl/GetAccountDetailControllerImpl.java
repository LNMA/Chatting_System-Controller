package com.louay.projects.controller.service.search.impl;

import com.louay.projects.controller.service.search.GetAccountDetailController;
import com.louay.projects.model.chains.accounts.Accounts;
import com.louay.projects.model.chains.accounts.Client;
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
@Component("getAccountDetail")
@ComponentScan(basePackages = {"com.louay.projects.model"})
@Scope("prototype")
public class GetAccountDetailControllerImpl implements GetAccountDetailController {
    @Autowired
    @Qualifier("usersDAO")
    private SelectUsersDAO selectUsersDAO;
    @Autowired
    @Qualifier("groupDAO")
    private SelectGroupDAO selectGroupDAO;

    @Override
    public Set<? extends Accounts> execute(Accounts account){
        if (account == null){
            throw new RuntimeException("account may be null at GetAccountDetailControllerImpl.class.");
        }

        if (account instanceof Users){
            Client client = (Client) account;
            return (Set<? extends Accounts>) this.selectUsersDAO.findUserInAccountDetailByUsername(client);

        }else if (account instanceof Groups){
            Groups groups = (Groups) account;
            return (Set<? extends Accounts>) this.selectGroupDAO.findGroupDetailByIdGroup(groups);

        }else {
            throw new UnsupportedOperationException("only client and groups are supported to perform this operation.");
        }
    }
}
