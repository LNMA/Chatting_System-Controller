package com.louay.projects.controller.service.member.impl;

import com.louay.projects.controller.service.member.GetUserRequestController;
import com.louay.projects.model.chains.accounts.Client;
import com.louay.projects.model.chains.member.account.FriendRequest;
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
@Component("userRequestCont")
@ComponentScan(basePackages = {"com.louay.projects.model"})
@Scope("prototype")
public class GetUserRequestControllerImpl implements GetUserRequestController {
    @Autowired
    @Qualifier("usersDAO")
    private SelectUsersDAO selectUsersDAO;

    @Override
    public Map<Long, FriendRequest> getUserRequestBySenderAndReceiver(FriendRequest request){
        if (request == null || request.getSourceAccount().getUsername() == null
                || request.getTargetAccount().getUsername() == null){
            throw new RuntimeException("something null at GetUserRequestControllerImpl.class");
        }
        return this.selectUsersDAO.findFriendRequestBySenderAndReceiver(request);
    }

    @Override
    public boolean isRequestSendOrReceive(FriendRequest request){
        Map<Long, FriendRequest> sendRequest = getUserRequestBySenderAndReceiver(request);

        Client temp = request.getTargetAccount();
        request.setTargetAccount(request.getSourceAccount());
        request.setSourceAccount(temp);

        Map<Long, FriendRequest> receiveRequest = getUserRequestBySenderAndReceiver(request);

        Client tempInverse = request.getTargetAccount();
        request.setTargetAccount(request.getSourceAccount());
        request.setSourceAccount(tempInverse);

        return !(sendRequest.isEmpty() && receiveRequest.isEmpty());
    }

    @Override
    public Map<Long, FriendRequest> getSentRequestAndPicByReceiver(FriendRequest request){
        if (request == null || request.getTargetAccount().getUsername() == null){
            throw new RuntimeException("something null at GetUserRequestControllerImpl.class");
        }
        return this.selectUsersDAO.findFriendRequestAndPicByReceiver(request);
    }

    @Override
    public Map<Long, FriendRequest> getSentRequestAndPicBySender(FriendRequest request){
        if (request == null || request.getSourceAccount().getUsername() == null){
            throw new RuntimeException("something null at GetUserRequestControllerImpl.class");
        }
        return this.selectUsersDAO.findFriendRequestAndPicBySender(request);
    }
}
