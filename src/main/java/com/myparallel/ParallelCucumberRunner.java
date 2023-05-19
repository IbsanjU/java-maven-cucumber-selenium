package com.myparallel;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber-reports/" + TestContext.getBrowserName()},
        features = "src/test/resources/features",
        glue = "com.myparallel.stepdefinitions"
//        tags = "@YourTag"
)
public class ParallelCucumberRunner extends CucumberRunner {
    public ParallelCucumberRunner() {
        super(TestContext.getBrowserName());
    }
}
