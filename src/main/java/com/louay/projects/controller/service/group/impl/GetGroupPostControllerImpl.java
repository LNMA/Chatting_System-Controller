package com.louay.projects.controller.service.group.impl;

import com.louay.projects.controller.service.group.GetGroupPostController;
import com.louay.projects.model.chains.accounts.group.Groups;
import com.louay.projects.model.dao.SelectGroupDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Controller
@Configuration
@Component("getGroupPostCont")
@ComponentScan(basePackages = { "com.louay.projects.model"})
@Scope("prototype")
public class GetGroupPostControllerImpl implements GetGroupPostController {
    @Autowired
    @Qualifier("groupDAO")
    SelectGroupDAO selectGroupDAO;

    public void getGroupPost(Groups groups){

    }
}
