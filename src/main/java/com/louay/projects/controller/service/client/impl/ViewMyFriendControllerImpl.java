package com.louay.projects.controller.service.client.impl;

import com.louay.projects.controller.service.client.ViewMyFriendController;
import com.louay.projects.model.chains.communications.AccountPicture;
import com.louay.projects.model.chains.users.Users;
import com.louay.projects.model.chains.util.PictureBase64;
import com.louay.projects.model.dao.SelectUsersDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.sql.SQLException;
import java.util.*;

@Controller
@Configuration
@Component("findFriendByName")
@ComponentScan(basePackages = {"com.louay.projects.model"})
@Scope("prototype")
public class ViewMyFriendControllerImpl implements ViewMyFriendController {

    @Autowired
    @Qualifier("usersDAO")
    private SelectUsersDAO selectUsersDAO;

    @Autowired
    @Qualifier("friendUserImgList")
    private List<PictureBase64> list;

    @Autowired
    @Qualifier("buildAnnotationContextControl")
    private ApplicationContext context;


    @Override
    public List<PictureBase64> execute(Users users) {
        if (users == null || users.getUsername() == null || "".equals(users.getUsername())) {
            throw new RuntimeException("Username must be exist to perform this operation.");
        }

        Set<AccountPicture> container = (Set<AccountPicture>) selectUsersDAO.findFriendAndPictureByUsername(users);

        int size ;
        for (AccountPicture p: container) {
            PictureBase64 pictureBase64 = this.context.getBean(PictureBase64.class);
            pictureBase64.setUsername(p.getUsername());
            try {
                size = (int)p.getPicture().length();
                pictureBase64.setPictureBase64(Base64.getEncoder().encodeToString(p.getPicture().getBytes(1,size)));
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            this.list.add(pictureBase64);
        }
        return this.list;
    }

}
