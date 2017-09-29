package com.identity.service;

import java.util.Optional;

/**
 * File summary interface having exposing specified fields.
 */
public interface FileSummary {

    String getName();

    Optional<String> getMimeType();

    Long getSize();

    Optional<String> getFileExtension();

}
