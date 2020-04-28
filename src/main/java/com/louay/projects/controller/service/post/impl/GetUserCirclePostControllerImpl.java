package com.louay.projects.controller.service.post.impl;

import com.louay.projects.controller.service.post.GetUserCirclePostController;
import com.louay.projects.controller.service.register.SignUpClientController;
import com.louay.projects.model.chains.accounts.Client;
import com.louay.projects.model.chains.accounts.Users;
import com.louay.projects.model.chains.communications.Post;
import com.louay.projects.model.chains.communications.account.AccountImgPost;
import com.louay.projects.model.chains.communications.account.AccountTextPost;
import com.louay.projects.model.chains.communications.group.GroupImgPost;
import com.louay.projects.model.chains.communications.group.GroupTextPost;
import com.louay.projects.model.dao.SelectGroupDAO;
import com.louay.projects.model.dao.SelectUsersDAO;
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
@Component("getUserCirclePost")
@ComponentScan(basePackages = {"com.louay.projects.model"})
@Scope("prototype")
public class GetUserCirclePostControllerImpl implements GetUserCirclePostController {

    @Autowired
    @Qualifier("usersDAO")
    private SelectUsersDAO selectUsersDAO;

    @Autowired
    @Qualifier("groupDAO")
    private SelectGroupDAO selectGroupDAO;

    @Autowired
    @Qualifier("buildAnnotationContextControl")
    private ApplicationContext context;

    @Override
    public TreeSet<Post> getUserCirclesPost(Users users) {
        if (users == null || users.getUsername() == null) {
            throw new RuntimeException("something null at GetUserCirclePostControllerImpl.class.getUserCirclesPost.");
        }
        Set<AccountTextPost> accountTextPostSet = buildAccountTextPostSet(buildAccountTextPost(users));

        Set<AccountImgPost> accountImgPostSet = buildAccountImgPostSet(buildAccountImgPost(users));

        Set<AccountTextPost> friendTextPostSet = buildFriendTextPostSet(buildAccountTextPost(users));

        Set<AccountImgPost> friendImgPostSet = buildFriendImgPostSet(buildAccountImgPost(users));

        Set<GroupTextPost> groupTextPostSet = buildGroupTextPostSet(buildGroupTextPost(users));

        Set<GroupImgPost> groupImgPostSet = buildGroupImgPostSet(buildGroupImgPost(users));

        Set<GroupTextPost> friendGroupTextPostSet = buildFriendGroupTextPostSet(buildGroupTextPost(users));

        Set<GroupImgPost> friendGroupImgPostSet = buildFriendGroupImgPostSet(buildGroupImgPost(users));


        TreeSet<Post> treePostSet = new TreeSet<>();

        if (!accountTextPostSet.isEmpty()) {
            treePostSet.addAll(accountTextPostSet);
        }

        if (!accountImgPostSet.isEmpty()) {
            treePostSet.addAll(accountImgPostSet);
        }

        if (!friendTextPostSet.isEmpty()) {
            treePostSet.addAll(friendTextPostSet);
        }

        if (!friendImgPostSet.isEmpty()) {
            treePostSet.addAll(friendImgPostSet);
        }

        if (!groupImgPostSet.isEmpty()) {
            treePostSet.addAll(groupImgPostSet);
        }

        if (!groupTextPostSet.isEmpty()) {
            treePostSet.addAll(groupTextPostSet);
        }

        if (!friendGroupTextPostSet.isEmpty()) {
            treePostSet.addAll(friendGroupTextPostSet);
        }

        if (!friendGroupImgPostSet.isEmpty()) {
            treePostSet.addAll(friendGroupImgPostSet);
        }

        if (treePostSet.isEmpty()) {
            SignUpClientController signUpControl = (SignUpClientController) this.context.getBean("signUpControl");
            AccountTextPost accountTextPost = this.context.getBean(AccountTextPost.class);
            Users usersSystem = accountTextPost.getUser();

            usersSystem.setUsername("System");
            usersSystem.setPicture(signUpControl.GetDefaultUserImg());
            accountTextPost.setDatePost(NowDate.getNowTimestamp());
            accountTextPost.setPost("There is no post here !.");
            treePostSet.add(accountTextPost);
        }

        return treePostSet;
    }

