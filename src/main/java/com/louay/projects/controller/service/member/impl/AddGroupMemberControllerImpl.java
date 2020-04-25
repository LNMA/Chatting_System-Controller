package com.louay.projects.controller.service.member.impl;

import com.louay.projects.controller.service.member.AddGroupMemberController;
import com.louay.projects.controller.service.member.DeleteGroupInviteController;
import com.louay.projects.controller.service.member.GetGroupMemberController;
import com.louay.projects.model.chains.member.group.GroupInvite;
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
@Component("addGroupMemberCont")
@ComponentScan(basePackages = {"com.louay.projects.model", "com.louay.projects.controller"})
@Scope("prototype")
public class AddGroupMemberControllerImpl implements AddGroupMemberController {
    @Autowired
    @Qualifier("groupDAO")
    private CirclesGroupDAO circlesGroupDAO;

    @Autowired
    private GroupInvite invite;

    @Autowired
    @Qualifier("groupMemberCont")
    private GetGroupMemberController getGroupMemberController;

    @Autowired
    @Qualifier("deleteGroupInviteCont")
    private DeleteGroupInviteController deleteGroupInviteController;

    @Override
    public boolean addGroupMemberInvite(GroupMembers groupMembers){
        if (groupMembers == null || groupMembers.getGroup().getIdGroup() == null ||
                groupMembers.getFriendMember().getUsername() == null ||
                this.getGroupMemberController.isImMember(groupMembers) ){
            throw new RuntimeException("something wrong at AddGroupMemberControllerImpl.class.addGroupMemberInvite");
        }
        int result = 0;

        this.invite.getSourceGroup().setIdGroup(groupMembers.getGroup().getIdGroup());
        this.invite.getTargetAccount().setUsername(groupMembers.getFriendMember().getUsername());

        result += this.deleteGroupInviteController.deleteInvite(this.invite);

        result += this.circlesGroupDAO.insertGroupMember(groupMembers);

        return result > 1;
    }

    @Override
    public int addMember(GroupMembers groupMembers){
        if (groupMembers == null || groupMembers.getGroup().getIdGroup() == null ||
                groupMembers.getFriendMember().getUsername() == null){
            throw new RuntimeException("something null at AddGroupMemberControllerImpl.class");
        }
        return this.circlesGroupDAO.insertGroupMember(groupMembers) ;
    }

}
