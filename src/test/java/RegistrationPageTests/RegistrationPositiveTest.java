package RegistrationPageTests;

import SeleniumHelper.SeleniumHelper;
import UiPages.RegistrationPage;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class RegistrationPositiveTest extends SeleniumHelper {

    public static final int Engineering = 1;
    public static final int Marketing = 2;
    public static final int Sales = 3;
    public static final int support = 4;

    RegistrationPage registrationPage;

    @BeforeTest
    public void setUp() throws IOException {
        init();
        registrationPage = new RegistrationPage(webDriver);
    }


    @Test
    public void testWithProperData()
    {
        registrationPage.fillFirstName("kedarnath");
        registrationPage.fillLastName("kasamsetty");
        registrationPage.filldepartName(Engineering);
        registrationPage.filluserName("kedarnath");
        registrationPage.fillpassword("password");
        registrationPage.fillConfirmpassword("password");
        registrationPage.fillEmail("kedar.1224@gmail.com");
        registrationPage.fillContactNo("1234567890");

        registrationPage.clickSubmit();


        // Assert that Registration is successfull
        Assert.assertEquals(webDriver.getCurrentUrl(),"http://adjiva.com/qa-test/thanks.html");
    }

    @AfterTest
    public void close() {
        webDriver.close();
    }
}
