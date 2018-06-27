package utils;

import SeleniumHelper.SeleniumHelper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Logger;

public class Listener extends SeleniumHelper implements ITestListener {
    Logger logger = Logger.getLogger(Listener.class.getName());

    @Override
    public void onTestStart(ITestResult iTestResult) {

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        logger.info("test failed" + iTestResult.getName());
        logger.info("inside test failure method");
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat fom = new SimpleDateFormat("dd_MM_YYY_hh_mm_ss");
        String mehodName = iTestResult.getName();
        if (!iTestResult.isSuccess()) {
            File file = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
            String path = new File(System.getProperty("user.dir")).getAbsolutePath() + "\\src\\resources\\";

            File fileDes = new File(path + "/failure_Screenshots/" + mehodName + fom.format(calendar.getTime()) + ".png");
            logger.info("path" + fileDes);

            try {
                FileUtils.copyFile(file, fileDes);

                Reporter.log("<a href='" + fileDes.getAbsolutePath() + "'><img src='" + fileDes.getAbsolutePath() + "height='100' width='100'/> " + "<br />");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }
}
