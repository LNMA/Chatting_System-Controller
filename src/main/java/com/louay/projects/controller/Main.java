package com.louay.projects.controller;


import com.louay.projects.model.chains.member.group.GroupMembers;
import com.louay.projects.model.dao.DeleteGroupDAO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = { "com.louay.projects.controller"})
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac =new AnnotationConfigApplicationContext();
        ac.scan("com.louay.projects.model","com.louay.projects.controller");
        ac.refresh();

        GroupMembers invite = ac.getBean(GroupMembers.class);
        invite.getGroup().setIdGroup("group1");
        invite.getFriendMember().setUsername("louay1");


        DeleteGroupDAO selectUsersDAO = (DeleteGroupDAO) ac.getBean("groupDAO");
        selectUsersDAO.deleteGroupMemberByIdGroupAndUsername(invite);

    }
}
