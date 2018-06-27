package RegistrationPageTests;

import SeleniumHelper.SeleniumHelper;
import UiPages.RegistrationPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class Url_Redirection_Tests extends SeleniumHelper {

    RegistrationPage registrationPage;

    @BeforeTest
    public void setUp() throws IOException {
        init();
        registrationPage = new RegistrationPage(webDriver);
    }

    @Test
    public void testRedirectionFailureWithoutSubmitAction() {
        webDriver.navigate().to("http://adjiva.com/qa-test/thanks.html");
        Assert.assertEquals(webDriver.getCurrentUrl(), "http://adjiva.com/qa-test/");
    }

    @Test
    public void testRemoveDataonRefreshPage() {
        registrationPage.fillFirstName("kedarnath");

        webDriver.navigate().refresh();

        Assert.assertEquals(webDriver.findElement(By.name("first_name")).getText(),"");
    }


    @AfterTest
    public void close() {
        webDriver.close();
    }
}
