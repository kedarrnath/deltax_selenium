package RegistrationPageTests;

import SeleniumHelper.SeleniumHelper;
import UiPages.RegistrationPage;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class User_submission_without_mandatory_fields_Test extends SeleniumHelper {
    RegistrationPage registrationPage;


    @BeforeTest
    public void setUp() throws IOException {
        init();
        registrationPage = new RegistrationPage(webDriver);
    }


    @Test
    public void clickOnSubmitWithoutMandatoryData() throws InterruptedException {

        registrationPage.fillContactNo("1234567890");

        registrationPage.filldepartName(1);

        //   click on Submit button
        registrationPage.clickSubmit();

        // Assert that error messages was shown if user clicks without providing data
        Assert.assertEquals(registrationPage.errToastsVisibleOnRegistrationPage(), true);

    }

    @AfterTest
    public void close() {
        webDriver.close();
    }
}
