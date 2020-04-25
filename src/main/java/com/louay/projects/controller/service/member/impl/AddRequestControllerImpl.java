package com.louay.projects.controller.service.member.impl;

import com.louay.projects.controller.service.member.AddRequestController;
import com.louay.projects.controller.service.member.GetUserRequestController;
import com.louay.projects.model.chains.member.Request;
import com.louay.projects.model.chains.member.account.FriendRequest;
import com.louay.projects.model.chains.member.group.GroupRequest;
import com.louay.projects.model.dao.CirclesGroupDAO;
import com.louay.projects.model.dao.CirclesUsersDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Controller
@Configuration
@Component("addRequestCont")
@ComponentScan(basePackages = {"com.louay.projects.model", "com.louay.projects.controller"})
@Scope("prototype")
public class AddRequestControllerImpl implements AddRequestController {
    @Autowired
    @Qualifier("usersDAO")
    private CirclesUsersDAO circlesUsersDAO;
    @Autowired
    @Qualifier("groupDAO")
    private CirclesGroupDAO circlesGroupDAO;
    @Autowired
    @Qualifier("userRequestCont")
    private GetUserRequestController getUserRequestController;

    @Override
    public int addRequest(Request request){
        if (request == null || request.getTargetAccount() == null){
            throw new RuntimeException("something null at AddRequestControllerImpl.class");
        }else if (this.getUserRequestController.isRequestSendOrReceive((FriendRequest) request)){
            throw new IllegalArgumentException("request already sent, you cannot send it again.");
        }

        if (request instanceof GroupRequest){
            return this.circlesGroupDAO.insertGroupRequest(request);
        }else if (request instanceof FriendRequest){
            return this.circlesUsersDAO.insertFriendRequest(request);
        }else {
            throw new UnsupportedOperationException();
        }
    }

}
