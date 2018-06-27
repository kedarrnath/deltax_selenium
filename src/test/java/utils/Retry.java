package utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class Retry implements IRetryAnalyzer {
    private int retryCount = 0;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (retryCount < 3) {
            System.out.println("Retrying Test ::" + iTestResult.getName() + "status" + getResultName(iTestResult.getStatus()));
            retryCount++;
            return true;
        }
        return false;
    }

    public String getResultName(int status) {
        String resultName = null;
        if (status == 1) {
            resultName = "SUCCESS";
        }
        if (status == 2) {
            resultName = "FAILURE";
        }
        if (status == 3) {
            resultName = "SKIP";
        }
        return resultName;
    }

    public void transform(ITestAnnotation testAnnotation, Class object, Constructor test, Method test1) {
        IRetryAnalyzer retryAnalyzer = testAnnotation.getRetryAnalyzer();
        if (retryAnalyzer == null) {
            testAnnotation.setRetryAnalyzer(Retry.class);
        }

    }

}
