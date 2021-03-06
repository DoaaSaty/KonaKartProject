package com.konakart.automation.games;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
	private int retryCount = 0;
//original: TODO: uncomment  private int maxRetryCount = 4;
    private int maxRetryCount = 3;//for faster testing

    public boolean retry(ITestResult result) {

        if (retryCount < maxRetryCount) {
            retryCount++;
            return true;
        }
        return false;
    }

}
