package com.louay.projects.controller.service.member.impl;

import com.louay.projects.controller.service.member.DeleteGroupInviteController;
import com.louay.projects.model.chains.member.group.GroupInvite;
import com.louay.projects.model.dao.DeleteGroupDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Controller
@Configuration
@Component("deleteGroupInviteCont")
@ComponentScan(basePackages = {"com.louay.projects.model"})
@Scope("prototype")
public class DeleteGroupInviteControllerImpl implements DeleteGroupInviteController {
    @Autowired
    @Qualifier("groupDAO")
    private DeleteGroupDAO deleteGroupDAO;

    @Override
    public int deleteInvite(GroupInvite invite){
        if (invite == null || invite.getSourceGroup().getIdGroup() == null || invite.getTargetAccount().getUsername() == null){
            throw new RuntimeException("something null at DeleteGroupInviteControllerImpl.class.deleteInvite");
        }
        return this.deleteGroupDAO.deleteGroupInviteByIdGroupAndUsername(invite);
    }
}
