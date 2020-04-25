package com.louay.projects.controller.service.member.impl;

import com.louay.projects.controller.service.member.AddGroupInviteController;
import com.louay.projects.controller.service.member.GetGroupInviteController;
import com.louay.projects.model.chains.member.group.GroupInvite;
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
@Component("addGroupInviteCont")
@ComponentScan(basePackages = {"com.louay.projects.model"})
@Scope("prototype")
public class AddGroupInviteControllerImpl implements AddGroupInviteController {
    @Autowired
    @Qualifier("groupDAO")
    private CirclesGroupDAO circlesGroupDAO;

    @Autowired
    @Qualifier("groupInviteCont")
    private GetGroupInviteController getGroupInviteController;

    @Override
    public int addGroupInvite(GroupInvite groupInvite){
        if (groupInvite == null || groupInvite.getSourceGroup().getIdGroup() == null
                || groupInvite.getTargetAccount().getUsername() == null ||
                this.getGroupInviteController.isImInvited(groupInvite)){
            throw new RuntimeException("something null wrong AddGroupInviteControllerImpl.class.addGroupInvite.");

        }
        return this.circlesGroupDAO.insertGroupInvite(groupInvite);
    }
}
