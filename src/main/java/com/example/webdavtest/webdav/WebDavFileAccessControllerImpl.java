package com.example.webdavtest.webdav;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

import java.net.MalformedURLException;
import java.net.URL;

@RestController
public class WebDavFileAccessControllerImpl implements WebDavFileAccessController {

    @Autowired
    JackrabbitService jackrabbitService;

    public static String generateWebDavUrl(HttpServletRequest request, String webDavPath) {
        try {

            URL requestUrl = new URL(request.getRequestURL().toString());
            String host = requestUrl.getHost();
            int port = requestUrl.getPort();
            String contextPath = request.getContextPath();

            // Construct the base URL
            String baseUrl = String.format("%s://%s:%d%s", requestUrl.getProtocol(), host, port, contextPath);

            // Append the WebDAV path
            return baseUrl + webDavPath;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public RedirectView getFile(Long fileId, HttpServletRequest request) {

        try {

            jackrabbitService.addFileToRepository("C:/Users/gably/IdeaProjects/webdavTest/src/main/java/com/example/webdavtest/webdav/test.txt", "test.txt");

            String fileName = "test.txt";
            String webDavPath = "/webdav/default/" + fileName;
            String webDavUrl = generateWebDavUrl(request, webDavPath);

            return new RedirectView(webDavUrl);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
