package com.louay.projects.controller.service.client.impl;

import com.louay.projects.controller.service.client.EditUserImgPostController;
import com.louay.projects.model.chains.communications.Post;
import com.louay.projects.model.chains.communications.account.AccountImgPost;
import com.louay.projects.model.chains.communications.constant.PostClassName;
import com.louay.projects.model.chains.communications.group.GroupImgPost;
import com.louay.projects.model.dao.SelectGroupDAO;
import com.louay.projects.model.dao.SelectUsersDAO;
import com.louay.projects.model.dao.UpdateGroupPostDAO;
import com.louay.projects.model.dao.UpdateUserPostDAO;
import com.louay.projects.model.util.pool.MyConnectionPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.sql.SQLException;
import java.util.Set;

@Controller
@Configuration
@Component("editUserImgPost")
@ComponentScan(basePackages = {"com.louay.projects.model"})
@Scope("prototype")
public class EditUserImgPostControllerImpl implements EditUserImgPostController {
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

    @Autowired
    @Qualifier("pool")
    private MyConnectionPool pool;

    @Override
    public int editImgPost(PostClassName postClassName, Post post, String filename, byte [] bytes){
        int result;

        if (post == null || postClassName == null ){
            throw new RuntimeException("post or postClassName is null.");

        }else if (postClassName == PostClassName.ACCOUNT_IMG_POST){
            result = editAccountImgPost(post, filename, bytes);

        }else if (postClassName == PostClassName.GROUP_IMG_POST){
            result = editGroupImgPost(post, filename, bytes);
        }else {
            throw new UnsupportedOperationException("only edit img post.");
        }
        return result;
    }

    private int editAccountImgPost(Post post, String filename, byte[] bytes){
        Set<AccountImgPost> accountImgPostSet = (Set<AccountImgPost>) this.selectUsersDAO.findUserImgPostByIdPost(post);

        AccountImgPost accountImgPost = accountImgPostSet.iterator().next();
        java.sql.Blob blob = this.pool.initBlob();
        try {
            blob.setBytes(1, bytes);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        accountImgPost.setImage(blob);
        accountImgPost.setFileName(filename);

        return this.updateUserPostDAO.updateAccountImgPostByIdPost(accountImgPost);
    }

    private int editGroupImgPost(Post post, String filename, byte[] bytes){
        Set<GroupImgPost> groupImgPostSet = (Set<GroupImgPost>) this.selectGroupDAO.findGroupImgPostByIdPost(post);

        GroupImgPost groupImgPost = groupImgPostSet.iterator().next();
        java.sql.Blob blob = this.pool.initBlob();
        try {
            blob.setBytes(1, bytes);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        groupImgPost.setImage(blob);
        groupImgPost.setFileName(filename);

        return this.updateGroupPostDAO.updateGroupImgPostByIdPost(groupImgPost);
    }
}
