package com.louay.projects.controller.service.member.impl;

import com.louay.projects.controller.service.member.AddUserFriendController;
import com.louay.projects.controller.service.member.GetUserFriendController;
import com.louay.projects.model.chains.accounts.Client;
import com.louay.projects.model.chains.member.account.UserFriend;
import com.louay.projects.model.dao.CirclesUsersDAO;
import com.louay.projects.model.dao.SelectUsersDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Controller
@Configuration
@Component("addUserFriendCont")
@ComponentScan(basePackages = {"com.louay.projects.model", "com.louay.projects.controller"})
@Scope("prototype")
public class AddUserFriendControllerImpl implements AddUserFriendController {

    @Autowired
    @Qualifier("userFriendMemberCont")
    private GetUserFriendController getUserFriendController;

    @Autowired
    @Qualifier("usersDAO")
    private CirclesUsersDAO circlesUsersDAO;

    @Override
    public int addUserFriend(UserFriend userFriend){
        if (userFriend == null || userFriend.getUser().getUsername() == null ||
                userFriend.getFriendMember().getUsername() == null){
            throw new RuntimeException("something null at AddUserFriendControllerImpl.class");
        }else if (this.getUserFriendController.isMyFriend(userFriend)){
            throw new IllegalArgumentException("user already friend member.");
        }

        int result = 0;

        result = this.circlesUsersDAO.insertUserFriends(userFriend);

        Client temp = userFriend.getUser();
        userFriend.setUser(userFriend.getFriendMember());
        userFriend.setFriendMember(temp);

        result = this.circlesUsersDAO.insertUserFriends(userFriend);

        Client tempInverse = userFriend.getUser();
        userFriend.setUser(userFriend.getFriendMember());
        userFriend.setFriendMember(tempInverse);

        return result;
    }
}
