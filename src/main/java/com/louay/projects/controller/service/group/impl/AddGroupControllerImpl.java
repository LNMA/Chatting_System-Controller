package com.louay.projects.controller.service.group.impl;

import com.louay.projects.controller.service.group.AddGroupController;
import com.louay.projects.controller.service.member.AddGroupMemberController;
import com.louay.projects.model.chains.accounts.group.Groups;
import com.louay.projects.model.chains.member.constant.GroupMemberType;
import com.louay.projects.model.chains.member.group.GroupMembers;
import com.louay.projects.model.dao.CreateGroupsDAO;
import com.louay.projects.model.dao.InsertGroupPostDAO;
import com.louay.projects.model.util.date.NowDate;
import com.louay.projects.model.util.file.FileProcess;
import com.louay.projects.model.util.pool.MyConnectionPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.sql.SQLException;

@Controller
@Configuration
@Component("addGroupCont")
@ComponentScan(basePackages = { "com.louay.projects.model",  "com.louay.projects.controller"})
@Scope("prototype")
public class AddGroupControllerImpl implements AddGroupController {
    @Autowired
    @Qualifier("groupDAO")
    CreateGroupsDAO createGroupsDAO;

    @Autowired
    @Qualifier("groupDAO")
    private InsertGroupPostDAO insertGroupPostDAO;

    @Autowired
    @Qualifier("fileOperation")
    private FileProcess fileProcess;

    @Autowired
    @Qualifier("pool")
    private MyConnectionPool pool;

    @Autowired
    @Qualifier("addGroupMemberCont")
    private AddGroupMemberController addGroupMemberController;

    @Autowired
    private GroupMembers groupMembers;


    @Override
    public boolean createGroup(Groups groups, String username){
        if (groups == null || groups.getIdGroup() == null || groups.getGroupPrivacy() == null ){
            throw new RuntimeException("something null at AddGroupControllerImpl.class.");
        }
        int result = this.createGroupsDAO.insertGroupDetail(groups);
        if (result == -404) {
            throw new IllegalArgumentException("SORRY! ,but id group is already exist, please try again.");
        }
        result += uploadDefaultImg(groups);

        result += addMasterMember(groups, username);

        return result > 2;
    }

    @Override
    public int addMasterMember(Groups groups, String username){
        this.groupMembers.getGroup().setIdGroup(groups.getIdGroup());
        this.groupMembers.getFriendMember().setUsername(username);
        this.groupMembers.setGroupMemberType(GroupMemberType.MASTER.getMemberType());
        this.groupMembers.setFriendMemberSince(NowDate.getNowTimestamp());

        return this.addGroupMemberController.addMember(this.groupMembers);
    }

    @Override
    public int uploadDefaultImg(Groups groups) {
        int result = 0;
        byte[] bytes = null;
        try {
            //TODO change path to your path
            bytes = this.fileProcess.readAPicture("C:\\Users\\Ryzen 5\\Documents\\IdeaProjects\\Chatting_System-Controller\\myImg\\group_Icon.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (bytes != null) {
            java.sql.Blob blob = this.pool.initBlob();
            try {
                blob.setBytes(1, bytes);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            groups.setPicture(blob);
            groups.setPictureName("group_Icon.png");
            result = this.insertGroupPostDAO.insertGroupPicture(groups);
        }
        return result;
    }

    @Override
    public java.sql.Blob GetDefaultGroupImg() {
        byte[] bytes = null;
        java.sql.Blob blob = null;
        try {
            //TODO change path to your path
            bytes = this.fileProcess.readAPicture("C:\\Users\\Ryzen 5\\Documents\\IdeaProjects\\Chatting_System-Controller\\myImg\\group_Icon.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (bytes != null) {
            blob = this.pool.initBlob();
            try {
                blob.setBytes(1, bytes);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return blob;
    }

}
