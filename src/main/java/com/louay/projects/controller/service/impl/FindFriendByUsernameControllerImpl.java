package com.louay.projects.controller.service.impl;

import com.louay.projects.controller.service.FindFriendByUsernameController;
import com.louay.projects.model.chains.communications.AccountPicture;
import com.louay.projects.model.chains.users.Users;
import com.louay.projects.model.chains.util.PictureDirection;
import com.louay.projects.model.dao.SelectUsersDAO;
import com.louay.projects.model.util.file.FileProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

@Controller
@Configuration
@Component("findFriendByName")
@ComponentScan(basePackages = {"com.louay.projects.model"})
@Scope("prototype")
public class FindFriendByUsernameControllerImpl implements FindFriendByUsernameController {

    @Autowired
    @Qualifier("usersDAO")
    private SelectUsersDAO selectUsersDAO;

    @Autowired
    @Qualifier("friendUserImgList")
    private List<PictureDirection> list;

    @Autowired
    @Qualifier("buildAnnotationContextControl")
    private ApplicationContext context;

    @Autowired
    @Qualifier("fileOperation")
    private FileProcess fileProcess;

    String path = "C:\\Users\\Ryzen 5\\Documents\\IdeaProjects\\Chatting_System-View\\src\\main\\webapp\\client\\friendImag\\";//TODO Change to your path


    @Override
    public List<PictureDirection> execute(Users users) {
        if (users == null || users.getUsername() == null || "".equals(users.getUsername())) {
            throw new RuntimeException("Username must be exist to perform this operation.");
        }

        Set<AccountPicture> container = (Set<AccountPicture>) selectUsersDAO.findFriendAndPictureByUsername(users);
        prepareFiles(container);
        return this.list;
    }

    private void prepareFiles(Set<AccountPicture> container) {
        byte[] bytes;
        StringBuilder fullPath = new StringBuilder();
        java.sql.Blob blob;
        int size;

        try {
            for (AccountPicture n : container) {
                PictureDirection direction = this.context.getBean(PictureDirection.class);

                direction.setUsername(n.getUsername());
                fullPath.append(n.getPictureName());
                direction.setFileName(fullPath.toString());
                direction.setPath(this.path);
                this.list.add(direction);

                fullPath.insert(0, this.path);
                blob = n.getPicture();
                size = (int) blob.length();
                bytes = n.getPicture().getBytes(1, size);
                this.fileProcess.writePicture(fullPath.toString(), bytes);
                blob.free();
                fullPath.delete(0, fullPath.length());
            }
        } catch (SQLException | IOException e) {
            System.out.println(e);
        }

    }

    public boolean deleteImg(List<PictureDirection> directions){
        File file;
        boolean isDelete = false;
        for (PictureDirection p :directions) {
            file = new File(p.getPath()+""+p.getFileName());
            isDelete = file.delete();
        }
        return isDelete;
    }


}
