package com.myparallel;

import java.util.concurrent.*;

public class ParallelBrowserRunner {
    private static final int NUM_THREADS = 3; // Number of threads to run in parallel

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(NUM_THREADS);
        CompletionService<String> completionService = new ExecutorCompletionService<>(executorService);

        String[] browsers = {"chrome", "firefox", "ie"};//, "edge"};

        for (String browser : browsers) {
            completionService.submit(() -> {
                // Run your test or test suite for the specified browser here
                runTests(browser);
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

    private static void runTests(String browser) {
        // Implement your test execution logic for the specified browser
        System.out.println("Running tests for browser: " + browser);

        // Example: Run Cucumber tests with the specified browser using CucumberRunner
        System.setProperty("browser", browser);
        try {
            CucumberRunner.main(new String[]{});
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
