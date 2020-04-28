package com.louay.projects.controller.service.profile.impl;

import com.louay.projects.controller.service.profile.GroupProfileController;
import com.louay.projects.model.chains.accounts.group.Groups;
import com.louay.projects.model.dao.*;
import com.louay.projects.model.util.date.NowDate;
import com.louay.projects.model.util.pool.MyConnectionPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.sql.SQLException;
import java.util.Set;

@Controller
@Configuration
@Component("groupProfileInfoCont")
@ComponentScan(basePackages = {"com.louay.projects.model"})
@Scope("prototype")
public class GroupProfileControllerImpl implements GroupProfileController {
    @Autowired
    @Qualifier("groupDAO")
    private SelectGroupDAO selectGroupDAO;
    @Autowired
    @Qualifier("groupDAO")
    private UpdateGroupDAO updateGroupDAO;
    @Autowired
    @Qualifier("groupDAO")
    private UpdateGroupPostDAO updateGroupPostDAO;
    @Autowired
    @Qualifier("buildAnnotationContextControl")
    private ApplicationContext context;

    @Override
    public Set<Groups> getGroupInfo(Groups groups){
        if (groups == null || groups.getIdGroup() == null){
            throw new RuntimeException("Something null at GroupProfileControllerImpl.class.getGroupInfo.method.");
        }
        return (Set<Groups>) this.selectGroupDAO.findGroupDetailByIdGroup(groups);
    }

    @Override
    public int updateGroupProfile(Groups groups, String fieldCode) {
        if (groups == null || groups.getIdGroup() == null || fieldCode == null){
            throw new RuntimeException("Something null at GroupProfileControllerImpl.class.updateGroupProfile.method.");
        }

        switch (fieldCode){
            case "pGroup0img1c":
                return this.updateGroupPostDAO.updateGroupPictureByIdGroup(groups);

            case "pGroup1privacy2c":
                Groups groupsPrivacy = this.selectGroupDAO.findGroupNoPicByIdGroup(groups).iterator().next();
                groupsPrivacy.setGroupPrivacy(groups.getGroupPrivacy());
                return this.updateGroupDAO.updateGroupDetailByIdGroup(groupsPrivacy);

            case "pGroup2Activity3c":
                Groups groupsActivity = this.selectGroupDAO.findGroupNoPicByIdGroup(groups).iterator().next();
                groupsActivity.setGroupActivity(groups.getGroupActivity());
                return this.updateGroupDAO.updateGroupDetailByIdGroup(groupsActivity);

            default: throw new UnsupportedOperationException("unsupported profile update.");
        }
    }

    @Override
    public int auxUpdateGroupImg(String idGroup, String fileName, byte[] bytes, String field){
        if (idGroup == null || fileName == null || bytes == null || field == null){
            throw new RuntimeException("Something wrong while upload file.");
        }
        Groups groups = this.context.getBean(Groups.class);
        groups.setIdGroup(idGroup);
        groups.setPictureName(fileName);

        MyConnectionPool pool = (MyConnectionPool) this.context.getBean("pool");
        java.sql.Blob blob = null;

        try{
            blob = pool.initBlob();
            blob.setBytes(1,bytes);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        groups.setPicture(blob);
        groups.setDateCreate(NowDate.getNowTimestamp());

        return this.updateGroupProfile(groups, field);
    }
}
