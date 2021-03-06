package com.louay.projects.controller.service.register.impl;

import com.louay.projects.controller.service.register.SignUpClientController;
import com.louay.projects.model.chains.accounts.Users;
import com.louay.projects.model.dao.CreateUsersDAO;
import com.louay.projects.model.dao.InsertUserPostDAO;
import com.louay.projects.model.util.file.FileProcess;
import com.louay.projects.model.util.pool.MyConnectionPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.sql.SQLException;


@Controller
@Configuration
@Component("signUpControl")
@ComponentScan(basePackages = {"com.louay.projects.model"})
@Scope("prototype")
public class SignUpClientControllerImpl implements SignUpClientController {

    @Autowired
    @Qualifier("usersDAO")
    private CreateUsersDAO createUsersDAO;

    @Autowired
    @Qualifier("usersDAO")
    private InsertUserPostDAO insertUserPostDAO;

    @Autowired
    @Qualifier("pool")
    private MyConnectionPool pool;

    @Autowired
    @Qualifier("fileOperation")
    private FileProcess fileProcess;


    @Override
    public boolean execute(Users users) {
        if (users == null || users.getUsername() == null || users.getPassword() == null || users.getAccountPermission() == null ||
                users.getUsername().length() < 4 || users.getPassword().length() < 7) {
            throw new RuntimeException("Username and password and account permission at least must be exist.");
        }
        int accountResult = this.createUsersDAO.insertUser(users);
        if (accountResult == -404) {
            throw new IllegalArgumentException("SORRY! ,but username is already exist, please try again.");
        }
        int detailResult = this.createUsersDAO.insertClientDetail(users);
        int uploadImgResult = this.uploadDefaultImg(users);
        return accountResult + detailResult + uploadImgResult > 2;

    }

    @Override
    public int uploadDefaultImg(Users users) {
        int result = 0;
        byte[] bytes = null;
        try {
            //TODO change path to your path
            bytes = this.fileProcess.readAPicture("C:\\Users\\Ryzen 5\\Documents\\IdeaProjects\\Chatting_System-Controller\\myImg\\person_black.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (bytes != null) {
            java.sql.Blob blob = this.pool.initBlob();
            try {
                blob.setBytes(1, bytes);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            users.setPicture(blob);
            users.setPictureName("baseline_person_black_48dp.png");
            result = this.insertUserPostDAO.insertAccountPicture(users);
        }
        return result;
    }

    @Override
    public java.sql.Blob GetDefaultUserImg() {
        byte[] bytes = null;
        java.sql.Blob blob = null;
        try {
            //TODO change path to your path
            bytes = this.fileProcess.readAPicture("C:\\Users\\Ryzen 5\\Documents\\IdeaProjects\\Chatting_System-Controller\\myImg\\person_black.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (bytes != null) {
            blob = this.pool.initBlob();
            try {
                blob.setBytes(1, bytes);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return blob;
    }
}
