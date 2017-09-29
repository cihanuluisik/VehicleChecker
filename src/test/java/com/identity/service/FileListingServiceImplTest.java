package com.identity.service;

import com.identity.service.helper.TestBeansBuilder;
import com.identity.service.helper.TestFileFinder;
import org.junit.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class FileListingServiceImplTest {

    private TestFileFinder testFileFinder  = new TestFileFinder("src/test/resources/");
    private TestBeansBuilder testBeansBuilder = new TestBeansBuilder();

    @Test
    public void givenNonExistingDirectoryThrowsError() throws Exception {
        assertThat(catchThrowable(() -> testBeansBuilder.getFileListingService("nonExistingFolder").getFiles()))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("nonExistingFolder does not exist");
    }

    @Test
    public void givenAFileToServiceInsteadOfFolderThrowsError() throws Exception {
        assertThat(catchThrowable(() -> testBeansBuilder.getFileListingService("src/test/resources/folders/1-file/x.csv").getFiles()))
                .isInstanceOf(RuntimeException.class)
                .hasMessageEndingWith("x.csv is not a directory");
    }

    @Test
    public void givenSingleFileFolderThenReturnsCorrectFileFeatures() throws Exception {
        List<FileSummary> fileSummaryList = testBeansBuilder.getFileListingService("src/test/resources/folders/1-file").getFiles();
        assertThat(fileSummaryList).hasSize(1);
        FileSummary fileSummary = fileSummaryList.get(0);
        assertThat(fileSummary.getName()).isEqualToIgnoringCase("x.csv");
        assertThat(fileSummary.getFileExtension().get()).isEqualToIgnoringCase("csv");
        assertThat(fileSummary.getMimeType().get()).isEqualToIgnoringCase("application/vnd.ms-excel");
        assertThat(fileSummary.getSize()).isEqualTo(22L);
    }

    @Test
    public void givenAFolderWithTwoFilesThenReturnBoth() throws Exception {
        FileListingService service = testBeansBuilder.getFileListingService("src/test/resources/folders/2-files");
        List<FileSummary> fileSummaryList = service.getFiles();
        assertThat(fileSummaryList).hasSize(2);
        assertThat(fileSummaryList)
                .contains(new FileSummaryImpl(testFileFinder.testFile("folders/2-files/x.csv"))
                         ,new FileSummaryImpl(testFileFinder.testFile("folders/2-files/y.xls")));
    }

    @Test
    public void givenAFolderWithTwoRelevantFilesThenReturnBoth() throws Exception {
        FileListingService service = testBeansBuilder.getFileListingService("src/test/resources/folders/2-files-valid");
        List<FileSummary> fileSummaryList = service.getFiles();
        assertThat(fileSummaryList).hasSize(2);
        assertThat(fileSummaryList)
                .contains(new FileSummaryImpl(testFileFinder.testFile("folders/2-files-valid/x.csv"))
                         ,new FileSummaryImpl(testFileFinder.testFile("folders/2-files-valid/y.xls")));
    }

    @Test
    public void givenAFolderWithSubfolderThenIgnoreTheSubfolder() throws Exception {
        FileListingService service = testBeansBuilder.getFileListingService("src/test/resources/folders/2-files-with-subfolder");
        List<FileSummary> fileSummaryList = service.getFiles();
        assertThat(fileSummaryList).hasSize(2);
        assertThat(fileSummaryList)
                .contains(new FileSummaryImpl(testFileFinder.testFile("folders/2-files-with-subfolder/x.csv"))
                         ,new FileSummaryImpl(testFileFinder.testFile("folders/2-files-with-subfolder/y.xls")));
    }

    @Test
    public void givenAMixedFolderWith10RelevantFilesThenReturn10() throws Exception {
        FileListingService service = testBeansBuilder.getFileListingService("src/test/resources/folders/10-files-mixed");
        List<FileSummary> fileSummaryList = service.getFiles();
        assertThat(fileSummaryList).hasSize(10);
        assertThat(fileSummaryList)
                .contains(
                        new FileSummaryImpl(testFileFinder.testFile("folders/10-files-mixed/x1.csv"))
                        ,new FileSummaryImpl(testFileFinder.testFile("folders/10-files-mixed/x2.csv"))
                        ,new FileSummaryImpl(testFileFinder.testFile("folders/10-files-mixed/x3.csv"))
                        ,new FileSummaryImpl(testFileFinder.testFile("folders/10-files-mixed/x4.csv"))
                        ,new FileSummaryImpl(testFileFinder.testFile("folders/10-files-mixed/x5.csv"))
                        ,new FileSummaryImpl(testFileFinder.testFile("folders/10-files-mixed/y1.xls"))
                        ,new FileSummaryImpl(testFileFinder.testFile("folders/10-files-mixed/y2.xls"))
                        ,new FileSummaryImpl(testFileFinder.testFile("folders/10-files-mixed/y3.xls"))
                        ,new FileSummaryImpl(testFileFinder.testFile("folders/10-files-mixed/y4.xls"))
                        ,new FileSummaryImpl(testFileFinder.testFile("folders/10-files-mixed/y5.xls"))
                );
    }

}
