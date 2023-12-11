package com.example.webdavtest.webdav;

import org.apache.jackrabbit.webdav.simple.SimpleWebdavServlet;
import org.slf4j.Logger;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

public class WebDavServlet extends SimpleWebdavServlet {

    @Override
    public javax.jcr.Repository getRepository() {
        System.out.println("Starting WebDavServlet!");
        try {
            return RepositoryProvider.getRepository(
                new ClassPathResource("webDavFileStorage/repository.xml").getFile().getAbsolutePath(),
                new ClassPathResource("webDavFileStorage").getFile().getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Starting WebDavServlet FAILED!");
            throw new RuntimeException(e);
        }
    }

}
