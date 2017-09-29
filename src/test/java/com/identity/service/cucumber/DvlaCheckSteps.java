package com.identity.service.cucumber;

import com.identity.service.helper.SelinimumChromeDriverService;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class DvlaCheckSteps {

    private WebDriver driver;
    private  SelinimumChromeDriverService selinimumChromeDriverService = new SelinimumChromeDriverService("C:/Users/Cihan/Downloads/chromedriver.exe");

    @Before
    public void before() throws IOException {
        selinimumChromeDriverService.start();
        driver = selinimumChromeDriverService.createDriver();
    }

    @After
    public void after() throws IOException {
        driver.quit();
        selinimumChromeDriverService.stop();
    }

    @When("^I navigate to \"([^\"]*)\"$")
    public void iNavigateTo(String url) throws Throwable {
        driver.get(url);

    }

    @And("^click 'Start Now'$")
    public void clickStartNow() throws Throwable {
        driver.findElement(By.partialLinkText("Start now")).click();
    }

    @And("^enter vehicle registration number \"([^\"]*)\"$")
    public void enterVehicleRegistrationNumberRegistration(String registration) throws Throwable {
        driver.findElement(By.id("Vrm")).sendKeys(registration);
    }

    @And("^click 'Continue'$")
    public void clickContinue() throws Throwable {
        driver.findElement(By.name("Continue")).click();
    }

    @Then("^make should be \"([^\"]*)\"$")
    public void makeShouldBe(String givenMake) throws Throwable {
        String makeOnthePage = driver.findElement(By.xpath("//span[.='Make']/following-sibling::span[1]")).getText();
        assertThat(makeOnthePage).isEqualToIgnoringCase(givenMake);
    }

    @And("^color should be \"([^\"]*)\"$")
    public void colorShouldBe(String color) throws Throwable {
        String colorOnthePage = driver.findElement(By.xpath("//span[.='Colour']/following-sibling::span[1]")).getText();
        assertThat(colorOnthePage).isEqualToIgnoringCase(color);
    }

}
