package com.louay.projects.controller.service.post.impl;

import com.louay.projects.controller.service.post.GetUserEditPostController;
import com.louay.projects.model.chains.communications.Post;
import com.louay.projects.model.chains.communications.account.AccountImgPost;
import com.louay.projects.model.chains.communications.account.AccountTextPost;
import com.louay.projects.model.chains.communications.constant.PostClassName;
import com.louay.projects.model.chains.communications.group.GroupImgPost;
import com.louay.projects.model.chains.communications.group.GroupTextPost;
import com.louay.projects.model.dao.SelectGroupDAO;
import com.louay.projects.model.dao.SelectUsersDAO;
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
@Component("getUserEditPost")
@ComponentScan(basePackages = {"com.louay.projects.model"})
@Scope("prototype")
public class GetUserEditPostControllerImpl implements GetUserEditPostController {

    @Autowired
    @Qualifier("usersDAO")
    private SelectUsersDAO selectUsersDAO;

    @Autowired
    @Qualifier("groupDAO")
    private SelectGroupDAO selectGroupDAO;

    @Override
    public Set<? extends Post> getUserPost(Post post, PostClassName postClassName) {
        if (postClassName == PostClassName.ACCOUNT_TEX_POST) {
            return getUserTextPost(post);
        } else if (postClassName == PostClassName.ACCOUNT_IMG_POST) {
            return getUserImgPost(post);
        } else if (postClassName == PostClassName.GROUP_TEXT_POST) {
            return getGroupTextPost(post);
        } else if (postClassName == PostClassName.GROUP_IMG_POST) {
            return getGroupImgPost(post);
        } else {
            throw new UnsupportedOperationException("Only ACCOUNT_TEX_POST & ACCOUNT_IMG_POST & GROUP_TEXT_POST & GROUP_IMG_POST are supported.");
        }
    }

    private Set<AccountTextPost> getUserTextPost(Post post) {
        return (Set<AccountTextPost>) this.selectUsersDAO.findUserTextPostByIdPost(post);
    }

    private Set<AccountImgPost> getUserImgPost(Post post) {
        return (Set<AccountImgPost>) this.selectUsersDAO.findUserImgPostByIdPost(post);
    }

    private Set<GroupTextPost> getGroupTextPost(Post post) {
        return (Set<GroupTextPost>) this.selectGroupDAO.findGroupTextPostByIdPost(post);
    }

    private Set<GroupImgPost> getGroupImgPost(Post post) {
        return (Set<GroupImgPost>) this.selectGroupDAO.findGroupImgPostByIdPost(post);
    }
}
