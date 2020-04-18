package com.louay.projects.controller.service.search.impl;


import com.louay.projects.controller.service.search.SearchAccountGroupController;
import com.louay.projects.model.chains.accounts.Accounts;
import com.louay.projects.model.chains.accounts.Admin;
import com.louay.projects.model.chains.accounts.Users;
import com.louay.projects.model.chains.accounts.group.Groups;
import com.louay.projects.model.dao.SelectGroupDAO;
import com.louay.projects.model.dao.SelectUsersDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.HashSet;
import java.util.Set;

@Controller
@Configuration
@Component("searchAccountGroup")
@ComponentScan(basePackages = {"com.louay.projects.model"})
@Scope("prototype")
public class SearchAccountGroupControllerImpl implements SearchAccountGroupController {
    @Autowired
    @Qualifier("usersDAO")
    private SelectUsersDAO selectUsersDAO;

    @Autowired
    @Qualifier("groupDAO")
    private SelectGroupDAO selectGroupDAO;

    @Autowired
    @Qualifier("buildAnnotationContextControl")
    private ApplicationContext context;

    @Override
    public Set<Accounts> getSearchResult(String key) {
        Set<Users> usersSet = (Set<Users>) this.selectUsersDAO.findUserInAccountByLikeUsername(buildUser(key));
        Set<Groups> groupsSet = (Set<Groups>) this.selectGroupDAO.findGroupDetailByLikeIdGroup(buildGroupDetail(key));

        Set<Accounts> accountsSet = new HashSet<>(usersSet);
        accountsSet.addAll(groupsSet);

        return accountsSet;
    }

    private Users buildUser(String username){
        Users users = this.context.getBean(Admin.class);
        users.setUsername(username);
        return users;
    }

    private Groups buildGroupDetail(String idGroup){
        Groups groupsDetail = this.context.getBean(Groups.class);
        groupsDetail.setIdGroup(idGroup);
        return groupsDetail;
    }

}
