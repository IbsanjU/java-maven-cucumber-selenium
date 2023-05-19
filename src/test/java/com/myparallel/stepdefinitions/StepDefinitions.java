package com.myparallel.stepdefinitions;

import com.myparallel.BaseCollection;
import com.myparallel.CucumberRunner;
import io.cucumber.java.en.Given;
import org.openqa.selenium.WebDriver;

public class StepDefinitions {
    private WebDriver driver;

    @Given("I open the browser")
    public void iOpenTheBrowser() {
        driver = BaseCollection.getBrowser(TestContext.getBrowserName());
        // Perform actions on the retrieved browser instance
    }

    // Add more step definitions as needed
}
