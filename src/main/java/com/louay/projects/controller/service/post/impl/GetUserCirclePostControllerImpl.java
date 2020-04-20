package com.louay.projects.controller.service.post.impl;

import com.louay.projects.controller.service.post.GetUserCirclePostController;
import com.louay.projects.model.chains.accounts.Users;
import com.louay.projects.model.chains.communications.Post;
import com.louay.projects.model.chains.communications.account.AccountImgPost;
import com.louay.projects.model.chains.communications.account.AccountTextPost;
import com.louay.projects.model.chains.communications.group.GroupImgPost;
import com.louay.projects.model.chains.communications.group.GroupTextPost;
import com.louay.projects.model.dao.SelectGroupDAO;
import com.louay.projects.model.dao.SelectUsersDAO;
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
@ComponentScan(basePackages = { "com.louay.projects.model"})
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
    public TreeSet<Post> getUserCirclesPost(Users users){
        Set<AccountTextPost> accountTextPostSet = buildAccountTextPostSet(buildAccountTextPost(users));

        Set<AccountImgPost> accountImgPostSet = buildAccountImgPostSet(buildAccountImgPost(users));

        Set<AccountTextPost> friendTextPostSet = buildFriendTextPostSet(buildAccountTextPost(users));

        Set<AccountImgPost> friendImgPostSet = buildFriendImgPostSet(buildAccountImgPost(users));

        Set<GroupTextPost> groupTextPostSet = buildGroupTextPostSet(buildGroupTextPost(users));

        Set<GroupImgPost> groupImgPostSet = buildGroupImgPostSet(buildGroupImgPost(users));


        TreeSet<Post> treePostSet = new TreeSet<>();

        if (accountTextPostSet.size() > 0){
            treePostSet.addAll(accountTextPostSet);
        }

        if (accountImgPostSet.size() > 0){
            treePostSet.addAll(accountImgPostSet);
        }

        if (friendTextPostSet.size() > 0){
            treePostSet.addAll(friendTextPostSet);
        }

        if (friendImgPostSet.size() > 0){
            treePostSet.addAll(friendImgPostSet);
        }

        if (groupImgPostSet.size() > 0){
            treePostSet.addAll(groupImgPostSet);
        }

        if (groupTextPostSet.size() > 0){
            treePostSet.addAll(groupTextPostSet);
        }

        if (treePostSet.size() < 1){
            AccountTextPost accountTextPost = this.context.getBean(AccountTextPost.class);
            accountTextPost.setPost("There is no post here !.");
            Users usersSystem = accountTextPost.getUser();
            usersSystem.setUsername("System");
            treePostSet.add(accountTextPost);
        }

        return treePostSet;
    }

    private Set<GroupTextPost> buildGroupTextPostSet(GroupTextPost groupTextPost){
        return (Set<GroupTextPost>) this.selectGroupDAO.findGroupTextPostByIdGroup(groupTextPost);
    }

    private Set<GroupImgPost> buildGroupImgPostSet(GroupImgPost groupImgPost){
        return (Set<GroupImgPost>) this.selectGroupDAO.findGroupImgPostByUsername(groupImgPost);
    }

    private Set<AccountImgPost> buildFriendImgPostSet(AccountImgPost accountTextPost){
        return (Set<AccountImgPost>) this.selectUsersDAO.findUserFriendImgPostByUsername(accountTextPost);
    }

    private Set<AccountTextPost> buildFriendTextPostSet(AccountTextPost accountTextPost){
        return (Set<AccountTextPost>) this.selectUsersDAO.findUserFriendTextPostByUsername(accountTextPost);
    }

    private Set<AccountImgPost> buildAccountImgPostSet(AccountImgPost accountTextPost){
        return (Set<AccountImgPost>) this.selectUsersDAO.findUserImgPostByUsername(accountTextPost);
    }

    private Set<AccountTextPost> buildAccountTextPostSet(AccountTextPost accountTextPost){
        return (Set<AccountTextPost>) this.selectUsersDAO.findUserTextPostByUsername(accountTextPost);
    }

    private AccountImgPost buildAccountImgPost(Users users){
        AccountImgPost accountImgPost = this.context.getBean(AccountImgPost.class);
        Users usersImgPost = accountImgPost.getUser();
        usersImgPost.setUsername(users.getUsername());

        return accountImgPost;
    }

    private AccountTextPost buildAccountTextPost(Users users){
        AccountTextPost accountTextPost = this.context.getBean(AccountTextPost.class);
        Users usersTxtPost = accountTextPost.getUser();
        usersTxtPost.setUsername(users.getUsername());

        return accountTextPost;
    }

    private GroupImgPost buildGroupImgPost(Users users){
        GroupImgPost groupImgPost = this.context.getBean(GroupImgPost.class);
        Users usersGroupImgPost = groupImgPost.getUser();
        usersGroupImgPost.setUsername(users.getUsername());

        return groupImgPost;
    }

    private GroupTextPost buildGroupTextPost(Users users){
        GroupTextPost groupTextPost = this.context.getBean(GroupTextPost.class);
        Users usersGroupTextPost = groupTextPost.getUser();
        usersGroupTextPost.setUsername(users.getUsername());

        return groupTextPost;
    }
}
