package com.louay.projects.controller.service.member.impl;

import com.louay.projects.controller.service.member.GetGroupInviteController;
import com.louay.projects.model.chains.member.group.GroupInvite;
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
@Component("groupInviteCont")
@ComponentScan(basePackages = {"com.louay.projects.model"})
@Scope("prototype")
public class GetGroupInviteControllerImpl implements GetGroupInviteController {
    @Autowired
    @Qualifier("groupDAO")
    private SelectGroupDAO selectGroupDAO;

    @Override
    public Map<Long, GroupInvite> getGroupInviteByIdAndUsername(GroupInvite invite){
        if (invite == null || invite.getSourceGroup().getIdGroup() == null || invite.getTargetAccount().getUsername() == null){
            throw new RuntimeException("something null at GetGroupInviteControllerImpl.class.getGroupInviteByIdAndUsername");
        }
        return this.selectGroupDAO.findGroupInviteByUsernameAndIdGroup(invite);
    }

    @Override
    public boolean isImInvited(GroupInvite invite){
        return !getGroupInviteByIdAndUsername(invite).isEmpty();
    }

    @Override
    public Map<Long, GroupInvite> getGroupInviteAndTargetInfoByUsername(GroupInvite invite){
        if (invite == null || invite.getSourceGroup().getIdGroup() == null || invite.getTargetAccount().getUsername() == null){
            throw new RuntimeException("something null at GetGroupInviteControllerImpl.class.getGroupInviteAndTargetInfoByUsername");
        }
        return this.selectGroupDAO.findGroupInviteAndTargetInfoByUsername(invite);
    }

    @Override
    public Map<Long, GroupInvite> getGroupInviteAndTargetInfoByIdGroup(GroupInvite invite){
        if (invite == null || invite.getSourceGroup().getIdGroup() == null || invite.getTargetAccount().getUsername() == null){
            throw new RuntimeException("something null at GetGroupInviteControllerImpl.class.getGroupInviteAndTargetInfoByIdGroup.");
        }
        return this.selectGroupDAO.findGroupInviteAndTargetInfoByIdGroup(invite);
    }

    @Override
    public Map<Long, GroupInvite> getGroupInviteAndGroupPicByUsername(GroupInvite invite){
        if (invite == null || invite.getSourceGroup().getIdGroup() == null || invite.getTargetAccount().getUsername() == null){
            throw new RuntimeException("something null at GetGroupInviteControllerImpl.class.getGroupInviteAndGroupPicByUsername");
        }
        return this.selectGroupDAO.findGroupInviteAndGroupPicByUsername(invite);
    }
}
