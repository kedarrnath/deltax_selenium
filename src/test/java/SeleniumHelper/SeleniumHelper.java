package SeleniumHelper;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.*;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class SeleniumHelper {
    public WebDriver webDriver;
    FileInputStream is;
    Properties prop;
    Logger logger = Logger.getLogger(SeleniumHelper.class.getName());
    public static ExtentReports extentReports;
    public static ExtentTest extent;


    public void init() throws IOException {
        loadProperties();
        selectBrowser(prop.getProperty("browser"));
        getUrl(prop.getProperty("url"));
        extentReports = new ExtentReports(System.getProperty("user.dir") + "\\src\\resources\\testReports\\test.html", false);
    }

    public void selectBrowser(String browser) {
        if (System.getProperty("os.name").contains("Window")) {
            if (browser.equalsIgnoreCase("firefox")) {
                //https://github.com/mozilla/geckodriver/releases
                System.out.println(System.getProperty("user.dir"));
                System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\src\\Drivers\\geckodriver.exe");
                webDriver = new FirefoxDriver();
            } else if (browser.equalsIgnoreCase("chrome")) {
                //https://chromedriver.storage.googleapis.com/index.html
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\main\\java\\Drivers\\chromedriver.exe");
                webDriver = new ChromeDriver();
            }
        } else if (System.getProperty("os.name").contains("Mac")) {
            System.out.println(System.getProperty("os.name"));
            if (browser.equalsIgnoreCase("firefox")) {
                System.out.println(System.getProperty("user.dir"));
                System.setProperty("webdriver.firefox.marionette", System.getProperty("user.dir") + "/Drivers/geckodriver");
                webDriver = new FirefoxDriver();
            } else if (browser.equalsIgnoreCase("chrome")) {
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\main\\java\\Drivers\\chromedriver.exe");
                webDriver = new ChromeDriver();
            }
        }
    }

    public void getUrl(String url) throws IOException {
        webDriver.get(url);
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

    }

    public void waitForLoad(WebDriver driver) throws InterruptedException {
        ExpectedCondition<Boolean> pageLoadCondition = driver1 -> ((JavascriptExecutor) driver1).executeScript("return document.readyState").equals("complete");
        Thread.sleep(2000);
        WebDriverWait wait = new WebDriverWait(driver, 4000);
        wait.until(pageLoadCondition);
    }

    public void refreshPage() throws InterruptedException {
        webDriver.navigate().refresh();
        Thread.sleep(600);
    }

    public void loadProperties() throws IOException {
        prop = new Properties();
        is = new FileInputStream(new File(System.getProperty("user.dir") +"\\src\\main\\java\\resources\\config.properties"));

        prop.load(is);
    }

    public void getResult(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.SUCCESS) {
            extent.log(LogStatus.PASS, result.getName() + "test is pass");
        } else if (result.getStatus() == ITestResult.FAILURE) {
            String screenCapture = getScreenShot("");
            extent.log(LogStatus.FAIL, extent.addScreenCapture(screenCapture) + "test is failed" + result.getThrowable());
        }
    }

    @Deprecated
    public String[][] getDataFromSheet(String excelName) throws IOException, InvalidFormatException {

        String path = System.getProperty("user.dir") + "\\src\\resources\\" + excelName;

        FileInputStream fis = new FileInputStream(path);

        Workbook wb = WorkbookFactory.create(fis);

        Sheet s = wb.getSheet("Sheet1");

        int totalColNums = s.getRow(0).getLastCellNum();
        int totalRowNums = s.getLastRowNum() + 1;

        String values[][] = new String[totalRowNums][totalColNums];
        logger.info("totalCols:" + totalColNums + totalRowNums);

        for (int i = s.getFirstRowNum(); i <= s.getLastRowNum(); i++) {
            for (int j = 0; j < totalColNums; j++) {
                values[i][j] = s.getRow(i).getCell(j).getStringCellValue();
                logger.info("msg " + values[i][j]);
            }
        }

        return values;
    }

    public String getScreenShot(String name) throws IOException {

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYY-MM-DD-HH-MM-SS");
        File file = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + "/";


        File fileDes = new File(path + "\\src\\resources\\" + name + "_" + ".png");

        logger.info("absPath" + fileDes);

        FileUtils.copyFile(file, fileDes);
        return fileDes.toString();
    }

    @AfterMethod()
    public void afterMethod(ITestResult result) throws IOException {
        getResult(result);
    }

    @BeforeMethod()
    public void beforeMethod(Method result) {
        extent = extentReports.startTest(result.getName());
        extent.log(LogStatus.INFO, result.getName() + "test started");

    }

//    @AfterClass(alwaysRun = true)
//    public void end() {
//        webDriver.quit();
//        extentReports.endTest(extent);
//        extentReports.flush();
//    }
}
