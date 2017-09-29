package com.identity.service;

import org.junit.Test;

import java.io.File;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class FileSummaryTest {

    @Test
    public void givenNullDirectoryThrowsError() throws Exception {
        assertThat(catchThrowable(() -> new FileSummaryImpl(null)))
                                                .isInstanceOf(NullPointerException.class)
                                                .hasMessage("File can not be null");
    }

    @Test
    public void givenADirectoryMimeTypeShouldBeEmpty() throws Exception {
        Optional<String> mimeType = new FileSummaryImpl(new File(".")).getMimeType();
        assertThat(mimeType.isPresent()).isFalse();
    }

    @Test
    public void givenANonExistingFileShouldBeEmpty() throws Exception {
        Optional<String> mimeType = new FileSummaryImpl(new File("./xxxx")).getMimeType();
        assertThat(mimeType.isPresent()).isFalse();
    }

    @Test
    public void givenAnExcelFileThenMimeTypeShouldBe() throws Exception {
        Optional<String> mimeType = new FileSummaryImpl(new File("src/test/resources/folders/2-files/y.xls")).getMimeType();
        assertThat(mimeType.get()).isEqualToIgnoringCase("application/vnd.ms-excel");
    }

    @Test
    public void givenAFileWithoutExtensionShouldReturnEmpty() throws Exception {
        assertThat(new FileSummaryImpl(new File("./xxxx")).getFileExtension()).isEmpty();
    }

    @Test
    public void givenAFileNameWithTooManyDotsShouldReturnExtensionCorrect() throws Exception {
        assertThat(new FileSummaryImpl(new File("./xxxx.a.b.c")).getFileExtension().get()).isEqualToIgnoringCase("c");
    }

}
