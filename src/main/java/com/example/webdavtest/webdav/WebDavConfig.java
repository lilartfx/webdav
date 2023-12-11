package com.example.webdavtest.webdav;

import org.apache.jackrabbit.webdav.simple.SimpleWebdavServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

@Configuration
public class WebDavConfig {

    /*Using ClassPathResource to access the webDavFileStorage directory will work when running your application in an IDE or unpackaged form. However, when your application is packaged into a JAR, accessing file paths directly from the classpath can lead to issues, as the packaged JAR may not be able to write to these directories.*/

    @Bean
    public ServletRegistrationBean<SimpleWebdavServlet> webDavServlet() throws IOException {
        System.out.println("Registering WebDavServlet!");
        ServletRegistrationBean<SimpleWebdavServlet> registration = new ServletRegistrationBean<>(new WebDavServlet());
        String fileStoragePath = new ClassPathResource("webDavFileStorage").getFile().getAbsolutePath();
        String mimeConfigPath = new ClassPathResource("mime.xml").getFile().getAbsolutePath();
        registration.addUrlMappings("/webdav/*");
        registration.addInitParameter("resource-path-prefix", "/webdav");
        registration.addInitParameter("rootpath", fileStoragePath);
        //registration.addInitParameter("mime-info", mimeConfigPath); <-- tried to give own mime type config, idk why it throws an error, uncomment to see the error

        return registration;
    }
}
