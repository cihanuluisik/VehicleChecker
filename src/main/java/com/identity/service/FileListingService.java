package com.identity.service;

import java.io.IOException;
import java.util.List;

public interface FileListingService {
    /**
     * Returns filtered files's summaries
     * @return list of file summaries
     * @throws IOException
     */
    List<FileSummary> getFiles() throws IOException;
}
