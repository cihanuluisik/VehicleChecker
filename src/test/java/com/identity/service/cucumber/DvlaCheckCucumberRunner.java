package com.identity.service.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/vehicle.check.feature"
        ,format = {"pretty","html:reports/service-report"}
        ,glue={"com.identity.service.cucumber"}
)
public class DvlaCheckCucumberRunner {

}


