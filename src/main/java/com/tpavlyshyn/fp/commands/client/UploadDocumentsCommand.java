package com.tpavlyshyn.fp.commands.client;

import com.tpavlyshyn.fp.commands.Command;
import com.tpavlyshyn.fp.commands.Path;
import com.tpavlyshyn.fp.commands.action.Dispatcher;
import com.tpavlyshyn.fp.commands.action.Forward;
import com.tpavlyshyn.fp.commands.action.Redirect;
import com.tpavlyshyn.fp.entity.user.User;
import com.tpavlyshyn.fp.exceptions.ServiceException;
import com.tpavlyshyn.fp.services.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import org.apache.log4j.Logger;

import java.io.*;

public class UploadDocumentsCommand implements Command {
    private final static Logger log = Logger.getLogger(UploadDocumentsCommand.class);

    private final UserService userService;

    public UploadDocumentsCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Dispatcher execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int userId = user.getId();

        try {

            Part part = request.getPart("file");
            String fileName = part.getSubmittedFileName();

            String path = Path.IMAGES_STORE + File.separator + fileName;

            InputStream is = part.getInputStream();
            boolean test = uploadFile(is, path);
            boolean result = userService.setDocuments(userId, path);
            if (test && result) {
                return new Redirect(request.getContextPath() + Path.PAGE__INDEX);
            }
        } catch (ServiceException | ServletException | IOException ex) {
            log.error(ex.getMessage(), ex);
            return new Redirect(Path.ERROR_PAGE);
        }
        return new Forward(Path.PAGE__INDEX);

    }

    private boolean uploadFile(InputStream is, String path) {
        boolean test = false;
        try {
            byte[] buf = new byte[1024];
            is.read();

            OutputStream outputStream = new FileOutputStream(path);

            int len;
            while ((len = is.read(buf)) > 0) {
                outputStream.write(buf, 0, len);
            }
            outputStream.close();
            is.close();

            test = true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return test;
    }
}

