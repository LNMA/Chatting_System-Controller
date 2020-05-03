package com.louay.projects.controller.service.profile.impl;

import com.louay.projects.controller.service.profile.UserProfileController;
import com.louay.projects.model.chains.accounts.Client;
import com.louay.projects.model.chains.accounts.Users;
import com.louay.projects.model.dao.SelectUsersDAO;
import com.louay.projects.model.dao.UpdateUserPostDAO;
import com.louay.projects.model.dao.UpdateUsersDAO;
import com.louay.projects.model.util.date.NowDate;
import com.louay.projects.model.util.pool.MyConnectionPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.sql.SQLException;
import java.util.Set;

@Controller
@Configuration
@Component("userProfileInfoCont")
@ComponentScan(basePackages = {"com.louay.projects.model"})
@Scope("prototype")
public class UserProfileControllerImpl implements UserProfileController {
    @Autowired
    @Qualifier("usersDAO")
    private SelectUsersDAO selectUsersDAO;
    @Autowired
    @Qualifier("usersDAO")
    private UpdateUsersDAO updateUsersDAO;
    @Autowired
    @Qualifier("usersDAO")
    private UpdateUserPostDAO updateUserPostDAO;
    @Autowired
    @Qualifier("buildAnnotationContextControl")
    private ApplicationContext context;

    @Override
    public Set<Users> getUserInfo(Users users) {
        if (users == null || users.getUsername() == null){
            throw new RuntimeException("Something null at GetUserInfoControllerImpl.class.getUserInfo.method.");
        }
        return (Set<Users>) this.selectUsersDAO.findClientAllInfoByUsername(users);
    }

    @Override
    public int updateUserProfile(Users users, String fieldCode) {
        if (users == null || users.getUsername() == null || fieldCode == null){
            throw new RuntimeException("Something null at GetUserInfoControllerImpl.class.updateUserProfile.method.");
        }
        Client client;
        if (users instanceof  Client){
            client = (Client) users;
        }else {
            throw new IllegalArgumentException("users must instance of client.");
        }
        switch (fieldCode){
            case "p0img1c":
                return this.updateUserPostDAO.updateAccountPictureByUsername(client);

            case "p1pass2c":
                return 0; //disable replace with auxUpdateUserPassword method.

            case "p2fName3c":
                Client usersFirstName = (Client) this.selectUsersDAO.findClientInAccountDetailByUsername(client).iterator().next();
                usersFirstName.setFirstName(client.getFirstName());
                return this.updateUsersDAO.updateClientDetailByUsername(usersFirstName);

            case "p2lName4c":
                Client usersLastName = (Client) this.selectUsersDAO.findClientInAccountDetailByUsername(client).iterator().next();
                usersLastName.setLastName(client.getLastName());
                return this.updateUsersDAO.updateClientDetailByUsername(usersLastName);

            case "p2gender5c":
                Client usersGender = (Client) this.selectUsersDAO.findClientInAccountDetailByUsername(client).iterator().next();
                usersGender.setGender(client.getGender());
                return this.updateUsersDAO.updateClientDetailByUsername(usersGender);

            case "p2birth6c":
                Client usersBirthday = (Client) this.selectUsersDAO.findClientInAccountDetailByUsername(client).iterator().next();
                usersBirthday.setBirthday(client.getBirthday());
                return this.updateUsersDAO.updateClientDetailByUsername(usersBirthday);

            case "p2telephone7c":
                Client usersTelephone = (Client) this.selectUsersDAO.findClientInAccountDetailByUsername(client).iterator().next();
                usersTelephone.setTelephone(client.getTelephone());
                return this.updateUsersDAO.updateClientDetailByUsername(usersTelephone);

            case "p2email8c":
                Client usersEmail = (Client) this.selectUsersDAO.findClientInAccountDetailByUsername(client).iterator().next();
                usersEmail.setEmail(client.getEmail());
                return this.updateUsersDAO.updateClientDetailByUsername(usersEmail);

            case "p2allAddress9c":
                Client usersAllAddress = (Client) this.selectUsersDAO.findClientInAccountDetailByUsername(client).iterator().next();
                usersAllAddress.setCountry(client.getCountry());
                usersAllAddress.setState(client.getState());
                usersAllAddress.setAddress(client.getAddress());
                return this.updateUsersDAO.updateClientDetailByUsername(usersAllAddress);

            default: throw new UnsupportedOperationException("unsupported profile update.");
        }
    }

    @Override
    public int auxUpdateUserPassword(Client client, String oldPassword, String newPassword, String reNewPassword, String fieldCode){
        if (client == null || client.getUsername() == null || fieldCode == null || oldPassword == null ||
                newPassword == null || reNewPassword == null){
            throw new RuntimeException("Something null at GetUserInfoControllerImpl.class.auxUpdateUserPassword.method.");
        }
        Users usersPassword = this.selectUsersDAO.findUserInAccountByUsername(client).iterator().next();

        if (!oldPassword.equals(usersPassword.getPassword())){
            throw new IllegalArgumentException("old password did not match.");
        }
        if (!newPassword.equals(reNewPassword)){
            throw new IllegalArgumentException("new password did not match.");
        }

        usersPassword.setPassword(newPassword);

        return this.updateUsersDAO.updateUserByUsername(usersPassword);
    }

    @Override
    public int auxUpdateUserImg(String username, String fileName, byte[] bytes, String field){
        if (username == null || fileName == null || bytes == null || field == null){
            throw new RuntimeException("Something wrong while upload file.");
        }
        Client client = this.context.getBean(Client.class);
        client.setUsername(username);
        client.setPictureName(fileName);

        MyConnectionPool pool = (MyConnectionPool) this.context.getBean("pool");
        java.sql.Blob blob = null;

        try{
            blob = pool.initBlob();
            blob.setBytes(1,bytes);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        client.setPicture(blob);
        client.setDateCreate(NowDate.getNowTimestamp());

        return this.updateUserProfile(client, field);
    }
}
