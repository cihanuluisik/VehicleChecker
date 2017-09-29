package com.identity.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class FileListingServiceImpl implements FileListingService {

    private final String inputFolder;
    private final List<String> extensions;
    private final String runTimePath;

    /**
     * @param inputFolder directory to scan
     * @param extensions  file extensions to filter in
     */
    private FileListingServiceImpl(String inputFolder, List<String> extensions) {
        this.inputFolder = inputFolder;
        this.extensions = extensions;
        runTimePath = System.getProperty("user.dir");
    }

    @Override
    public List<FileSummary> getFiles() throws IOException {
        Path inputPath = validateDirectory();
        List<FileSummary> fileSummaryList = Files.list(inputPath)
                .filter(Files::isRegularFile)
                .map(path -> new FileSummaryImpl(path.toFile()))
                .filter(fileSummary -> extensions.contains(fileSummary.getFileExtension().get()))
                .collect(Collectors.toList());
        return fileSummaryList;
    }

    private Path validateDirectory() {
        Path inputPath = Paths.get(runTimePath, inputFolder);

        if (!Files.exists(inputPath)) {
            throw new RuntimeException(inputFolder + " does not exist");
        }
        if (!Files.isDirectory(inputPath)) {
            throw new RuntimeException(inputFolder + " is not a directory");
        }
        return inputPath;
    }

    public static class Builder {
        private final String inputFolder;
        private List<String> extensions = List.of("csv", "xls");

        public Builder(String inputFolder) {
            this.inputFolder = inputFolder;
        }

        public Builder withExtensions(List<String> extensions) {
            this.extensions = extensions;
            return this;
        }

        public FileListingService build() {
            return new FileListingServiceImpl(inputFolder, extensions);
        }
    }

}
