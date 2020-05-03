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
import org.springframework.context.ApplicationContext;
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
@ComponentScan(basePackages = {"com.louay.projects.model", "com.louay.projects.controller"})
@Scope("prototype")
public class GetGroupPostControllerImpl implements GetGroupPostController {
    @Autowired
    @Qualifier("groupDAO")
    private SelectGroupDAO selectGroupDAO;

    @Autowired
    @Qualifier("buildAnnotationContextControl")
    private ApplicationContext context;

    public TreeSet<Post> getGroupPost(Groups groups) {
        if (groups == null || groups.getIdGroup() == null) {
            throw new RuntimeException("something null at GetGroupPostControllerImpl.class.getGroupPost.");
        }

        Set<? extends Post> textPostSet = buildGroupTextPostSet(buildGroupTextPost(groups));

        Set<? extends Post> imgPostSet = buildGroupImgPostSet(buildGroupImgPost(groups));

        TreeSet<Post> postTreeSet = new TreeSet<>();

        if (!textPostSet.isEmpty()) {
            postTreeSet.addAll(textPostSet);
        }

        if (!imgPostSet.isEmpty()) {
            postTreeSet.addAll(imgPostSet);
        }

        if (postTreeSet.isEmpty()) {
            AddGroupController addGroupCont = (AddGroupController) this.context.getBean("addGroupCont");
            GroupTextPost groupTextPost = this.context.getBean(GroupTextPost.class);

            groupTextPost.getUser().setFirstName("System");
            groupTextPost.getUser().setPicture(addGroupCont.GetDefaultGroupImg());
            groupTextPost.setPost("There is no post here !.");
            groupTextPost.setDatePost(NowDate.getNowTimestamp());
            postTreeSet.add(groupTextPost);
        }

        return postTreeSet;
    }

    @Override
    public TreeSet<Post> getGroupImgPost(GroupImgPost groupImgPost) {
        if (groupImgPost == null || groupImgPost.getGroups().getIdGroup() == null) {
            throw new RuntimeException("something null at GetGroupPostControllerImpl.class.getGroupImgPost.");
        }

        Set<? extends Post> imgPostSet = (Set<? extends Post>) this.selectGroupDAO.findGroupImgPostByIdGroup(groupImgPost);

        TreeSet<Post> postTreeSet = new TreeSet<>();

        if (!imgPostSet.isEmpty()) {
            postTreeSet.addAll(imgPostSet);
        }

        return postTreeSet;
    }

    private Set<GroupTextPost> buildGroupTextPostSet(GroupTextPost groupTextPost) {
        return (Set<GroupTextPost>) this.selectGroupDAO.findGroupTextPostAndUserInfoByIdGroup(groupTextPost);
    }

    private Set<GroupImgPost> buildGroupImgPostSet(GroupImgPost groupImgPost) {
        return (Set<GroupImgPost>) this.selectGroupDAO.findGroupImgPostAndUserInfoByIdGroup(groupImgPost);
    }

    private GroupTextPost buildGroupTextPost(Groups groups) {
        GroupTextPost groupTextPost = this.context.getBean(GroupTextPost.class);
        groupTextPost.getGroups().setIdGroup(groups.getIdGroup());

        return groupTextPost;
    }

    private GroupImgPost buildGroupImgPost(Groups groups) {
        GroupImgPost groupImgPost = this.context.getBean(GroupImgPost.class);
        groupImgPost.getGroups().setIdGroup(groups.getIdGroup());

        return groupImgPost;
    }

}
