package com.identity.service.helper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class SelinimumChromeDriverService {

    private final ChromeDriverService chromeDriverService;

    public void start() throws IOException {
        chromeDriverService.start();
    }

    public void stop() {
        chromeDriverService.stop();
    }

    public SelinimumChromeDriverService(String execFile) {
        System.setProperty("webdriver.chrome.driver", execFile);
        chromeDriverService = ChromeDriverService.createDefaultService();
    }

    public WebDriver createDriver() {
        WebDriver driver = new RemoteWebDriver(chromeDriverService.getUrl(), DesiredCapabilities.chrome());
        // Puts an Implicit wait, this means that any search for elements on the page could take the
        // time the implicit wait is set for before throwing exception
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return  driver;
    }

}
