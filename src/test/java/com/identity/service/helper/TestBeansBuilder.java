package com.identity.service.helper;

import com.identity.service.FileListingService;
import com.identity.service.FileListingServiceImpl;

public class TestBeansBuilder {
    public FileListingService getFileListingService(String inputFolder) {
        return new FileListingServiceImpl.Builder(inputFolder).build();
    }
}