    @Override
    public TreeSet<Post> getUserPostOnly(Users users) {
        if (users == null || users.getUsername() == null) {
            throw new RuntimeException("something null at GetUserCirclePostControllerImpl.class.getUserPostOnly.");
        }
        Set<AccountTextPost> accountTextPostSet = buildAccountTextPostSet(buildAccountTextPost(users));

        Set<AccountImgPost> accountImgPostSet = buildAccountImgPostSet(buildAccountImgPost(users));

        TreeSet<Post> treePostSet = new TreeSet<>();

        if (!accountTextPostSet.isEmpty()) {
            treePostSet.addAll(accountTextPostSet);
        }

        if (!accountImgPostSet.isEmpty()) {
            treePostSet.addAll(accountImgPostSet);
        }

        if (treePostSet.isEmpty()) {
            SignUpClientController signUpControl = (SignUpClientController) this.context.getBean("signUpControl");
            AccountTextPost accountTextPost = this.context.getBean(AccountTextPost.class);
            Client usersSystem = accountTextPost.getUser();

            usersSystem.setFirstName("System");
            usersSystem.setPicture(signUpControl.GetDefaultUserImg());
            accountTextPost.setDatePost(NowDate.getNowTimestamp());
            accountTextPost.setPost("There is no post here !.");
            treePostSet.add(accountTextPost);
        }

        return treePostSet;
    }

    @Override
    public TreeSet<Post> getUserImgPost(Users users) {
        if (users == null || users.getUsername() == null) {
            throw new RuntimeException("something null at GetUserCirclePostControllerImpl.class.getUserImgPost.");
        }
        Set<AccountImgPost> imgPostSet =
                (Set<AccountImgPost>) this.selectUsersDAO.findUserImgPostByUsername(buildAccountImgPost(users));

        return new TreeSet<>(imgPostSet);
    }

    private Set<GroupTextPost> buildFriendGroupTextPostSet(GroupTextPost groupTextPost) {
        return (Set<GroupTextPost>) this.selectGroupDAO.findUserFiendGroupTextPostAndUserGroupInfoByUsername(groupTextPost);
    }

    private Set<GroupImgPost> buildFriendGroupImgPostSet(GroupImgPost groupImgPost) {
        return (Set<GroupImgPost>) this.selectGroupDAO.findUserFriendGroupImgPostAndUserGroupInfoByUsername(groupImgPost);
    }

    private Set<GroupTextPost> buildGroupTextPostSet(GroupTextPost groupTextPost) {
        return (Set<GroupTextPost>) this.selectGroupDAO.findGroupTextPostAndUserGroupInfoByUsername(groupTextPost);
    }

    private Set<GroupImgPost> buildGroupImgPostSet(GroupImgPost groupImgPost) {
        return (Set<GroupImgPost>) this.selectGroupDAO.findGroupImgPostAndUserGroupInfoByUsername(groupImgPost);
    }

    private Set<AccountImgPost> buildFriendImgPostSet(AccountImgPost accountImgPost) {
        return (Set<AccountImgPost>) this.selectUsersDAO.findUserFriendImgPostAndInfoByUsername(accountImgPost);
    }

    private Set<AccountTextPost> buildFriendTextPostSet(AccountTextPost accountTextPost) {
        return (Set<AccountTextPost>) this.selectUsersDAO.findUserFriendTextPostAndInfoByUsername(accountTextPost);
    }

    private Set<AccountImgPost> buildAccountImgPostSet(AccountImgPost accountImgPost) {
        return (Set<AccountImgPost>) this.selectUsersDAO.findUserImgPostAndInfoByUsername(accountImgPost);
    }

    private Set<AccountTextPost> buildAccountTextPostSet(AccountTextPost accountTextPost) {
        return (Set<AccountTextPost>) this.selectUsersDAO.findUserTextPostAndInfoByUsername(accountTextPost);
    }

    private AccountImgPost buildAccountImgPost(Users users) {
        AccountImgPost accountImgPost = this.context.getBean(AccountImgPost.class);
        Users usersImgPost = accountImgPost.getUser();
        usersImgPost.setUsername(users.getUsername());

        return accountImgPost;
    }

    private AccountTextPost buildAccountTextPost(Users users) {
        AccountTextPost accountTextPost = this.context.getBean(AccountTextPost.class);
        Users usersTxtPost = accountTextPost.getUser();
        usersTxtPost.setUsername(users.getUsername());

        return accountTextPost;
    }

    private GroupImgPost buildGroupImgPost(Users users) {
        GroupImgPost groupImgPost = this.context.getBean(GroupImgPost.class);
        Users usersGroupImgPost = groupImgPost.getUser();
        usersGroupImgPost.setUsername(users.getUsername());

        return groupImgPost;
    }

    private GroupTextPost buildGroupTextPost(Users users) {
        GroupTextPost groupTextPost = this.context.getBean(GroupTextPost.class);
        Users usersGroupTextPost = groupTextPost.getUser();
        usersGroupTextPost.setUsername(users.getUsername());

        return groupTextPost;
    }
}
