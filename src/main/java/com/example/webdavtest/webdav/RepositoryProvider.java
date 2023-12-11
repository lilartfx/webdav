package com.example.webdavtest.webdav;

import org.apache.jackrabbit.core.TransientRepository;
import javax.jcr.Repository;
import java.io.File;

public class RepositoryProvider {

    private static Repository repository;

    public static Repository getRepository(String configFilePath, String repoHomePath) {
        if (repository == null) {
            // Path to the configuration file and repository home
            File configFile = new File(configFilePath);
            File repoHomeDir = new File(repoHomePath);

            repository = new TransientRepository(configFile, repoHomeDir);
        }
        return repository;
    }
}
