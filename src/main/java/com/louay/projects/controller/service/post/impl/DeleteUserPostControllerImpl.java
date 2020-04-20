package com.louay.projects.controller.service.post.impl;

import com.louay.projects.controller.service.post.DeleteUserPostController;
import com.louay.projects.model.chains.communications.Post;
import com.louay.projects.model.chains.communications.constant.PostClassName;
import com.louay.projects.model.dao.DeleteUserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;


@Controller
@Configuration
@Component("DeleteUserPost")
@ComponentScan(basePackages = {"com.louay.projects.model"})
@Scope("prototype")
public class DeleteUserPostControllerImpl implements DeleteUserPostController {

    @Autowired
    @Qualifier("usersDAO")
    private DeleteUserDAO deleteUserDAO;

    @Override
    public int deletePost(Post post, PostClassName postClassName) {
        int result;
        if (post == null || postClassName == null) {
            throw new RuntimeException("Post class or PostClassName null.");

        } else if (postClassName == PostClassName.ACCOUNT_TEX_POST) {
            result = this.deleteUserDAO.deleteAccountTextPostByIdPost(post);

        } else if (postClassName == PostClassName.ACCOUNT_IMG_POST) {
            result = this.deleteUserDAO.deleteAccountImgPostByIdPost(post);

        } else {
            throw new RuntimeException("Some thing wrong occurred at delete post process.");

        }

        return result;
    }

}
