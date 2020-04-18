package com.louay.projects.controller.service.client.impl;

import com.louay.projects.controller.service.client.EditUserTextPostController;
import com.louay.projects.model.chains.communications.Post;
import com.louay.projects.model.chains.communications.account.AccountTextPost;
import com.louay.projects.model.chains.communications.constant.PostClassName;
import com.louay.projects.model.chains.communications.group.GroupTextPost;
import com.louay.projects.model.dao.SelectGroupDAO;
import com.louay.projects.model.dao.SelectUsersDAO;
import com.louay.projects.model.dao.UpdateGroupPostDAO;
import com.louay.projects.model.dao.UpdateUserPostDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.Set;

@Controller
@Configuration
@Component("editUserTextPost")
@ComponentScan(basePackages = {"com.louay.projects.model"})
@Scope("prototype")
public class EditUserTextTextPostControllerImpl implements EditUserTextPostController {

    @Autowired
    @Qualifier("usersDAO")
    private SelectUsersDAO selectUsersDAO;

    @Autowired
    @Qualifier("groupDAO")
    private SelectGroupDAO selectGroupDAO;

    @Autowired
    @Qualifier("usersDAO")
    private UpdateUserPostDAO updateUserPostDAO;

    @Autowired
    @Qualifier("groupDAO")
    private UpdateGroupPostDAO updateGroupPostDAO;

    @Override
    public int editTextPost(PostClassName postClassName, Post post) {
        int result;

        if (post == null || postClassName == null) {
            throw new RuntimeException("post or postClassName is null.");

        } else if (postClassName == PostClassName.ACCOUNT_TEX_POST) {
            result = editAccountTextPost(post);

        } else if (postClassName == PostClassName.GROUP_TEXT_POST) {
            result = editGroupTextPost(post);

        } else {
            throw new UnsupportedOperationException("only edit text post.");

        }
        return result;
    }

    private int editAccountTextPost(Post post) {
        Set<AccountTextPost> accountTextPostSet = (Set<AccountTextPost>) this.selectUsersDAO.findUserTextPostByIdPost(post);

        AccountTextPost accountTextPost = accountTextPostSet.iterator().next();
        accountTextPost.setEditPost(((AccountTextPost) post).getPost());

        return this.updateUserPostDAO.updateAccountTextPostByIdPost(accountTextPost);
    }

    private int editGroupTextPost(Post post) {
        Set<GroupTextPost> groupTextPostSet = (Set<GroupTextPost>) this.selectGroupDAO.findGroupTextPostByIdPost(post);

        GroupTextPost groupTextPost = groupTextPostSet.iterator().next();
        groupTextPost.setEditPost(((GroupTextPost) post).getPost());

        return this.updateGroupPostDAO.updateGroupTextPostByIdPost(groupTextPost);
    }

}
