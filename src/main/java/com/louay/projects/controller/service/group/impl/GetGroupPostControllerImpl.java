package com.louay.projects.controller.service.group.impl;

import com.louay.projects.controller.service.group.AddGroupController;
import com.louay.projects.controller.service.group.GetGroupPostController;
import com.louay.projects.model.chains.accounts.group.Groups;
import com.louay.projects.model.chains.communications.Post;
import com.louay.projects.model.chains.communications.group.GroupImgPost;
import com.louay.projects.model.chains.communications.group.GroupTextPost;
import com.louay.projects.model.dao.SelectGroupDAO;
import com.louay.projects.model.util.date.NowDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;


import java.util.Set;
import java.util.TreeSet;

@Controller
@Configuration
@Component("getGroupPostCont")
@ComponentScan(basePackages = { "com.louay.projects.model", "com.louay.projects.controller"})
@Scope("prototype")
public class GetGroupPostControllerImpl implements GetGroupPostController {
    @Autowired
    @Qualifier("groupDAO")
    private SelectGroupDAO selectGroupDAO;
    @Autowired
    private GroupTextPost groupTextPost;
    @Autowired
    private GroupImgPost groupImgPost;
    @Autowired
    @Qualifier("addGroupCont")
    private AddGroupController addGroupController;


    public TreeSet<Post> getGroupPost(Groups groups){
        if (groups == null || groups.getIdGroup() == null ){
            throw new RuntimeException("something null at GetGroupPostControllerImpl.class.getGroupPost.");
        }
        this.groupTextPost.setGroups(groups);

        Set<? extends Post> textPostSet = (Set<? extends Post>) this.selectGroupDAO.findGroupTextPostAndUserInfoByIdGroup(this.groupTextPost);

        this.groupImgPost.setGroups(groups);

        Set<? extends Post> imgPostSet = (Set<? extends Post>) this.selectGroupDAO.findGroupImgPostAndUserInfoByIdGroup(this.groupImgPost);

        TreeSet<Post> postTreeSet = new TreeSet<>();

        if (!textPostSet.isEmpty()){
            postTreeSet.addAll(textPostSet);
        }

        if (!imgPostSet.isEmpty()){
            postTreeSet.addAll(imgPostSet);
        }

        if (postTreeSet.isEmpty()){
            this.groupTextPost.getUser().setFirstName("System");
            this.groupTextPost.getUser().setPicture(this.addGroupController.GetDefaultGroupImg());
            this.groupTextPost.setPost("There is no post here !.");
            this.groupTextPost.setDatePost(NowDate.getNowTimestamp());
            postTreeSet.add(this.groupTextPost);
        }

        return postTreeSet;
    }

    @Override
    public TreeSet<Post> getGroupImgPost(GroupImgPost groupImgPost){
        if (groupImgPost == null || groupImgPost.getGroups().getIdGroup() == null ){
            throw new RuntimeException("something null at GetGroupPostControllerImpl.class.getGroupImgPost.");
        }

        this.groupImgPost.setGroups(groupImgPost.getGroups());

        Set<? extends Post> imgPostSet = (Set<? extends Post>) this.selectGroupDAO.findGroupImgPostByIdGroup(this.groupImgPost);

        TreeSet<Post> postTreeSet = new TreeSet<>();

        if (!imgPostSet.isEmpty()){
            postTreeSet.addAll(imgPostSet);
        }

        return postTreeSet;
    }


}
