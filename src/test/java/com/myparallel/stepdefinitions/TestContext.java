package com.myparallel.stepdefinitions;

import com.myparallel.CucumberRunner;

public class TestContext {
    private static CucumberRunner cucumberRunner;
    private static String browserName;

    public static CucumberRunner getCucumberRunner() {
        return cucumberRunner;
    }

    public static void setCucumberRunner(CucumberRunner runner) {
        cucumberRunner = runner;
    }

    public static String getBrowserName() {
        return browserName;
    }

    public static void setBrowserName(String name) {
        browserName = name;
    }
}
