package com.louay.projects.controller.service.group.impl;

import com.louay.projects.controller.service.group.AddGroupMemberController;
import com.louay.projects.model.chains.member.group.GroupMembers;
import com.louay.projects.model.dao.CirclesGroupDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Controller
@Configuration
@Component("addMemberGroupCont")
@ComponentScan(basePackages = { "com.louay.projects.model"})
@Scope("prototype")
public class AddGroupMemberControllerImpl implements AddGroupMemberController {
    @Autowired
    @Qualifier("groupDAO")
    CirclesGroupDAO circlesGroupDAO;

    @Override
    public int addMember(GroupMembers groupMembers){
        if (groupMembers == null || groupMembers.getGroup().getIdGroup() == null ||
                groupMembers.getFriendMember().getUsername() == null){
            throw new RuntimeException("something null at AddGroupMemberControllerImpl.class");
        }
        return this.circlesGroupDAO.insertGroupMember(groupMembers);
    }
}
