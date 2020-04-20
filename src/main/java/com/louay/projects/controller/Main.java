package com.louay.projects.controller;


import com.louay.projects.controller.service.message.AddMessageUserController;
import com.louay.projects.model.chains.accounts.Client;
import com.louay.projects.model.chains.accounts.Users;
import com.louay.projects.model.chains.accounts.constant.UserGender;
import com.louay.projects.model.chains.accounts.constant.UserType;
import com.louay.projects.model.chains.communications.account.AccountMessage;
import com.louay.projects.model.chains.member.account.UserFriend;
import com.louay.projects.model.dao.SelectGroupDAO;
import com.louay.projects.model.dao.SelectUsersDAO;
import com.louay.projects.model.util.date.NowDate;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = { "com.louay.projects.controller"})
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac =new AnnotationConfigApplicationContext();
        ac.scan("com.louay.projects.model","com.louay.projects.controller");
        ac.refresh();

        Client user = ac.getBean(Client.class);
        user.setUsername("louay");
        user.setPassword("12345678");
        user.setAccountPermission(UserType.CLIENT.getType());
        user.setDateCreate(NowDate.getNowTimestamp());

        user.setFirstName("louay");
        user.setLastName("amr");
        user.setBirthday(java.sql.Date.valueOf("1994-10-08"));
        user.setAge(user.getAgeFromBirthday());
        user.setGender(UserGender.MALE.getGender());
        user.setTelephone("00962");
        user.setEmail("louay@projects");
        user.setCountry("jordan");
        user.setState("az");
        user.setAddress("qatar street");

        UserFriend friend = ac.getBean(UserFriend.class);
        Users users = friend.getUser();
        Users friendMember = friend.getFriendMember();
        users.setUsername("louay");
        friendMember.setUsername("louay1");
        friend.setFriendMemberSince(NowDate.getNowTimestamp());

        AccountMessage accountMessage = ac.getBean(AccountMessage.class);
        Users sourceUser = accountMessage.getSourceUser();
        sourceUser.setUsername("louay");
        Users targetUser = accountMessage.getTargetUser();
        targetUser.setUsername("louay1");
        accountMessage.setMessageStringBuilder(new StringBuilder("welcome"));
        accountMessage.setSeen(false);
        accountMessage.setSentDate(NowDate.getNowTimestamp());

        SelectUsersDAO selectUsersDAO = (SelectUsersDAO) ac.getBean("usersDAO");
        System.out.println(selectUsersDAO.findUserMessageAndNumNotSeenBySender(accountMessage).iterator().next().toString());


    }
}
