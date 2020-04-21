package com.louay.projects.controller.service.member.impl;

import com.louay.projects.controller.service.member.GetUserFriendController;
import com.louay.projects.model.chains.member.account.UserFriend;
import com.louay.projects.model.dao.SelectUsersDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.Map;

@Controller
@Configuration
@Component("userFriendMemberCont")
@ComponentScan(basePackages = {"com.louay.projects.model"})
@Scope("prototype")
public class GetUserFriendControllerImpl implements GetUserFriendController {
    @Autowired
    @Qualifier("usersDAO")
    private SelectUsersDAO selectUsersDAO;

    @Override
    public Map<Long, UserFriend> getMyFriend(UserFriend userFriend){
        if (userFriend == null || userFriend.getUser().getUsername() == null ||
                userFriend.getFriendMember().getUsername() == null){
            throw  new RuntimeException("userFriend or username or friendUsername may null at GetUserFriendControllerImpl.class");
        }
        return this.selectUsersDAO.findUserFriendByUserAndFriend(userFriend);
    }

    @Override
    public boolean isMyFriend(UserFriend userFriend){
        return !getMyFriend(userFriend).isEmpty();
    }
}
