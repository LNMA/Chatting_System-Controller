package com.louay.projects.controller.service.group.impl;

import com.louay.projects.controller.service.group.AddGroupPostController;
import com.louay.projects.model.chains.communications.group.GroupImgPost;
import com.louay.projects.model.chains.communications.group.GroupTextPost;
import com.louay.projects.model.dao.InsertGroupPostDAO;
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

@Controller
@Configuration
@Component("addGroupPostCont")
@ComponentScan(basePackages = { "com.louay.projects.model"})
@Scope("prototype")
public class AddGroupPostControllerImpl implements AddGroupPostController {

    @Autowired
    @Qualifier("groupDAO")
    private InsertGroupPostDAO insertGroupPostDAO;

    @Autowired
    @Qualifier("pool")
    private MyConnectionPool pool;

    @Autowired
    @Qualifier("buildAnnotationContextControl")
    private ApplicationContext context;

    @Override
    public Long addGroupImgPost(String username, String idGroup, String fileName, byte[] bytes) {
        if (username == null || idGroup == null || fileName == null || bytes == null){
            throw new RuntimeException("Something wrong at AddGroupPostControllerImpl.class.");
        }

        return this.insertGroupPostDAO.insertGroupImgPost(buildGroupImgPost(username, idGroup, fileName, bytes));
    }

    private GroupImgPost buildGroupImgPost(String username, String idGroup, String fileName, byte[] bytes){
        GroupImgPost groupImgPost = this.context.getBean(GroupImgPost.class);
        groupImgPost.getUser().setUsername(username);
        groupImgPost.getGroups().setIdGroup(idGroup);

        java.sql.Blob blob = null;
        try {
            blob = this.pool.initBlob();
            blob.setBytes(1, bytes);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        groupImgPost.setImage(blob);
        groupImgPost.setFileName(fileName);
        groupImgPost.setDatePost(NowDate.getNowTimestamp());

        return groupImgPost;
    }

    @Override
    public Long addGroupTextPost(GroupTextPost groupTextPost){
        if (groupTextPost == null || groupTextPost.getUser().getUsername() == null || groupTextPost.getPost() == null ||
                groupTextPost.getGroups().getIdGroup() == null){
            throw new RuntimeException("Something wrong at AddGroupPostControllerImpl.class.");
        }
        return this.insertGroupPostDAO.insertGroupTextPost(groupTextPost);
    }
}
