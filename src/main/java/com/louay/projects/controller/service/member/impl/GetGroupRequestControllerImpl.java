package com.louay.projects.controller.service.member.impl;

import com.louay.projects.controller.service.member.GetGroupRequestController;
import com.louay.projects.model.chains.member.group.GroupRequest;
import com.louay.projects.model.dao.SelectGroupDAO;
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
@Component("groupRequestCont")
@ComponentScan(basePackages = {"com.louay.projects.model"})
@Scope("prototype")
public class GetGroupRequestControllerImpl implements GetGroupRequestController {
    @Autowired
    @Qualifier("groupDAO")
    private SelectGroupDAO selectGroupDAO;

    @Override
    public Map<Long, GroupRequest> getGroupRequest(GroupRequest request){
        if (request == null || request.getSourceGroup().getIdGroup() == null || request.getTargetAccount().getUsername() == null){
            throw new RuntimeException("something null at GetGroupMemberControllerImpl.class.getGroupRequest");
        }
        return this.selectGroupDAO.findGroupRequestByUsernameAndIdGroup(request);
    }

    @Override
    public boolean isRequestSent(GroupRequest request){
        return !getGroupRequest(request).isEmpty();
    }


    @Override
    public Map<Long, GroupRequest> getGroupRequestAndInfoByUsername(GroupRequest request){
        if (request == null || request.getSourceGroup().getIdGroup() == null || request.getTargetAccount().getUsername() == null){
            throw new RuntimeException("something null at GetGroupMemberControllerImpl.class.getGroupRequestAndInfoByTargetAcc");
        }
        return this.selectGroupDAO.findGroupRequestAndInfoByUsername(request);
    }

    @Override
    public Map<Long, GroupRequest> getGroupRequestAndInfoByIdGroup(GroupRequest request){
        if (request == null || request.getSourceGroup().getIdGroup() == null || request.getTargetAccount().getUsername() == null){
            throw new RuntimeException("something null at GetGroupMemberControllerImpl.class.getGroupRequestAndInfoByTargetAcc");
        }
        return this.selectGroupDAO.findGroupRequestAndInfoByIdGroup(request);
    }
}
