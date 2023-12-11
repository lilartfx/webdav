package com.example.webdavtest.webdav;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.jcr.*;
import java.io.*;

@Service
public class JackrabbitService {

    public void addFileToRepository(String filePath, String nodeName) throws Exception {
        Repository repository = RepositoryProvider.getRepository(
                new ClassPathResource("webDavFileStorage/repository.xml").getFile().getAbsolutePath(),
                new ClassPathResource("webDavFileStorage").getFile().getAbsolutePath());

        Session session = null;
        try {
            session = repository.login(new SimpleCredentials("admin", "admin".toCharArray()), "default");

            Node rootNode = session.getRootNode();
            Node fileNode = rootNode.addNode(nodeName, "nt:file");
            Node contentNode = fileNode.addNode("jcr:content", "nt:resource");

            // Set the content type (e.g., "text/plain") and encoding if applicable
            contentNode.setProperty("jcr:mimeType", "text/plain");
            contentNode.setProperty("jcr:encoding", "UTF-8");

            // Set the binary data from the file
            Binary binary = session.getValueFactory().createBinary(new FileInputStream(filePath));
            contentNode.setProperty("jcr:data", binary);

            session.save();
            System.out.println("File added to repository successfully.");
        } finally {
            if (session != null) {
                session.logout();
            }
        }
    }

    /*public void removeNodes(String path) throws RepositoryException {
        Session session = null;
        try {
            session = repository.login(new SimpleCredentials("admin", "admin".toCharArray()));
            Node rootNode = session.getRootNode();
            Node nodeToRemove = rootNode.getNode(path);

            if (nodeToRemove != null) {
                nodeToRemove.remove();
                session.save();
                System.out.println("Nodes removed successfully.");
            } else {
                System.out.println("Node not found at path: " + path);
            }
        } finally {
            if (session != null) {
                session.logout();
            }
        }
    }*/
}
