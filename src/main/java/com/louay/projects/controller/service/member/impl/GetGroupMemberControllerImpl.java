package com.louay.projects.controller.service.member.impl;

import com.louay.projects.controller.service.member.GetGroupMemberController;
import com.louay.projects.model.chains.accounts.group.Groups;
import com.louay.projects.model.chains.member.group.GroupMembers;
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
@Component("groupMemberCont")
@ComponentScan(basePackages = {"com.louay.projects.model"})
@Scope("prototype")
public class GetGroupMemberControllerImpl implements GetGroupMemberController {
    @Autowired
    @Qualifier("groupDAO")
    private SelectGroupDAO selectGroupDAO;

    @Override
    public Map<Long, GroupMembers> getMemberByIdAndUsername(GroupMembers members){
        if (members == null || members.getGroup().getIdGroup() == null || members.getFriendMember().getUsername() == null){
            throw new RuntimeException("something null at GetGroupMemberControllerImpl.class.getMemberByIdAndUsername");
        }
        return this.selectGroupDAO.findGroupMemberByUsernameAndIdGroup(members);
    }

    @Override
    public boolean isImMember(GroupMembers members){
        return !getMemberByIdAndUsername(members).isEmpty();
    }

    @Override
    public Map<Long, GroupMembers> getGroupMemberAndInfo(Groups groups) {
        if (groups == null || groups.getIdGroup() == null) {
            throw new RuntimeException("something null at GetGroupMemberControllerImpl.class.getGroupMemberAndInfo");
        }
        return this.selectGroupDAO.findGroupMemberAndInfoByIdGroup(groups);
    }
}
