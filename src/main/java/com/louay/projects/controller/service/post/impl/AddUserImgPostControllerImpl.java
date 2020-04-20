package com.louay.projects.controller.service.post.impl;

import com.louay.projects.controller.service.post.AddUserImgPostController;
import com.louay.projects.model.chains.accounts.Users;
import com.louay.projects.model.chains.communications.account.AccountImgPost;
import com.louay.projects.model.dao.InsertUserPostDAO;
import com.louay.projects.model.util.date.NowDate;
import com.louay.projects.model.util.pool.MyConnectionPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.sql.SQLException;

@Controller
@Configuration
@Component("addUserImgPost")
@ComponentScan(basePackages = { "com.louay.projects.model"})
@Scope("prototype")
public class AddUserImgPostControllerImpl implements AddUserImgPostController {

    @Autowired
    @Qualifier("usersDAO")
    private InsertUserPostDAO insertUserPostDAO;

    @Autowired
    @Qualifier("pool")
    private MyConnectionPool pool;

    @Autowired
    private AccountImgPost accountImgPost;

    @Override
    public Long addImgPost(String username, String fileName, byte[] bytes) {
        Long result;
        if (username == null || fileName == null || bytes == null){
            throw new RuntimeException("Something wrong while upload file.");
        }
        Users users = this.accountImgPost.getUser();
        users.setUsername(username);
        this.accountImgPost.setFileName(fileName);

        java.sql.Blob blob = null;
        try {
            blob = this.pool.initBlob();
            blob.setBytes(1, bytes);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        this.accountImgPost.setImage(blob);
        this.accountImgPost.setDatePost(NowDate.getNowTimestamp());

        result = this.insertUserPostDAO.insertAccountImgPost(this.accountImgPost);

        return result;
    }
}
