package com.identity.service;

import org.apache.log4j.Logger;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Objects;
import java.util.Optional;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static java.util.Optional.ofNullable;

public class FileSummaryImpl implements FileSummary {

    final static Logger logger = Logger.getLogger(FileSummaryImpl.class);

    private final File file;

    public FileSummaryImpl(File file)  {
        Objects.requireNonNull(file,"File can not be null");
        this.file = file;
    }

    @Override
    public String getName() {
        return file.getName();
    }

    @Override
    public Optional<String> getMimeType() {
        try {
            return ofNullable(Files.probeContentType(file.toPath()));
        } catch (IOException e) {
            logger.error("mime type can not be read of file", e);
        }
        return empty();
    }

    @Override
    public Long getSize() {
        return file.length();
    }

    @Override
    public Optional<String> getFileExtension() {
        String[] fileNameParts = getName().split("\\.");
        return fileNameParts.length > 1 ? of(fileNameParts[fileNameParts.length-1]) : empty() ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileSummaryImpl that = (FileSummaryImpl) o;
        return file != null ? file.equals(that.file) : that.file == null;
    }

    @Override
    public int hashCode() {
        return file != null ? file.hashCode() : 0;
    }
}
