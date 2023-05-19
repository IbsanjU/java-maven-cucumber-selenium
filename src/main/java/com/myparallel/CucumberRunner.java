package com.myparallel;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.*;

@RunWith(Parameterized.class)
public class CucumberRunner {
    public static String browserName;
    private static final int NUM_THREADS = 3; // Number of threads to run in parallel
    private WebDriver driver;

    public CucumberRunner(String browserName) {
        this.browserName = browserName;
    }

    @Parameters
    public static Object[] browsers() {
        return new Object[]{"chrome"};//, "firefox", "edge"};
    }

    @Before
    public void setupDriver() {
        BaseCollection.initializeBrowser(browserName);
        driver = BaseCollection.getBrowser(browserName);
    }

    @After
    public void teardownDriver() {
        BaseCollection.closeBrowser(browserName);
    }

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(NUM_THREADS);
        CompletionService<String> completionService = new ExecutorCompletionService<>(executorService);

        String[] browsers = {"chrome"};//, "firefox", "edge"};

        for (String browser : browsers) {
            completionService.submit(() -> {
                CucumberRunner cucumberRunner = new CucumberRunner(browser);
                cucumberRunner.setupDriver();
                cucumberRunner.runCucumberTests();
                cucumberRunner.teardownDriver();
                return browser;
            });
        }

        // Wait for all tasks to complete
        for (int i = 0; i < browsers.length; i++) {
            Future<String> future = completionService.take();
            String browser = future.get();
            System.out.println("Tests completed for browser: " + browser);
        }

        // Shut down the executor service
        executorService.shutdown();
    }

    private void runCucumberTests() {
        Class[] classes = {ParallelCucumberRunner.class};
        JUnitCore.runClasses(classes);
    }
}
