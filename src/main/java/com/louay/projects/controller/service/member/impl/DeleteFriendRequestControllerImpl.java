package com.louay.projects.controller.service.member.impl;

import com.louay.projects.controller.service.member.DeleteFriendRequestController;
import com.louay.projects.model.chains.member.account.FriendRequest;
import com.louay.projects.model.dao.DeleteUserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Controller
@Configuration
@Component("deleteFriendRequestCont")
@ComponentScan(basePackages = {"com.louay.projects.model"})
@Scope("prototype")
public class DeleteFriendRequestControllerImpl implements DeleteFriendRequestController {
    @Autowired
    @Qualifier("usersDAO")
    DeleteUserDAO deleteUserDAO;

    @Override
    public int deleteUserRequestByUserAndFriend(FriendRequest request){
        if (request == null || request.getSourceAccount().getUsername() == null || request.getTargetAccount().getUsername() ==null){
            throw new RuntimeException("something null at DeleteFriendRequestControllerImpl.class.");
        }
        return this.deleteUserDAO.deleteFriendRequestBySenderAndReceiver(request);
    }
}
