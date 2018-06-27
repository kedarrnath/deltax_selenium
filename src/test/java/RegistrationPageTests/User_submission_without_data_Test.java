package RegistrationPageTests;

import SeleniumHelper.SeleniumHelper;
import UiPages.RegistrationPage;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class User_submission_without_data_Test extends SeleniumHelper {
    RegistrationPage registrationPage;

    @BeforeTest
    public void setup() throws IOException {
        init();
        registrationPage = new RegistrationPage(webDriver);
    }

    @Test
    public void clickOnSubmitWithoutData() {
        // click on Submit button
        registrationPage.clickSubmit();

        // Assert that error messages was shown if user clicks without providing data
        Assert.assertEquals(registrationPage.errToastsVisibleOnRegistrationPage(), true);

    }

    @AfterTest
    public void close() {
        webDriver.close();
    }
}
