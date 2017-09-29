package com.identity.service.helper;

import java.io.File;
import java.nio.file.Paths;

public class TestFileFinder {

    private final String testResourcesFolder;

    public TestFileFinder(String testResourcesFolder) {
        this.testResourcesFolder = testResourcesFolder;
    }

    public File testFile(String filePath) {
        return Paths.get(System.getProperty("user.dir"), testResourcesFolder + filePath).toFile();
    }
}
